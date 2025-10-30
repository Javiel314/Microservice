package com.microservice.accounts.functions;

import com.microservice.accounts.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class AccountFunctions {

    @Bean
    public Consumer<Long> updateDCommunication(AccountService accountService) {
        return accountNumber -> {
            log.info("Updating Communication status for the account number: {}", accountNumber);
            accountService.updateCommunicationStatus(accountNumber);
        };
    }
}
