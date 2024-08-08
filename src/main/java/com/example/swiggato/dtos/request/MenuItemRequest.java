package com.example.swiggato.dtos.request;


import com.example.swiggato.enums.MenuCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenuItemRequest {

    int restaurantId;

    String dishName;

    MenuCategory category;

    boolean veg;

    boolean available;

    double price;
}
