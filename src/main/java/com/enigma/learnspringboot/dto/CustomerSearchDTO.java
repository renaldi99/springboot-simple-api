package com.enigma.learnspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter@Setter
public class CustomerSearchDTO {
    private String searchCustomerFirstName;
    private String searchCustomerLastName;
    private Date searchCustomerDateOfBirth;
}