package com.example.swiggato.services;

import com.example.swiggato.dtos.request.FoodRequest;
import com.example.swiggato.dtos.response.CartStatusResponse;
import com.example.swiggato.dtos.response.FoodResponse;
import com.example.swiggato.exceptions.CustomerNotFoundException;
import com.example.swiggato.exceptions.MenuItemNotFound;
import com.example.swiggato.mapper.FoodMapper;
import com.example.swiggato.models.*;
import com.example.swiggato.repositories.CartRepository;
import com.example.swiggato.repositories.CustomerRepository;
import com.example.swiggato.repositories.FoodItemRepository;
import com.example.swiggato.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    final CartRepository cartRepository;
    final CustomerRepository customerRepository;

    final MenuRepository menuRepository;

    final FoodItemRepository foodItemRepository;
    @Autowired
    public CartService(CartRepository cartRepository, CustomerRepository customerRepository, MenuRepository menuRepository, FoodItemRepository foodItemRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.menuRepository = menuRepository;
        this.foodItemRepository = foodItemRepository;
    }

    public CartStatusResponse addFoodItemToCart(FoodRequest foodRequest) {
        Customer customer = customerRepository.findByMobileNo(foodRequest.getCustomerMobile());
        if(customer==null){
            throw new CustomerNotFoundException("Customer doesn't exists!!");
        }

        Optional<MenuItem> optionalMenuItem = menuRepository.findById(foodRequest.getMenuItemId());

        if(optionalMenuItem.isEmpty()){
            throw new MenuItemNotFound("MenuItem not found!!");
        }

        MenuItem menuItem = optionalMenuItem.get();
        if(!menuItem.getRestaurant().isOpened() || !menuItem.isAvailable()){
            throw new MenuItemNotFound("MenuItem not available!!");
        }

        Cart cart = customer.getCart();

        if(cart.getFoodItems().size()!=0){
            Restaurant currRest = cart.getFoodItems().get(0).getMenuItem().getRestaurant();
            Restaurant newRest = menuItem.getRestaurant();

            if(!currRest.equals(newRest)){

                List<FoodItem> foodItems = cart.getFoodItems();
                cart.setFoodItems(new ArrayList<>());

                for(FoodItem x :foodItems){
                    MenuItem temp = x.getMenuItem();
                    temp.getFoodItems().remove(x);
                    menuRepository.save(temp);
                }

                cart.setCartTotal(0);
                cartRepository.save(cart);
            }

        }

        boolean alreadyExists=false;
        FoodItem savedFoodItem=null;

        if(cart.getFoodItems().size()!=0){
            for(FoodItem foodItem : cart.getFoodItems()){
                if(foodItem.getMenuItem().getId()==menuItem.getId()){
                    int curr = foodItem.getRequiredQuantity();
                    foodItem.setRequiredQuantity(curr + foodRequest.getRequiredQuantity());
                    foodItem.setTotalCost(foodItem.getRequiredQuantity()*foodItem.getMenuItem().getPrice());
                    alreadyExists=true;
                    savedFoodItem=foodItem;
                    break;
                }
            }
        }

        if(!alreadyExists) {
            FoodItem foodItem = FoodItem.builder()
            .cart(customer.getCart())
            .menuItem(menuItem)
            .requiredQuantity(foodRequest.getRequiredQuantity())
            .totalCost(foodRequest.getRequiredQuantity() * menuItem.getPrice())
            .build();

            savedFoodItem = foodItemRepository.save(foodItem);
            cart.getFoodItems().add(savedFoodItem);
            menuItem.getFoodItems().add(savedFoodItem);
        }

    double cartTotal = 0;

    for(FoodItem food: cart.getFoodItems()){
        cartTotal += food.getTotalCost();
    }

    cart.setCartTotal(cartTotal);
    Cart savedCart = cartRepository.save(cart);
    MenuItem savedMenuItem = menuRepository.save(menuItem);

    List<FoodResponse> foodResponses = new ArrayList<>();

    for(FoodItem food:savedCart.getFoodItems()){
        foodResponses.add(FoodMapper.modelToResponseDto(food));
    }

    return CartStatusResponse.builder()
            .cartTotal(cartTotal)
            .customerAddress(savedCart.getCustomer().getAddress())
            .customerMobile(savedCart.getCustomer().getMobileNo())
            .customerName(savedCart.getCustomer().getName())
            .foodResponses(foodResponses)
            .restaurantName(savedMenuItem.getRestaurant().getName())
            .build();
    }
}
