package com.cinjug.customer.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ReflectionUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerClientApplication.class)
@AutoConfigureWireMock
public class CustomerClientWireMockTests {

  private static final ClassPathResource customers = new ClassPathResource("/customers.json");
  private static final ClassPathResource customerById = new ClassPathResource("/customer-by-id.json");
  @Autowired
  private CustomerClient client;

  @Test
  public void shouldReturnAllCustomers() {
    stubFor(get(urlMatching("/customers"))
        .willReturn(aResponse()
            .withHeader(CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
            .withStatus(HttpStatus.OK.value())
            .withBody(asJson(customers))));

    Collection<Customer> customers = client.findAll();

    assertThat(customers).hasSize(2);
  }

  @Test
  public void shouldReturnSingleCustomer() {
    stubFor(get(urlEqualTo("/customers/1"))
        .willReturn(aResponse()
            .withHeader(CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
            .withStatus(HttpStatus.OK.value())
            .withBody(asJson(customerById))));

    Customer customer = client.findById(1);

    assertThat(customer).isNotNull();
    assertThat(customer.getId()).isEqualTo(1L);
  }

  private String asJson(ClassPathResource resource) {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
      return reader.lines().collect(joining());
    } catch (Exception e) {
      ReflectionUtils.rethrowRuntimeException(e);
      return null;
    }
  }
}
