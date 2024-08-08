package com.example.swiggato.dtos.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartResponse {
    double cartTotal;

    List<MenuResponse> menuResponses;
}
