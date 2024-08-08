package com.example.swiggato.dtos.request;


import com.example.swiggato.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeliveryPartnerRequest {
    String name;

    String mobileNo;

    Gender gender;
}
