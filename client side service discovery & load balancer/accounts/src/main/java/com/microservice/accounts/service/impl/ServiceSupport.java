package com.microservice.accounts.service.impl;

import com.microservice.accounts.dto.AccountDto;
import com.microservice.accounts.entity.Account;
import com.microservice.accounts.entity.Customer;
import com.microservice.accounts.exception.ResourceNotFoundException;
import com.microservice.accounts.mapper.AccountMapper;
import com.microservice.accounts.repository.AccountRepository;
import com.microservice.accounts.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ServiceSupport {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;

    protected Customer getCustomer(String mobileNumber) {
        return customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
    }

    protected AccountDto getCustomersAccountDto (Long customerId) {
        Account account = accountRepository.findByCustomerId(customerId).orElseThrow(
                ()-> new ResourceNotFoundException("Account", "customerId", customerId.toString()));

        return accountMapper.mapToAccountDto(account);
    }
}
