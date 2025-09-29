package com.microservice.accounts.repository.client;

import com.microservice.accounts.dto.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClient {

    @GetMapping(value = "api/fetch", consumes = "application/json")
    public ResponseEntity<CardDto> fetchCard (@RequestParam String mobileNumber);
}
