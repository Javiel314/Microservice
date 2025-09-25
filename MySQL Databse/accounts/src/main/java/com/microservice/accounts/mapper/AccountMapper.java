package com.microservice.accounts.mapper;

import com.microservice.accounts.dto.AccountDto;
import com.microservice.accounts.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {

        Account mapToAccount(AccountDto accountDto);
        @Mapping(target = "accountNumber", source = "id")
        Account mapToAccount(AccountDto accountDto, Long id);
        AccountDto mapToAccountDto(Account account);
}
