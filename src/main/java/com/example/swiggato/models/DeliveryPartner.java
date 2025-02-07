package com.example.swiggato.models;

import com.example.swiggato.enums.Gender;
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
@Table(name = "delivery_partner")
public class DeliveryPartner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;


    @Column(unique = true,nullable = false)
    @Size(min = 10,max = 10)
    String mobileNo;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @Builder.Default
    @OneToMany(mappedBy = "deliveryPartner", cascade = CascadeType.ALL)
    List<OrderEntity> orders = new ArrayList<>();
}
