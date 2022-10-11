package com.rmit.sept.msaccounts.unit;

import com.rmit.sept.msaccounts.exception.AccountNumberException;
import com.rmit.sept.msaccounts.model.AccountDto;
import com.rmit.sept.msaccounts.model.AccountType;
import com.rmit.sept.msaccounts.repository.AccountRepository;
import com.rmit.sept.msaccounts.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Test
    public void getAccountDetailsByAccNo_validRequest_returnSuccess() {

        AccountDto accountDetails = new AccountDto();
        accountDetails.setAccountNumber(11);
        accountDetails.setAccountHolderId("01");
        accountDetails.setAccountName("Daniel");
        accountDetails.setBalance("23");
        accountDetails.setAccountType(AccountType.SAVING);

        when(accountRepository.findById(11)).thenReturn(Optional.of(accountDetails));

        Optional<AccountDto> result = accountService.getAccountDetailsByAccNo(11);

        assertThat(result.get().getAccountHolderId(), is("01"));
        assertThat(result.get().getAccountName(), is("Daniel"));
        verify(accountRepository).findById(11);
    }

    @Test
    public void updateAccountDetails_invalidAccountNo_returnError() throws Exception {

        Integer invalidAccountNo = 11;

        AccountDto accountDetails = new AccountDto();
        accountDetails.setAccountNumber(12);
        accountDetails.setAccountHolderId("01");
        accountDetails.setAccountName("Daniel");
        accountDetails.setBalance("23");
        accountDetails.setAccountType(AccountType.SAVING);

        when(accountRepository.findById(12)).thenReturn(Optional.of(accountDetails));

        assertThatThrownBy(() -> accountService.updateExistingAccountDetails(invalidAccountNo,accountDetails))
                .isInstanceOf(AccountNumberException.class)
                .hasMessageContaining("Account Number is not found in the database");
    }
}
