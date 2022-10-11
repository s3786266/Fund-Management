package com.rmit.sept.msaccounts.service;

import com.rmit.sept.msaccounts.exception.AccountNumberException;
import com.rmit.sept.msaccounts.model.AccountDto;
import com.rmit.sept.msaccounts.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountDto saveNewAccountDetails(AccountDto accountDetails) {
        return accountRepository.save(accountDetails);
    }

    public List<AccountDto> getAllAccountDetails() {
        return (List<AccountDto>) accountRepository.findAll();
    }

    public Optional<AccountDto> getAccountDetailsByAccNo(Integer accountNumber) {
        return accountRepository.findById(accountNumber);
    }

    public AccountDto updateExistingAccountDetails(Integer accountNumber, AccountDto newAccountDetails) throws Exception {

        Optional<AccountDto> accountDetails = accountRepository.findById(accountNumber);

        if (accountDetails.isPresent()) {
            accountDetails.get().setAccountHolderId(newAccountDetails.getAccountHolderId());
            accountDetails.get().setAccountName(newAccountDetails.getAccountName());
            accountDetails.get().setBalance(newAccountDetails.getBalance());
            accountDetails.get().setDate(newAccountDetails.getDate());
            accountDetails.get().setAccountType(newAccountDetails.getAccountType());

            log.info("Fetching old user details={} from database", accountDetails);
            log.info("Updating new user details={}", newAccountDetails);

            return accountRepository.save(accountDetails.get());
        }
        throw new AccountNumberException("Account Number is not found in the database", accountNumber);
    }

    public void deleteExistingAccountDetails(Integer accountNumber) {
        accountRepository.deleteById(accountNumber);
    }
}