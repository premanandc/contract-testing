package com.bcus.customer.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(CustomerClientApplication.class, args);
  }

  @Bean
  public CustomerClient client(RestTemplateBuilder builder, @Value("${customer-service.host:http://localhost:8080}") String baseUri) {
    return new CustomerClient(builder, baseUri);
  }
}
