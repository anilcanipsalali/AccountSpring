package com.anilcanipsalali.accountspring.dto.converter;

import com.anilcanipsalali.accountspring.dto.TransactionDto;
import com.anilcanipsalali.accountspring.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionDtoConverter {
    public TransactionDto convert(Transaction from) {
        return new TransactionDto(from.getId(),
                from.getTransactionType(),
                from.getAmount(),
                from.getTransactionDate());
    }
}