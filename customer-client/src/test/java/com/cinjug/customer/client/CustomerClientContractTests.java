package com.cinjug.customer.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= NONE)
@AutoConfigureStubRunner(ids = {"com.cinjug:customer-service:${customer-service.version}:stubs:8080"},
    stubsMode = LOCAL)
@DirtiesContext
public class CustomerClientContractTests {
  @Autowired
  private CustomerClient client;

  @Test
  public void shouldReturnAllCustomers() {

    Collection<Customer> customers = client.findAll();

    assertThat(customers).hasSize(3);
  }

}
