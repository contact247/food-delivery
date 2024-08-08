package com.example.swiggato.mapper;

import com.example.swiggato.dtos.response.CartResponse;
import com.example.swiggato.dtos.response.MenuResponse;
import com.example.swiggato.models.Cart;
import com.example.swiggato.models.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class CartMapper {
    public static CartResponse modelToResponseDto(Cart cart){
        List<MenuResponse> menuResponses = new ArrayList<>();
//        for(MenuItem x : cart.getMenuItems()){
//            menuResponses.add(MenuMapper.modelToResponseDto(x));
//        }
        return CartResponse.builder()
                .cartTotal(cart.getCartTotal())
                .menuResponses(menuResponses)
                .build();
    }
}
