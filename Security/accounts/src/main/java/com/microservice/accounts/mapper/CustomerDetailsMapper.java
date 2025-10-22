package com.microservice.accounts.mapper;

import com.microservice.accounts.dto.AccountDto;
import com.microservice.accounts.dto.CardDto;
import com.microservice.accounts.dto.CustomerDetailsDto;
import com.microservice.accounts.dto.LoanDto;
import com.microservice.accounts.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerDetailsMapper {

    @Mapping(target = "account", source = "accountDto")
    @Mapping(target = "loan", source = "loanDto")
    @Mapping(target = "card", source = "cardDto")
    CustomerDetailsDto mapToCustomerDetailsDto(Customer customer, AccountDto accountDto, LoanDto loanDto, CardDto cardDto);
}
