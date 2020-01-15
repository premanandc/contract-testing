package com.cinjug.customer.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

import static org.springframework.http.HttpMethod.GET;

public class CustomerClient {

  private final RestTemplate http;
  private final String baseUri;

  public CustomerClient(RestTemplateBuilder builder, String baseUri) {
    this.http = builder.build();
    this.baseUri = baseUri;
  }

  public Collection<Customer> findAll() {
    ParameterizedTypeReference<Collection<Customer>> reference = new ParameterizedTypeReference<Collection<Customer>>() {
    };
    return http.exchange(baseUri + "/customers", GET, null, reference).getBody();
  }

  public Customer findById(long customerId) {
    return http.exchange(baseUri + "/customers/{id}", GET, null, Customer.class, customerId).getBody();
  }
}
