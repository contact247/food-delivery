package com.example.swiggato.dtos.response;


import com.example.swiggato.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PartnerResponse {
    String name;

    String mobileNo;

    Gender gender;

    int noOfOrders;

}
