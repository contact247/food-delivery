package com.example.swiggato.dtos.response;


import com.example.swiggato.models.Customer;
import com.example.swiggato.models.DeliveryPartner;
import com.example.swiggato.models.FoodItem;
import com.example.swiggato.models.Restaurant;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderResponse {

    String orderId;

    double orderTotal;

    Date orderTime;

    String customerName;

    String customerMobile;

    String deliveryPartnerName;

    String deliveryPartnerMobile;

    String restaurantName;

    List<FoodResponse> foodResponses;
}
