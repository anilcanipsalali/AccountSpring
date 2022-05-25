package com.anilcanipsalali.accountspring.controller;

import com.anilcanipsalali.accountspring.dto.AccountDto;
import com.anilcanipsalali.accountspring.dto.CreateAccountRequest;
import com.anilcanipsalali.accountspring.dto.CustomerDto;
import com.anilcanipsalali.accountspring.service.AccountService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/v1/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody CreateAccountRequest request) {
        return ResponseEntity.ok(accountService.createAccount(request));
    }
    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable String accountId) {
        return ResponseEntity.ok(accountService.getAccountById(accountId));
    }
    @DeleteMapping(value ="/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable("accountId") String accountId) {
        return ResponseEntity.ok(accountService.deleteAccount(accountId));
    }
}
