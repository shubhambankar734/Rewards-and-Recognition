package com.rewards.account.service;

import com.rewards.account.entity.Account;
import com.rewards.account.exception.CustomException;
import com.rewards.account.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account getAccountById(Long id) throws CustomException {
        log.info("Inside getAccountById of Account Service");
        return accountRepository.findById(id).orElseThrow(() -> new CustomException("Account does not exist."));
    }

    public Account saveAccount(Account account) throws CustomException {
        Account existingAccount = accountRepository.findByAccountCode(account.getAccountCode());
        if(null != existingAccount)
            throw new CustomException("Account code already exist.");
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
