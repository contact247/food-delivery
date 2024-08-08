package com.example.swiggato.controllers;

import com.example.swiggato.dtos.request.MenuItemRequest;
import com.example.swiggato.dtos.request.RestaurantRequest;
import com.example.swiggato.dtos.response.MenuResponse;
import com.example.swiggato.dtos.response.RestaurantResponse;
import com.example.swiggato.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    final RestaurantService restaurantService;


    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/add")
    public ResponseEntity addRestaurant(@RequestBody RestaurantRequest restaurantRequest){
        RestaurantResponse restaurantResponse = restaurantService.addRestaurant(restaurantRequest);
        return new ResponseEntity(restaurantResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update/status")
    public ResponseEntity changeOpenedStatus(@RequestParam int id){
        try {
            String response = restaurantService.changeOpenedStatus(id);
            return new ResponseEntity(response,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add/menu")
    public ResponseEntity addMenuItemToRestaurant(@RequestBody MenuItemRequest menuItemRequest){
        RestaurantResponse restaurantResponse = restaurantService.addMenuItemToRestaurant(menuItemRequest);
        return new ResponseEntity(restaurantResponse,HttpStatus.CREATED);
    }

    // get menu of restaurant

    @GetMapping("/menu")
    public ResponseEntity getMenu(@RequestParam int id){
        List<MenuResponse> menu = restaurantService.getMenu(id);
        return new ResponseEntity(menu,HttpStatus.FOUND);
    }


    // give the restaurants who have served more than 'x' number of orders
    @GetMapping("/servedMoreThanXOrders")
    public ResponseEntity getRestservedMoreThanXOrders(@RequestParam int x){
        List<RestaurantResponse> restaurantResponses = restaurantService.getRestservedMoreThanXOrders(x);
        return new ResponseEntity(restaurantResponses ,HttpStatus.FOUND);
    }

    // give the restaurants which have maximum number of items in their menu and which are open also
    @GetMapping("/restaurantWithMaxItemsAndOpen")
    public ResponseEntity getRestaurantWithMaxItemsAndOpen(){
        List<RestaurantResponse> restaurantResponses = restaurantService.getRestaurantWithMaxItemsAndOpen();
        return new ResponseEntity(restaurantResponses, HttpStatus.FOUND);
    }
}
