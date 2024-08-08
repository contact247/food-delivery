package com.example.swiggato.services;

import com.example.swiggato.dtos.request.MenuItemRequest;
import com.example.swiggato.dtos.request.RestaurantRequest;
import com.example.swiggato.dtos.response.MenuResponse;
import com.example.swiggato.dtos.response.RestaurantResponse;
import com.example.swiggato.exceptions.RestaurantNotFound;
import com.example.swiggato.mapper.MenuMapper;
import com.example.swiggato.mapper.RestaurantMapper;
import com.example.swiggato.models.MenuItem;
import com.example.swiggato.models.Restaurant;
import com.example.swiggato.repositories.MenuRepository;
import com.example.swiggato.repositories.OrderRepository;
import com.example.swiggato.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    final RestaurantRepository restaurantRepository;

    final OrderRepository orderRepository;

    final MenuRepository menuRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, OrderRepository orderRepository, MenuRepository menuRepository) {
        this.restaurantRepository = restaurantRepository;
        this.orderRepository = orderRepository;
        this.menuRepository = menuRepository;
    }


    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = RestaurantMapper.requestDtoToModel(restaurantRequest);

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return RestaurantMapper.modelToResponseDto(savedRestaurant);
    }

    public String changeOpenedStatus(int id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if(optionalRestaurant.isEmpty()){
            throw new RestaurantNotFound("Restaurant doesn't exists!!");
        }

        Restaurant restaurant = optionalRestaurant.get();
        restaurant.setOpened(!restaurant.isOpened());
        restaurantRepository.save(restaurant);
        if(restaurant.isOpened()){
            return "Restaurant is open now!!";
        }

        return "Restaurant is close now!!";
    }

    public RestaurantResponse addMenuItemToRestaurant(MenuItemRequest menuItemRequest) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(menuItemRequest.getRestaurantId());

        if(optionalRestaurant.isEmpty()){
            throw new RestaurantNotFound("Restaurant doesn't exists!!");
        }

        Restaurant restaurant = optionalRestaurant.get();

        MenuItem menuItem = MenuMapper.requestDtoToModel(menuItemRequest);
        menuItem.setRestaurant(restaurant);

        restaurant.getAvailableMenuItems().add(menuItem);

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return RestaurantMapper.modelToResponseDto(savedRestaurant);
    }

    public List<MenuResponse> getMenu(int id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if(optionalRestaurant.isEmpty()){
            throw new RestaurantNotFound("Restaurant not found!!");
        }
        List<MenuResponse> menu = optionalRestaurant.get().getAvailableMenuItems()
                .stream()
                .map(foodItem -> MenuMapper.modelToResponseDto(foodItem))
                .collect(Collectors.toList());

        return menu;
    }

    public List<RestaurantResponse> getRestservedMoreThanXOrders(int x) {
        List<Restaurant> restaurants = new ArrayList<>();
       // List<Integer> restIds =
        restaurants = orderRepository.getRestservedMoreThanXOrders(x);
//        for(Integer id: restIds){
//            restaurants.add(restaurantRepository.findById(id).orElse(null));
//        }
        return restaurants.stream()
                .map(restaurant -> RestaurantMapper.modelToResponseDto(restaurant))
                .collect(Collectors.toList());
    }

    public List<RestaurantResponse> getRestaurantWithMaxItemsAndOpen() {
        List<Restaurant> restaurants = menuRepository.getRestaurantWithMaxItemsAndOpen();
        List<Restaurant> ans = new ArrayList<>();
        int maxCount = restaurants.get(0).getAvailableMenuItems().size();
        for(Restaurant x : restaurants){
            if(x.getAvailableMenuItems().size()!=maxCount){
                break;
            }else{
                ans.add(x);
            }
        }
       return ans.stream()
               .map(restaurant -> RestaurantMapper.modelToResponseDto(restaurant))
               .collect(Collectors.toList());
    }
}
