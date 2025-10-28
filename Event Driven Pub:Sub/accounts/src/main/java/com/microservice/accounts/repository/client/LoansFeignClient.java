package com.microservice.accounts.repository.client;

import com.microservice.accounts.dto.LoanDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name ="loans", fallback = LoansFallback.class)
public interface LoansFeignClient {

       public final String CORRELATION_ID = "sofiBank-correlation-id";

        @GetMapping(value = "api/fetch", consumes = "application/json")
        public ResponseEntity<LoanDto> fetchLoanDetails(@RequestHeader(CORRELATION_ID) String correlationId, @RequestParam String mobileNumber);
}
