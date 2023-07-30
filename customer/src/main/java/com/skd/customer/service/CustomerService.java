package com.skd.customer.service;

import com.skd.customer.dto.CustomerProfileRequest;
import com.skd.customer.dto.FraudCheckResponse;
import com.skd.customer.entity.Customer;
import com.skd.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public Customer registerCustomer(CustomerProfileRequest customerProfileRequest){
        Customer customer = Customer.builder()
                .firstName(customerProfileRequest.getFirstName())
                .lastName(customerProfileRequest.getLastName())
                .email(customerProfileRequest.getEmail())
                .build();

        customerRepository.saveAndFlush(customer);

        //todo
        //check if email is valid
        //check if email is not taken

        //check if customer is fraud
        FraudCheckResponse response = restTemplate.getForObject("http://FRAUD/api/v1/fraud-check/{customerId}", FraudCheckResponse.class,customer.getId());

        if(response.getIsFraudster())
            throw new IllegalStateException("Fraud Customer");
        return customer;
    }
}
