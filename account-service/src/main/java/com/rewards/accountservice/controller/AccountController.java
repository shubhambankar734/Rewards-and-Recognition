package com.rewards.accountservice.controller;

import com.rewards.accountservice.entity.Account;
import com.rewards.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/getAccount/{id}")
    public Account getAccountById(@PathVariable("id") Long id){
        return accountService.getAccountById(id);
    }

    @PostMapping("/saveAccount")
    public Account saveAccount(@RequestBody Account account){
        return accountService.saveAccount(account);
    }

    @PutMapping("/updateAccount")
    public Account updateAccount(@RequestBody Account account){
        return accountService.saveAccount(account);
    }
}
