package com.example.swiggato.dtos.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodRequest {
    int requiredQuantity;
    String customerMobile;
    int menuItemId;
}
