package com.example.swiggato.services;

import com.example.swiggato.dtos.response.OrderResponse;
import com.example.swiggato.exceptions.CustomerNotFoundException;
import com.example.swiggato.exceptions.EmptyCartException;
import com.example.swiggato.mapper.OrderMapper;
import com.example.swiggato.models.*;
import com.example.swiggato.repositories.CustomerRepository;
import com.example.swiggato.repositories.DeliveryPartnerRepository;
import com.example.swiggato.repositories.OrderRepository;
import com.example.swiggato.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrderService {
    final OrderRepository orderRepository;

   // final JavaMailSender javaMailSender;

    final DeliveryPartnerRepository deliveryPartnerRepository;

    final CustomerRepository customerRepository;

    final RestaurantRepository restaurantRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository, DeliveryPartnerRepository deliveryPartnerRepository, CustomerRepository customerRepository, RestaurantRepository restaurantRepository) {
        this.orderRepository = orderRepository;
        this.deliveryPartnerRepository = deliveryPartnerRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public OrderResponse placeOrder(String customerMobile) {

        Customer customer = customerRepository.findByMobileNo(customerMobile);
        if(customer == null){
            throw new CustomerNotFoundException("Mobile Number is invalid!!");
        }

        Cart cart = customer.getCart();

        if(cart.getFoodItems().size()==0){
            throw new EmptyCartException("Cart is empty!!");
        }


        DeliveryPartner deliveryPartner = deliveryPartnerRepository.findRandomDeliveryPartner();
        Restaurant restaurant = cart.getFoodItems().get(0).getMenuItem().getRestaurant();

        OrderEntity order = OrderMapper.prepareOrder(cart);


        order.setCustomer(customer);
        order.setDeliveryPartner(deliveryPartner);
        order.setRestaurant(restaurant);

        OrderEntity savedOrder = orderRepository.save(order);


        savedOrder.setFoodItems(cart.getFoodItems().stream().map(foodItem -> foodItem).collect(Collectors.toList()));


        customer.getOrders().add(savedOrder);
        deliveryPartner.getOrders().add(savedOrder);
        restaurant.getOrders().add(savedOrder);

        for(FoodItem foodItem: cart.getFoodItems()){
            foodItem.setCart(null);
            foodItem.setOrder(savedOrder);
        }

        clearCart(cart);

        customerRepository.save(customer);
        deliveryPartnerRepository.save(deliveryPartner);
        restaurantRepository.save(restaurant);


//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//
//        simpleMailMessage.setFrom();
//        simpleMailMessage.setTo(customer.getEmail());
//        simpleMailMessage.setSubject("Order Placed. Arriving Soon");
//
//        String text = "Hello "+customer.getName()+"\n"+
//                      "\nYour Order with Id: "+savedOrder.getOrderId()+" has been placed.\n"+
//                      "\nRestaurant: "+restaurant.getName()+"\n"+"\nFollowing are the fooditems: \n";
//        String fi="";
//        for(FoodItem x : savedOrder.getFoodItems()){
//           fi+= x.getMenuItem().getDishName()+"/"+x.getMenuItem().getPrice()+"  X  "+x.getRequiredQuantity()+" --------> "+x.getTotalCost()+"\n";
//        }
//        fi+="\n                                            Total: "+savedOrder.getOrderTotal();
//
//        text+=fi;
//
//        simpleMailMessage.setText(text);
//
//        javaMailSender.send(simpleMailMessage);

        return OrderMapper.modelToResponseDto(savedOrder);
    }

    private void clearCart(Cart cart) {
        cart.setCartTotal(0);
        cart.getFoodItems().clear();
    }
}
