package com.example.swiggato.dtos.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartStatusResponse {
    String customerName;
    String customerAddress;
    String customerMobile;
    double cartTotal;
    List<FoodResponse> foodResponses;
    String restaurantName;
}
