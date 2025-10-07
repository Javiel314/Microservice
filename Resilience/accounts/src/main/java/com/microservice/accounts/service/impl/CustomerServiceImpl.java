package com.microservice.accounts.service.impl;

import com.microservice.accounts.dto.AccountDto;
import com.microservice.accounts.dto.CardDto;
import com.microservice.accounts.dto.CustomerDetailsDto;
import com.microservice.accounts.dto.LoanDto;
import com.microservice.accounts.entity.Customer;
import com.microservice.accounts.mapper.CustomerDetailsMapper;
import com.microservice.accounts.repository.client.CardsFeignClient;
import com.microservice.accounts.repository.client.LoansFeignClient;
import com.microservice.accounts.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final ServiceSupport serviceSupport;
    private final LoansFeignClient loansFeignClient;
    private final CardsFeignClient cardsFeignClient;
    private final CustomerDetailsMapper customerDetailsMapper;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String correlationId, String mobileNumber) {

        Customer fetchedCustomer = this.serviceSupport.getCustomer(mobileNumber);
        log.error(fetchedCustomer.toString());

        AccountDto customerAccountDto = this.serviceSupport.getCustomersAccountDto(fetchedCustomer.getCustomerId());
        log.error(customerAccountDto.toString());
        LoanDto loanDto = this.fetchLoansDetails(correlationId, mobileNumber);

        CardDto cardDto = this.fetchCardsDetails(correlationId, mobileNumber);


        return this.customerDetailsMapper.mapToCustomerDetailsDto(fetchedCustomer,customerAccountDto,loanDto,cardDto);
    }

    private LoanDto fetchLoansDetails(String correlationId, String mobileNumber) {
        ResponseEntity<LoanDto> loanDtoResponseEntity= loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
        return loanDtoResponseEntity.getBody();
    }

    private CardDto fetchCardsDetails(String correlationId, String mobileNumber) {
        ResponseEntity<CardDto> cardDtoResponseEntity= cardsFeignClient.fetchCard(correlationId, mobileNumber);

        return cardDtoResponseEntity.getBody();
    }
}
