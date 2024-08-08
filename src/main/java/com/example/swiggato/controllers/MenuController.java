package com.example.swiggato.controllers;

import com.example.swiggato.dtos.response.MenuResponse;
import com.example.swiggato.enums.MenuCategory;
import com.example.swiggato.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

   final MenuService menuService;

   @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // get all foods of a particular category
    @GetMapping("/all/by/category/{menuCategory}")
    public ResponseEntity findAllMenuItemsByCategory(@PathVariable MenuCategory menuCategory){
       List<MenuResponse> menuResponses = menuService.findAllMenuItemsByCategory(menuCategory);
       return new ResponseEntity(menuResponses, HttpStatus.FOUND);
    }
    // get all MAIN_COURSE items with price above x rupees from a particular restaurant
    @GetMapping("/all/by/category/{menuCategory}/restaurantId/{restaurantId}/priceGreaterThan/{x}")
    public ResponseEntity findAllMenuItemsByCategoryPriceGreaterThanXByRId(@PathVariable MenuCategory menuCategory, @PathVariable int restaurantId, @PathVariable double x){
       List<MenuResponse> menuRespons = menuService.findAllMenuItemsByCategoryPriceGreaterThanXByRId(menuCategory,restaurantId,x);
       return new ResponseEntity(menuRespons,HttpStatus.FOUND);
    }
    // get all veg foods of a restaurant
    // get all non veg foods of a restaurant
    @GetMapping("/all/by/veg/{isVeg}/restaurantId/{restaurantId}")
    public ResponseEntity findAllByVegAndRId(@PathVariable boolean isVeg,@PathVariable int restaurantId){
//        System.out.println("REached here");
       List<MenuResponse> menuResponses = menuService.findAllByVegAndRId(isVeg,restaurantId);
       return new ResponseEntity(menuResponses,HttpStatus.FOUND);
    }

    // get cheapest 5 food items of a particular restaurant
    @GetMapping("/cheapest5/by/restaurant")
    public ResponseEntity findCheapest5ByRestaurant(@RequestParam int id)
    {
        List<MenuResponse> menuResponses = menuService.findCheapest5ByRestaurant(id);
        return new ResponseEntity(menuResponses,HttpStatus.FOUND);
    }
    // get costliest 5 food items of a particular restaurant
    @GetMapping("/costliest5/by/restaurant")
    public ResponseEntity findCostliest5ByRestaurant(@RequestParam int id)
    {
        List<MenuResponse> menuResponses = menuService.findCostliest5ByRestaurant(id);
        return new ResponseEntity(menuResponses,HttpStatus.FOUND);
    }
    // get costliest 5 food items of a particular category -> name of dish and restaurant which serve the dish
    @GetMapping("/costliest5/by/category/{category}")
    public ResponseEntity findCostliest5ByCategory(@PathVariable MenuCategory category)
    {
        List<MenuResponse> menuResponses = menuService.findCostliest5ByCategory(category);
        return new ResponseEntity(menuResponses,HttpStatus.FOUND);
    }
}
