package com.anilcanipsalali.accountspring.repository;

import com.anilcanipsalali.accountspring.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
