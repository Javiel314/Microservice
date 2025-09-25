package com.microservice.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
@Data
public class AccountDto {

    @NotEmpty(message = "Account number can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Schema(
            description = "Account Number of SofI Bank account", example = "3454433243"
    )
    private Long accountNumber;

    @NotEmpty(message = "Account type can not be a null or empty")
    @Schema(
            description = "Account type of SoFi Bank account", example = "Savings"
    )
    private String accountType;

    @NotEmpty(message = "Branch address can not be a null or empty")
    @Schema(
            description = "SoFi Bank branch address", example = "123 NewYork"
    )
    private String branchAddress;
}
