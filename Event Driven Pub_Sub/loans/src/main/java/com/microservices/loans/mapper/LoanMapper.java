package com.microservices.loans.mapper;

import com.microservices.loans.dto.LoanDto;
import com.microservices.loans.entity.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoanMapper {
    @Mapping(target = "loanId", source = "id")
    Loan mapToLoan(LoanDto loanDto, long id);

    @Mapping(target = "mobileNumber", source ="mobileNumber" )
    @Mapping(target = "loanNumber", source ="loanNumber" )
    @Mapping(target = "loanType", source ="loanType" )
    @Mapping(target = "totalLoan", source ="totalLoan" )
    @Mapping(target = "amountPaid", source ="amountPaid" )
    @Mapping(target = "outstandingAmount", source ="outstandingAmount" )
    Loan mapToLoan (String mobileNumber, String loanNumber, String loanType, int totalLoan, int amountPaid, int outstandingAmount);

    LoanDto mapToLaonDto(Loan loan);
}
