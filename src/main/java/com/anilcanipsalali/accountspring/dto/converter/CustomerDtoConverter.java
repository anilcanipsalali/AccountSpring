package com.anilcanipsalali.accountspring.dto.converter;

import com.anilcanipsalali.accountspring.dto.AccountCustomerDto;
import com.anilcanipsalali.accountspring.dto.CustomerDto;
import com.anilcanipsalali.accountspring.model.Customer;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomerDtoConverter {
    private final CustomerAccountDtoConverter customerAccountDtoConverter;

    public CustomerDtoConverter(CustomerAccountDtoConverter converter) {
        this.customerAccountDtoConverter = converter;
    }

    public AccountCustomerDto convertToAccountCustomer(Optional<Customer> from) {
        return from.map(f -> new AccountCustomerDto(f.getId(), f.getName(), f.getSurname())).orElse(null);
    }

    public CustomerDto convertToCustomerDto(Customer from) {
        return new CustomerDto(
                from.getId(),
                from.getName(),
                from.getSurname(),
                from.getAccounts()
                        .stream()
                        .map(customerAccountDtoConverter::convert)
                        .collect(Collectors.toSet()));

    }
}
