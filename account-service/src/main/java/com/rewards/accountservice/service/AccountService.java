package com.rewards.accountservice.service;

import com.rewards.accountservice.entity.Account;
import com.rewards.accountservice.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account getAccountById(Long id) {
        log.info("Inside getAccountById of Account Service");
        Optional<Account> account = accountRepository.findById(id);
        return account.isPresent() ? account.get() : null;
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }
}
