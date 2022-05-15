package com.anilcanipsalali.accountspring.repository;

import com.anilcanipsalali.accountspring.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
