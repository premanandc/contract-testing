package com.cinjug.customer.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(CustomerClient.class)
@RunWith(SpringRunner.class)
public class CustomerClientMockServerTests {

  private static final ClassPathResource customers = new ClassPathResource("/customers.json");
  private static final ClassPathResource customerById = new ClassPathResource("/customer-by-id.json");
  @Autowired
  private CustomerClient client;

  @Autowired
  private MockRestServiceServer server;

  @Test
  public void shouldReturnAllCustomers() {
    server.expect(requestTo("http://localhost:8080/customers"))
        .andExpect(method(HttpMethod.GET))
        .andRespond(withSuccess(customers, APPLICATION_JSON_UTF8));

    Collection<Customer> customers = client.findAll();

    assertThat(customers).hasSize(2);
  }

  @Test
  public void shouldReturnSingleCustomer() {
    server.expect(requestTo("http://localhost:8080/customers/1"))
        .andExpect(method(HttpMethod.GET))
        .andRespond(withSuccess(customerById, APPLICATION_JSON_UTF8));

    Customer customer = client.findById(1);

    assertThat(customer).isNotNull();
    assertThat(customer.getId()).isEqualTo(1L);
    assertThat(customer.getFirstName()).isEqualTo("Joe");
    assertThat(customer.getLastName()).isEqualTo("Foo");
    assertThat(customer.getEmail()).isEqualTo("joe@test.com");
  }
}
