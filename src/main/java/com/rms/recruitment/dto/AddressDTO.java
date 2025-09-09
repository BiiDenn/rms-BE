package com.rms.recruitment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private String roomNumber;
    private String houseNumber;
    private String zipcode;
    private String street;
    private String description;
}
