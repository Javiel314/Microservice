package com.microservice.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
@Data
public class CustomerDetailsDto {

    @Schema(
            description = "Customer details"
    )
    private CustomerDto customer;

    @Schema(
            description = "Account details of the Customer"
    )
    private AccountDto account;

    @Schema(
            description = "Loan details of the Customer"
    )
    private LoanDto loan;

    @Schema(
            description = "Card details of the Customer"
    )
    private CardDto card;
}
