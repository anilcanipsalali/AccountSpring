package com.anilcanipsalali.accountspring.controller;

import com.anilcanipsalali.accountspring.dto.CustomerDto;
import com.anilcanipsalali.accountspring.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable String customerId) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomer());
    }
    @DeleteMapping(value ="/{customerId}")
    public ResponseEntity<?> deleteAccount(@Valid @PathVariable("customerId") String customerId) {
        return ResponseEntity.ok(customerService.deleteCustomer(customerId));
    }
}
