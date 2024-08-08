package com.example.swiggato.models;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "order_entity")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String orderId;

    double orderTotal;

    @CreationTimestamp
    Date orderTime;

    @ManyToOne
    @JoinColumn
    Customer customer;

    @ManyToOne
    @JoinColumn
    DeliveryPartner deliveryPartner;

    @ManyToOne
    @JoinColumn
    Restaurant restaurant;

    @Builder.Default
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    List<FoodItem> foodItems = new ArrayList<>();
}
