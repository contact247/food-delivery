package com.example.swiggato.dtos.request;

import com.example.swiggato.enums.RestaurantCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestaurantRequest {
    String name;
    String location;
    RestaurantCategory restaurantCategory;
    String contactNumber;
}
