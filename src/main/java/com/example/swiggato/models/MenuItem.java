package com.example.swiggato.models;


import com.example.swiggato.enums.MenuCategory;
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
@Table(name = "menu_item")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String dishName;

    @Enumerated(EnumType.STRING)
    MenuCategory category;

    boolean veg;

    boolean available;

    double price;

    @ManyToOne
    @JoinColumn
    Restaurant restaurant;

    @Builder.Default
    @OneToMany(mappedBy = "menuItem" ,orphanRemoval = true,cascade = CascadeType.ALL)
    List<FoodItem> foodItems = new ArrayList<>();
}
