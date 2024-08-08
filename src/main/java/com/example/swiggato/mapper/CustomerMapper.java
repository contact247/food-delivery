package com.example.swiggato.mapper;

import com.example.swiggato.dtos.request.CustomerRequest;
import com.example.swiggato.dtos.response.CartResponse;
import com.example.swiggato.dtos.response.CustomerResponse;
import com.example.swiggato.models.Customer;

public class CustomerMapper {
    public static Customer requestDtoToModel(CustomerRequest customerRequest){
        return Customer.builder()
                .name(customerRequest.getName())
                .email(customerRequest.getEmail())
                .address(customerRequest.getAddress())
                .gender(customerRequest.getGender())
                .mobileNo(customerRequest.getMobileNo())
                .build();
    }

    public static CustomerResponse modelToResponseDto(Customer customer){
        CartResponse cartResponse = CartMapper.modelToResponseDto(customer.getCart());

        return CustomerResponse.builder()
                .name(customer.getName())
                .address(customer.getAddress())
                .mobileNo(customer.getMobileNo())
                .cartResponse(cartResponse)
                .build();
    }

}
