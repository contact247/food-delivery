package com.example.swiggato.mapper;

import com.example.swiggato.dtos.request.DeliveryPartnerRequest;
import com.example.swiggato.dtos.response.PartnerResponse;
import com.example.swiggato.models.DeliveryPartner;

public class PartnerMapper {
    public static DeliveryPartner requestDtoToModel(DeliveryPartnerRequest deliveryPartnerRequest){
        return DeliveryPartner.builder()
                .gender(deliveryPartnerRequest.getGender())
                .name(deliveryPartnerRequest.getName())
                .mobileNo(deliveryPartnerRequest.getMobileNo())
                .build();
    }

    public static PartnerResponse modelToResponseDto(DeliveryPartner deliveryPartner){
        return PartnerResponse.builder()
                .gender(deliveryPartner.getGender())
                .name(deliveryPartner.getName())
                .mobileNo(deliveryPartner.getMobileNo())
                .noOfOrders(deliveryPartner.getOrders().size())
                .build();
    }
}
