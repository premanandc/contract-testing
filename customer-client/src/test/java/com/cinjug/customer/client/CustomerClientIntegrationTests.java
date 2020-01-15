package com.cinjug.customer.client;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerClientIntegrationTests {
    @Autowired
    private CustomerClient client;

    @Test
    public void shouldReturnAllCustomers() {

        Collection<Customer> customers = client.findAll();

        assertThat(customers).hasSize(3)
                .extracting(Customer::getFirstName, Customer::getLastName, Customer::getEmail)
                .containsExactly(
                        tuple("First1", "Last1", "first1@test.com"),
                        tuple("First2", "Last2", "first2@test.com"),
                        tuple("First3", "Last3", "first3@test.com")
                );
    }

}
