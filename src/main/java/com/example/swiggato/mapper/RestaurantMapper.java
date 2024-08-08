package com.example.swiggato.mapper;

import com.example.swiggato.dtos.request.RestaurantRequest;
import com.example.swiggato.dtos.response.MenuResponse;
import com.example.swiggato.dtos.response.RestaurantResponse;
import com.example.swiggato.models.Restaurant;

import java.util.List;
import java.util.stream.Collectors;

public class RestaurantMapper {

    public static Restaurant requestDtoToModel(RestaurantRequest restaurantRequest){

        return Restaurant.builder()
                .name(restaurantRequest.getName())
                .contactNumber(restaurantRequest.getContactNumber())
                .restaurantCategory(restaurantRequest.getRestaurantCategory())
                .location(restaurantRequest.getLocation())
                .opened(false)
                .build();
    }

    public static RestaurantResponse modelToResponseDto(Restaurant restaurant){
        List<MenuResponse> menu = restaurant.getAvailableMenuItems().stream()
                .map(foodItem -> MenuMapper.modelToResponseDto(foodItem)).collect(Collectors.toList());

        RestaurantResponse restaurantResponse = RestaurantResponse.builder()
                .contactNumber(restaurant.getContactNumber())
                .location(restaurant.getLocation())
                .menu(menu)
                .name(restaurant.getName())
                .opened(restaurant.isOpened())
                .build();

        return restaurantResponse;
    }
}
