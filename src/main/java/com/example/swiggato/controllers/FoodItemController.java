package com.example.swiggato.controllers;

import com.example.swiggato.services.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
public class FoodItemController {

final FoodItemService foodItemService;

    @Autowired
    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }


    // give the food category of the item which is ordered most
    @GetMapping("/categoryMostOrdered")
    public ResponseEntity getCategoryMostOrdered(){
        String categoryMostOrdered = foodItemService.getCategoryMostOrdered();
        return new ResponseEntity(categoryMostOrdered, HttpStatus.FOUND);
    }
}
