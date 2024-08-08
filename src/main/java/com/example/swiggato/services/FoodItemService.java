package com.example.swiggato.services;

import com.example.swiggato.enums.MenuCategory;
import com.example.swiggato.repositories.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodItemService {

    final FoodItemRepository foodItemRepository;


    @Autowired
    public FoodItemService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    public String getCategoryMostOrdered() {
        MenuCategory menuCategory = foodItemRepository.getCategoryMostOrdered();
        return menuCategory.toString();
    }
}
