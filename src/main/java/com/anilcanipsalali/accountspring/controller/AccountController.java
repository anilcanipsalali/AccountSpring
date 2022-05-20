package com.anilcanipsalali.accountspring.controller;

import com.anilcanipsalali.accountspring.dto.AccountDto;
import com.anilcanipsalali.accountspring.dto.CreateAccountRequest;

import com.anilcanipsalali.accountspring.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody CreateAccountRequest request) {
        return ResponseEntity.ok(accountService.createAccount(request));
    }
}
