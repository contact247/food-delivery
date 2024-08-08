package com.example.swiggato.services;

import com.example.swiggato.dtos.request.CustomerRequest;
import com.example.swiggato.dtos.response.CustomerResponse;
import com.example.swiggato.enums.Gender;
import com.example.swiggato.exceptions.CustomerNotFoundException;
import com.example.swiggato.mapper.CustomerMapper;
import com.example.swiggato.models.Cart;
import com.example.swiggato.models.Customer;
import com.example.swiggato.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;


    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        Customer customer = CustomerMapper.requestDtoToModel(customerRequest);
        Cart cart = Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .build();

        customer.setCart(cart);

        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.modelToResponseDto(savedCustomer);
    }

    public CustomerResponse findByMobile(String mobile) {
        Customer customer = customerRepository.findByMobileNo(mobile);
        if(customer==null){
            throw new CustomerNotFoundException("Invalid Mobile Number!!");
        }
        return CustomerMapper.modelToResponseDto(customer);
    }

    public CustomerResponse findCustomerWithMostOrders() {
        List<Customer> customers = customerRepository.findAll();
        Customer ans = null;
        for(Customer x: customers){
            if(ans==null || ans.getOrders().size()<x.getOrders().size()){
                ans=x;
            }
        }

        return CustomerMapper.modelToResponseDto(ans);
    }

    public CustomerResponse findFemaleWithLeastOrder() {
        List<Customer> customers = customerRepository.findByGender(Gender.FEMALE);
        if(customers.isEmpty()){
            return null;
        }
        Customer customer = null;

        for(Customer x: customers){
            if(customer == null || x.getOrders().size()<customer.getOrders().size()){
                customer = x;
            }
        }
        return CustomerMapper.modelToResponseDto(customer);
    }
}
