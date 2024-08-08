package com.example.swiggato.services;

import com.example.swiggato.dtos.response.MenuResponse;
import com.example.swiggato.enums.MenuCategory;
import com.example.swiggato.exceptions.RestaurantNotFound;
import com.example.swiggato.mapper.MenuMapper;
import com.example.swiggato.models.MenuItem;
import com.example.swiggato.models.Restaurant;
import com.example.swiggato.repositories.MenuRepository;
import com.example.swiggato.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {


    final MenuRepository menuRepository;
    final RestaurantRepository restaurantRepository;


    @Autowired
    public MenuService(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }


    public List<MenuResponse> findAllMenuItemsByCategory(MenuCategory menuCategory) {
        List<MenuItem> menuItems = menuRepository.findByCategory(menuCategory);
        return menuItems.stream().map(foodItem -> MenuMapper.modelToResponseDto(foodItem)).collect(Collectors.toList());
    }

    public List<MenuResponse> findAllMenuItemsByCategoryPriceGreaterThanXByRId(MenuCategory menuCategory, int restaurantId, double price) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        if(restaurant==null){
            throw new RestaurantNotFound("Restaurant not found!!");
        }
        List<MenuItem> menuItems = menuRepository.findByCategoryAndRestaurantAndPriceGreaterThan(menuCategory,restaurant,price);

        return  menuItems.stream().map(foodItem -> MenuMapper.modelToResponseDto(foodItem)).collect(Collectors.toList());
    }

    public List<MenuResponse> findAllByVegAndRId(boolean isVeg, int restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        if(restaurant==null){
            throw new RestaurantNotFound("Restaurant not found!!");
        }
        List<MenuItem> menuItems = menuRepository.findByVegAndRestaurant(isVeg,restaurant);
        return menuItems.stream().map(foodItem -> MenuMapper.modelToResponseDto(foodItem)).collect(Collectors.toList());
    }

    public List<MenuResponse> findCheapest5ByRestaurant(int id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        if(restaurant==null){
            throw new RestaurantNotFound("Restaurant not found!!");
        }
        List<MenuItem> menuItems = menuRepository.findTop5ByRestaurantOrderByPrice(restaurant);
        return menuItems.stream().map(foodItem -> MenuMapper.modelToResponseDto(foodItem)).collect(Collectors.toList());
    }

    public List<MenuResponse> findCostliest5ByRestaurant(int id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        if(restaurant==null){
            throw new RestaurantNotFound("Restaurant not found!!");
        }
        List<MenuItem> menuItems = menuRepository.findTop5ByRestaurantOrderByPriceDesc(restaurant);
        return menuItems.stream().map(foodItem -> MenuMapper.modelToResponseDto(foodItem)).collect(Collectors.toList());
    }

    public List<MenuResponse> findCostliest5ByCategory(MenuCategory category) {
        List<MenuItem> menuItems = menuRepository.findTop5ByCategoryOrderByPriceDesc(category);
        return menuItems.stream().map(foodItem -> MenuMapper.modelToResponseDto(foodItem)).collect(Collectors.toList());
    }
}
