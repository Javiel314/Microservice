package com.microservice.accounts.service.impl;

import com.microservice.accounts.constants.AccountConstants;
import com.microservice.accounts.dto.AccountDto;
import com.microservice.accounts.dto.CustomerDto;
import com.microservice.accounts.entity.Account;
import com.microservice.accounts.entity.Customer;
import com.microservice.accounts.exception.CustomerAlreadyExistsException;
import com.microservice.accounts.exception.ResourceNotFoundException;
import com.microservice.accounts.mapper.AccountMapper;
import com.microservice.accounts.mapper.CustomerMapper;
import com.microservice.accounts.repository.AccountRepository;
import com.microservice.accounts.repository.CustomerRepository;
import com.microservice.accounts.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

   private final AccountRepository accountRepository;
   private final CustomerRepository customerRepository;
   private final CustomerMapper customerMapper;
   private final AccountMapper accountMapper;

    @Override
    public void create(CustomerDto customerDto){

        this.checkExisting(customerDto);
        Customer customer = this.mapToCustomer(customerDto);

        Customer createdCustomer = this.save(customer);
        this.save(createNewAccount(createdCustomer));

    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer fetchedCustomer = this.getCustomer(mobileNumber);

       AccountDto customerAccountDto = getCustomersAccountDto(fetchedCustomer.getCustomerId());

        return customerMapper.mapToCustomerDto(fetchedCustomer, customerAccountDto);
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;

        Account accountToUpdate = this.getAccount(customerDto.getAccount().getAccountNumber());

        accountToUpdate = mapToAccount(customerDto.getAccount(), accountToUpdate.getAccountNumber());

        this.save(accountToUpdate);

        this.updateCustomer(customerDto);

        return true;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        boolean isDeleted= false;

        Customer customerToDelete = this.getCustomer(mobileNumber);

        customerRepository.deleteById(customerToDelete.getCustomerId());
        accountRepository.deleteByCustomerId(customerToDelete.getCustomerId());


        return isDeleted;
    }

    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);

        return newAccount;
    }

    private void checkExisting(CustomerDto customerDto) {
        Optional<Customer> existingCustomer = customerRepository.findByMobileNumber(customerDto.getEmail());

        if (existingCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer with email:" + customerDto.getEmail() +  " already exists");
        }
    }

    private Customer mapToCustomer (CustomerDto customerDto) {
        return customerMapper.mapToCustomer(customerDto);
    }

     private Customer mapToCustomer (CustomerDto customerDto, Long Id) {
        return customerMapper.mapToCustomer(customerDto);
    }

    private Account mapToAccount (AccountDto accountDto, Long id){
        return accountMapper.mapToAccount(accountDto, id );
    }

    private Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    private void save(Account account){
        accountRepository.save(account);
    }

    private Customer getCustomer(String mobileNumber) {
        return customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
    }

    private Account getAccount (Long accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).orElseThrow(
                () -> new ResourceNotFoundException("Account", "accountNumber", accountNumber.toString()));
    }

    private AccountDto getCustomersAccountDto (Long customerId) {
      Account account = accountRepository.findByCustomerId(customerId).orElseThrow(
              ()-> new ResourceNotFoundException("Account", "customerId", customerId.toString()));

      return accountMapper.mapToAccountDto(account);
    }

    private void updateCustomer (CustomerDto customerDto) {
        Customer customerToUpdate = getCustomer(customerDto.getEmail());

        customerToUpdate = this.mapToCustomer(customerDto, customerToUpdate.getCustomerId());

        customerToUpdate.setUpdatedBy(customerDto.getName());
        customerToUpdate.setUpdatedAt(LocalDateTime.now());

        this.save(customerToUpdate);
    }



}
