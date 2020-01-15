package com.cinjug.customer.client;

import org.assertj.core.groups.Tuple;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(CustomerClient.class)
@RunWith(SpringRunner.class)
public class CustomerClientMockServerTests {

    private static final ClassPathResource customers = new ClassPathResource("/customers.json");
    @Autowired
    private CustomerClient client;

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void shouldReturnAllCustomers() {
        server.expect(requestTo("http://localhost:8080/customers"))
                .andExpect(method(GET))
                .andRespond(withSuccess(customers, APPLICATION_JSON_UTF8));

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
