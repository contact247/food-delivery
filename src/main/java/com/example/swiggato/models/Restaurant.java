package com.example.swiggato.models;

import com.example.swiggato.enums.RestaurantCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    String location;

    @Enumerated(EnumType.STRING)
    RestaurantCategory restaurantCategory;

    @Column(unique = true,nullable = false)
    @Size(min = 10,max = 10)
    String contactNumber;

    boolean opened;

    @Builder.Default
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    List<MenuItem> availableMenuItems = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    List<OrderEntity> orders = new ArrayList<>();
}
