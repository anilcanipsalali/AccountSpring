package com.anilcanipsalali.accountspring.service;

import com.anilcanipsalali.accountspring.dto.AccountDto;
import com.anilcanipsalali.accountspring.dto.CreateAccountRequest;
import com.anilcanipsalali.accountspring.dto.converter.AccountDtoConverter;
import com.anilcanipsalali.accountspring.exception.AccountNotFoundException;
import com.anilcanipsalali.accountspring.exception.CustomerNotFoundException;
import com.anilcanipsalali.accountspring.model.Account;
import com.anilcanipsalali.accountspring.model.Customer;
import com.anilcanipsalali.accountspring.model.Transaction;
import com.anilcanipsalali.accountspring.repository.AccountRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountDtoConverter converter;
    private final Clock clock;

    public AccountService(AccountRepository accountRepository,
                          CustomerService customerService,
                          AccountDtoConverter converter, Clock clock) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.converter = converter;
        this.clock = clock;
    }

    public AccountDto createAccount(CreateAccountRequest createAccountRequest) {
        Customer customer = customerService.findCustomerById(createAccountRequest.getCustomerId());

        Account account = new Account(
                customer,
                createAccountRequest.getInitialCredit(),
                getLocalDateTimeNow());

        if (createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) {
            Transaction transaction = new Transaction(
                    createAccountRequest.getInitialCredit(),
                    getLocalDateTimeNow(),
                    account);

            account.getTransaction().add(transaction);
        }
        return converter.convertToAccountDto(accountRepository.save(account));
    }

    protected Account findAccountById(String id) {
        return accountRepository.findById(id)
                .orElseThrow(
                        () -> new AccountNotFoundException("Account could not find by id: " + id));
    }

    public AccountDto getAccountById(String accountId) {
        return converter.convertToAccountDto(findAccountById(accountId));
    }
    public String deleteAccount(String accountId) {
        accountRepository.deleteById((Objects.requireNonNull(findAccountById(accountId).getId())));
        return "Account with ID: " + accountId + " deleted!" ;
    }
    private LocalDateTime getLocalDateTimeNow() {
        Instant instant = clock.instant();
        return LocalDateTime.ofInstant(
                instant,
                Clock.systemDefaultZone().getZone());
    }
}
