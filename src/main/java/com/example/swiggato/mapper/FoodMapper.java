package com.example.swiggato.mapper;

import com.example.swiggato.dtos.response.FoodResponse;
import com.example.swiggato.models.FoodItem;

public class FoodMapper {
    public static FoodResponse modelToResponseDto(FoodItem food){
        return FoodResponse.builder()
                .veg(food.getMenuItem().isVeg())
                .dishName(food.getMenuItem().getDishName())
                .price(food.getMenuItem().getPrice())
                .category(food.getMenuItem().getCategory())
                .quantityAdded(food.getRequiredQuantity())
                .build();
    }
}
