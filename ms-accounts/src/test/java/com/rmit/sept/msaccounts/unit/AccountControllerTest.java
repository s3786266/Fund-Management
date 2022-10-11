package com.rmit.sept.msaccounts.unit;

import com.rmit.sept.msaccounts.controller.AccountController;
import com.rmit.sept.msaccounts.exception.AccountNumberException;
import com.rmit.sept.msaccounts.model.AccountDto;
import com.rmit.sept.msaccounts.model.AccountType;
import com.rmit.sept.msaccounts.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    private HttpHeaders httpHeaders;


    @BeforeEach
    public void setUp() {
        httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "APPLICATION/JSON");
    }

    @Test
    public void postAccountRequestBody_validRequest_returnCreated() {
        AccountDto accountDetails = new AccountDto();
        accountDetails.setAccountHolderId("01");
        accountDetails.setAccountName("Daniel");
        accountDetails.setBalance("23");
        accountDetails.setAccountType(AccountType.SAVING);

        ResponseEntity<AccountDto> response = accountController.registerNewAccount(accountDetails);
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        verify(accountService, times(1)).saveNewAccountDetails(accountDetails);
    }

    @Test
    public void getAccountDetailsByAccNo_validRequest_returnSuccess() {

        AccountDto accountDetails = new AccountDto();
        accountDetails.setAccountNumber(11);
        accountDetails.setAccountHolderId("01");
        accountDetails.setAccountName("Daniel");
        accountDetails.setBalance("23");
        accountDetails.setAccountType(AccountType.SAVING);

        when(accountService.getAccountDetailsByAccNo(any())).thenReturn(Optional.of(accountDetails));

        Optional<AccountDto> response = accountService.getAccountDetailsByAccNo(any());
        assertThat(response.get().getAccountHolderId(), is("01"));
        assertThat(response.get().getAccountName(), is("Daniel"));
    }
}
