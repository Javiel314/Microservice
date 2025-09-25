package com.microservices.loans.service;

import com.microservices.loans.dto.LoanDto;

public interface LoanService {
    void createLoan(String mobileNumber);
    LoanDto fetchLoan(String mobileNumber);
    boolean update(LoanDto loanDto);
    boolean deleteLoan(String mobileNumber);
}
