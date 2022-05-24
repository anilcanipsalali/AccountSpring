package com.anilcanipsalali.accountspring.controller;

import com.anilcanipsalali.accountspring.IntegrationTestSupport;
import com.anilcanipsalali.accountspring.dto.CustomerDto;
import com.anilcanipsalali.accountspring.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "server-port=0",
        "command.line.runner.enabled=false"
})
@RunWith(SpringRunner.class)
@DirtiesContext
class CustomerControllerTest extends IntegrationTestSupport {

    @Test
    public void testGetCustomerById_whenCustomerIdExists_shouldReturnCustomerDto() throws Exception {

        Customer customer = customerRepository.save(generateCustomer());
        accountService.createAccount(generateCreateAccountRequest(customer.getId(), 100));

        CustomerDto expected = converter.convertToCustomerDto(
                customerRepository.getById(
                        Objects.requireNonNull(customer.getId())));

        this.mockMvc.perform(get(CUSTOMER_API_ENDPOINT + customer.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(expected), false))
                .andReturn();
    }

    @Test
    public void testGetCustomerById_whenCustomerIdDoesNotExist_shouldReturnHttpNotFound() throws Exception {
        this.mockMvc.perform(get(CUSTOMER_API_ENDPOINT + "non-exists-customer"))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}
