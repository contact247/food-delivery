package com.example.swiggato.mapper;

import com.example.swiggato.dtos.request.MenuItemRequest;
import com.example.swiggato.dtos.response.MenuResponse;
import com.example.swiggato.models.MenuItem;

public class MenuMapper {

    public static MenuItem requestDtoToModel(MenuItemRequest menuItemRequest){

         return MenuItem.builder()
                .price(menuItemRequest.getPrice())
                .veg(menuItemRequest.isVeg())
                .available(menuItemRequest.isAvailable())
                .dishName(menuItemRequest.getDishName())
                .category(menuItemRequest.getCategory())
                .build();
    }

    public static MenuResponse modelToResponseDto(MenuItem menuItem){
        return  MenuResponse.builder()
                .veg(menuItem.isVeg())
                .price(menuItem.getPrice())
                .category(menuItem.getCategory())
                .dishName(menuItem.getDishName())
                .build();
    }
}
