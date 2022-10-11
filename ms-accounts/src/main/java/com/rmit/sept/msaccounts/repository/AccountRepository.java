package com.rmit.sept.msaccounts.repository;

import com.rmit.sept.msaccounts.model.AccountDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository  extends CrudRepository<AccountDto, Integer> {
}
