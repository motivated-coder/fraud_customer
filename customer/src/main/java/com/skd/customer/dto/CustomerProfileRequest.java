package com.skd.customer.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerProfileRequest {
    private String firstName;
    private String lastName;
    private String email;
}
