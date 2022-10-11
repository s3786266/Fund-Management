package com.rmit.sept.msaccounts.controller;

import com.rmit.sept.msaccounts.model.AccountDto;
import com.rmit.sept.msaccounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(path="/account/register")
    public ResponseEntity<AccountDto> registerNewAccount(@RequestBody AccountDto accountDetails) {
        return new ResponseEntity<>(accountService.saveNewAccountDetails(accountDetails), HttpStatus.CREATED);
    }

    @GetMapping(path="/accounts/account")
    public List<AccountDto> getAllAccountDetails() {
        return accountService.getAllAccountDetails();
    }

    @GetMapping(path="/account/{accountNumber}")
    public Optional<AccountDto> getAccountDetailsByAccNo(@PathVariable Integer accountNumber) {
        return accountService.getAccountDetailsByAccNo(accountNumber);
    }

    @PutMapping(path="/account/{accountNumber}")
    public ResponseEntity<AccountDto> updateExistingAccountDetails(@PathVariable Integer accountNumber, @RequestBody AccountDto newAccountDetails) throws Exception {
        return new ResponseEntity<>(accountService.updateExistingAccountDetails(accountNumber, newAccountDetails), HttpStatus.OK);
    }

    @DeleteMapping(path="/account/{accountNumber}")
    public void deleteExistingAccountDetails(@PathVariable Integer accountNumber) {
        accountService.deleteExistingAccountDetails(accountNumber);
    }
}
