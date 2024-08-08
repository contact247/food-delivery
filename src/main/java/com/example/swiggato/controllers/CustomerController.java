package com.example.swiggato.controllers;

import com.example.swiggato.dtos.request.CustomerRequest;
import com.example.swiggato.dtos.response.CustomerResponse;
import com.example.swiggato.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

//    final CustomerService customerService;
//    @Autowired
//    public CustomerController(CustomerService customerService) {
//        this.customerService = customerService;
//    }


    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse response = customerService.addCustomer(customerRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }


    @GetMapping("/find/mobile/{mobile}")
    public ResponseEntity findByMobile(@PathVariable String mobile){
        try{
            CustomerResponse customerResponse = customerService.findByMobile(mobile);
            return new ResponseEntity(customerResponse,HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // get the customer with most number of orders
    @GetMapping("/find/customerWithMostOrders")
    public ResponseEntity findCustomerWithMostOrders(){
       CustomerResponse customerResponse =  customerService.findCustomerWithMostOrders();
       return new ResponseEntity(customerResponse,HttpStatus.FOUND);
    }

    // get the female customer with least number of orders
    @GetMapping("/find/femaleWithLeastOrder")
    public ResponseEntity findFemaleWithLeastOrder(){
        CustomerResponse customerResponse = customerService.findFemaleWithLeastOrder();
        return new ResponseEntity(customerResponse,HttpStatus.FOUND);
    }
}
