package com.microservice.accounts.repository.client;

import com.microservice.accounts.dto.CardDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient {

    @Override
    public ResponseEntity<CardDto> fetchCard(String correlationId, String mobileNumber) {
        return null;
    }
}
