package com.rewards.accountservice.controller;

import com.rewards.accountservice.entity.Account;
import com.rewards.accountservice.service.AccountService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@CrossOrigin("*")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/getAccount/{id}")
    public Account getAccountById(@PathVariable("id") Long id){
        return accountService.getAccountById(id);
    }

    @PostMapping("/saveAccount")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public Account saveAccount(@RequestBody Account account){
        return accountService.saveAccount(account);
    }

    @PutMapping("/updateAccount")
    public Account updateAccount(@RequestBody Account account){
        return accountService.saveAccount(account);
    }
}
