package com.microservice.accounts.service;

import com.microservice.accounts.dto.CustomerDetailsDto;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
