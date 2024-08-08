package com.example.swiggato.services;

import com.example.swiggato.dtos.request.DeliveryPartnerRequest;
import com.example.swiggato.dtos.response.PartnerResponse;
import com.example.swiggato.mapper.PartnerMapper;
import com.example.swiggato.models.DeliveryPartner;
import com.example.swiggato.repositories.DeliveryPartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryPartnerService {
    final DeliveryPartnerRepository deliveryPartnerRepository;

    //final JavaMailSender javaMailSender;

    @Autowired
    public DeliveryPartnerService(DeliveryPartnerRepository deliveryPartnerRepository) {
        this.deliveryPartnerRepository = deliveryPartnerRepository;
    }

    public String addDeliveryPartner(DeliveryPartnerRequest deliveryPartnerRequest) {
        DeliveryPartner deliveryPartner = PartnerMapper.requestDtoToModel(deliveryPartnerRequest);

        deliveryPartnerRepository.save(deliveryPartner);
        return "You have been successfully registered!!";
    }

    public PartnerResponse partnerWithHighestOrders() {
        List<DeliveryPartner> deliveryPartners = deliveryPartnerRepository.findAll();
        DeliveryPartner ans = null;

        for(DeliveryPartner deliveryPartner : deliveryPartners){
            if(ans==null || deliveryPartner.getOrders().size()>ans.getOrders().size()){
                ans = deliveryPartner;
            }
        }

        return PartnerMapper.modelToResponseDto(ans);
    }

    public List<PartnerResponse> sendEmailToPartnerWithLessDeliveries(int days ,int noOfDelivery) {

        List<DeliveryPartner> deliveryPartners = deliveryPartnerRepository.sendEmailToPartnerWithLessDeliveries(days,noOfDelivery);
//
//        for(DeliveryPartner p : deliveryPartners){
//            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//            simpleMailMessage.setFrom("");
//            simpleMailMessage.setSubject("Below Threshold Number Of Deliveries!");
//            simpleMailMessage.setTo("");
//
//            String text = "Hello "+ p.getName()+".\n\nYou have delivered less than " + noOfDelivery +
//                          " deliveries in the last "+ days + " days. Kindly deliver minimum required number"+
//                          "of orders for being"+" eligible for incentives.\n\n"+
//                          "Hurry up!! Try to hit the target as soon as possible!!";
//
//            simpleMailMessage.setText(text);
//
//            javaMailSender.send(simpleMailMessage);
//
//        }
//

        return deliveryPartners.stream().map(deliveryPartner -> PartnerMapper.modelToResponseDto(deliveryPartner)).collect(Collectors.toList());
    }
}
