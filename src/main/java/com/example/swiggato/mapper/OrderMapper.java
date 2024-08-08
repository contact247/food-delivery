package com.example.swiggato.mapper;

import com.example.swiggato.dtos.response.FoodResponse;
import com.example.swiggato.dtos.response.OrderResponse;
import com.example.swiggato.models.Cart;
import com.example.swiggato.models.FoodItem;
import com.example.swiggato.models.OrderEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderMapper {

    public static OrderEntity prepareOrder(Cart cart){
        return OrderEntity.builder()
                .orderTotal(cart.getCartTotal())
                .orderId(String.valueOf(UUID.randomUUID()))
                .build();
    }

    public static OrderResponse modelToResponseDto(OrderEntity order){
        List<FoodResponse> foodResponses = new ArrayList<>();

        for(FoodItem food : order.getFoodItems()){
            FoodResponse foodResponse = FoodResponse.builder()
                    .veg(food.getMenuItem().isVeg())
                    .dishName(food.getMenuItem().getDishName())
                    .price(food.getMenuItem().getPrice())
                    .category(food.getMenuItem().getCategory())
                    .quantityAdded(food.getRequiredQuantity())
                    .build();
            foodResponses.add(foodResponse);
        }

        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .orderTotal(order.getOrderTotal())
                .orderTime(order.getOrderTime())
                .customerName(order.getCustomer().getName())
                .customerMobile(order.getCustomer().getMobileNo())
                .deliveryPartnerName(order.getDeliveryPartner().getName())
                .deliveryPartnerMobile(order.getDeliveryPartner().getMobileNo())
                .restaurantName(order.getRestaurant().getName())
                .foodResponses(foodResponses)
                .build();
    }
}
