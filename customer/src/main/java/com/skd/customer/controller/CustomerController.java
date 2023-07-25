package com.skd.customer.controller;

import com.skd.customer.dto.CustomerProfileRequest;
import com.skd.customer.entity.Customer;
import com.skd.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<Customer> register(@RequestBody CustomerProfileRequest customerProfileRequest){
        Customer customer = customerService.registerCustomer(customerProfileRequest);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
