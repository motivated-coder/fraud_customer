package com.skd.customer.service;

import com.skd.customer.dto.CustomerProfileRequest;
import com.skd.customer.entity.Customer;
import com.skd.customer.repository.CustomerRepository;
import com.skd.fraud.FraudCheckResponse;
import com.skd.fraud.FraudClient;
import com.skd.notification.NotificationClient;
import com.skd.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public Customer registerCustomer(CustomerProfileRequest customerProfileRequest) {
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
        FraudCheckResponse response = fraudClient.isFraudster(customer.getId());

        if (response.getIsFraudster())
            throw new IllegalStateException("Fraud Customer");

        notificationClient.sendNotification(
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        String.format("Hi %s, welcome to Notification Service...",
                                customer.getFirstName())
                )
        );
        return customer;
    }
}
