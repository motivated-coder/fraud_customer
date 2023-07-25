package com.skd.customer.service;

import com.skd.customer.dto.CustomerProfileRequest;
import com.skd.customer.entity.Customer;
import com.skd.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer registerCustomer(CustomerProfileRequest customerProfileRequest){
        Customer customer = Customer.builder()
                .firstName(customerProfileRequest.getFirstName())
                .lastName(customerProfileRequest.getLastName())
                .email(customerProfileRequest.getEmail())
                .build();

        customerRepository.saveAndFlush(customer);

        //todo
        //check if customer is fraud
        //make call to fraud service

        return customer;
    }
}
