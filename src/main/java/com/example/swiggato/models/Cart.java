package com.example.swiggato.models;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    double cartTotal;

    @OneToOne
    @JoinColumn
    Customer customer;

    @Builder.Default
    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    List<FoodItem> foodItems = new ArrayList<>();
}
