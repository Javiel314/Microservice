package com.microservice.accounts.service;

import com.microservice.accounts.dto.CustomerDto;

public interface AccountService {

    void create(CustomerDto customerDto);
    CustomerDto fetchAccount(String email);
    boolean updateAccount(CustomerDto customerDto);
    boolean deleteAccount(String mobileNumber);
}
