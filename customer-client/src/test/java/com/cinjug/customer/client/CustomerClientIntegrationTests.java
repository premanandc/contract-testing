package com.cinjug.customer.client;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerClientIntegrationTests {
  @Autowired
  private CustomerClient client;

  @Test
  public void shouldReturnAllCustomers() {

    Collection<Customer> customers = client.findAll();

    Assertions.assertThat(customers).hasSize(3);
  }

}
