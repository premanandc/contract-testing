package com.bcus.customer.client;

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
@Ignore("We don't need this integration test any more!")
public class CustomerClientIntegrationTests {
  @Autowired
  private CustomerClient client;

  @Test
  public void shouldReturnAllCustomers() {

    Collection<Customer> customers = client.findAll();

    assertThat(customers).hasSize(3);
  }

}
