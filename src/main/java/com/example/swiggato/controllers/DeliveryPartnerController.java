package com.example.swiggato.controllers;

import com.example.swiggato.dtos.request.DeliveryPartnerRequest;
import com.example.swiggato.dtos.response.PartnerResponse;
import com.example.swiggato.services.DeliveryPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partner")
public class DeliveryPartnerController {
    final DeliveryPartnerService deliveryPartnerService;

    @Autowired
    public DeliveryPartnerController(DeliveryPartnerService deliveryPartnerService) {
        this.deliveryPartnerService = deliveryPartnerService;
    }

    @PostMapping("/add")
    public ResponseEntity addDeliveryPartner(@RequestBody DeliveryPartnerRequest deliveryPartnerRequest){
        String message = deliveryPartnerService.addDeliveryPartner(deliveryPartnerRequest);
        return new ResponseEntity(message, HttpStatus.CREATED);
    }

    // give deliveryPartner with the  highest number of deliveries
    @GetMapping("/withHighestOrders")
    public ResponseEntity partnerWithHighestOrders(){
        PartnerResponse partnerResponse = deliveryPartnerService.partnerWithHighestOrders();
        return new ResponseEntity(partnerResponse, HttpStatus.FOUND);
    }

    // send email to all the partners who have done less than 10 deliveries
    @GetMapping("/sendEmailToPartnersWithLessDeliveriesInLastXDays/days/{days}/noOfDeliveries/{noOfDeliveries}")
    public ResponseEntity sendEmailToPartnerWithLessDeliveries(@PathVariable int days,@PathVariable int noOfDeliveries){
       List<PartnerResponse> partnerResponses = deliveryPartnerService.sendEmailToPartnerWithLessDeliveries(days,noOfDeliveries);
        return new ResponseEntity(partnerResponses,HttpStatus.FOUND);
    }
}
