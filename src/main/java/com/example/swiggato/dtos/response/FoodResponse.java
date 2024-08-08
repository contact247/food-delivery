package com.example.swiggato.dtos.response;

import com.example.swiggato.enums.MenuCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodResponse {
    String dishName;
    MenuCategory category;
    boolean veg;
    double price;
    int quantityAdded;
}
