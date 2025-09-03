package com.microservice.accounts.mapper;

import com.microservice.accounts.dto.AccountDto;
import com.microservice.accounts.dto.CustomerDto;
import com.microservice.accounts.entity.Customer;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
    Customer mapToCustomer(CustomerDto customerDto);
    @Mapping(target = "customerId", source = "id")
    Customer mapToCustomer(CustomerDto customerDto, Long id);
    CustomerDto mapToCustomerDto(Customer customer);
    CustomerDto mapToCustomerDto(Customer customer, AccountDto accountDto);
}
