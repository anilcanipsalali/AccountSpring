package com.anilcanipsalali.accountspring.repository;

import com.anilcanipsalali.accountspring.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
