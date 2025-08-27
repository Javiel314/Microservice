package com.microservices.loans.mapper;

import com.microservices.loans.dto.LoanDto;
import com.microservices.loans.entity.Loan;
import org.mapstruct.Mapper;

@Mapper
public interface LoanMapper {

    Loan mapToLoan(Loan loan);
    LoanDto mapToLaonDto(Loan loan);
}
