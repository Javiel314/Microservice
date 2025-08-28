package com.microservices.loans.service.impl;

import com.microservices.loans.constant.LoanConstants;
import com.microservices.loans.dto.LoanDto;
import com.microservices.loans.entity.Loan;
import com.microservices.loans.exception.LoanAlreadyExistsException;
import com.microservices.loans.exception.ResourceNotFoundException;
import com.microservices.loans.mapper.LoanMapper;
import com.microservices.loans.repository.LoanRepository;
import com.microservices.loans.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;

    @Override
    public void createLoan(String mobileNumber) {

        checkExistingLoan(mobileNumber);
        Loan newLoan = this.createNewLoan(mobileNumber);
        this.save(newLoan);
    }

    @Override
    public LoanDto fetchLoan(String mobileNumber) {
        return loanMapper.mapToLaonDto(this.getLoan(mobileNumber));
    }

    @Override
    public boolean update(LoanDto loanDto) {
        Loan loanToUpdate = this.getLoan(loanDto.getMobileNumber());
        this.save(this.loanMapper.mapToLoan(loanDto, loanToUpdate.getLoanId()));

        return true;
    }


    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loan loanToDelete = this.getLoan(mobileNumber);
        this.loanRepository.deleteById(loanToDelete.getLoanId());
        return true;
    }

    private void checkExistingLoan(String mobileNumber){
        Optional<Loan> existingLoan = this.loanRepository.findByMobileNumber(mobileNumber);
        if (existingLoan.isPresent()){
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber"+mobileNumber);
        }
    }

    private Loan getLoan(String mobileNumber){
        return this.loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Loan", "mobileNumber",mobileNumber)
        );
    }

    private void save(Loan loan){
        this.loanRepository.save(loan);
    }

    private Loan createNewLoan(String mobileNumber){
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);

        return this.loanMapper.mapToLoan(
                mobileNumber,
                Long.toString(randomLoanNumber),
                LoanConstants.HOME_LOAN,
                LoanConstants.NEW_LOAN_LIMIT,
                0,
                LoanConstants.NEW_LOAN_LIMIT
        );
    }
}
