package com.microservice.accounts.dto;

public record AccountsMsgDto(Long AccountNumber, String name, String email, String mobileNumber) {
}
