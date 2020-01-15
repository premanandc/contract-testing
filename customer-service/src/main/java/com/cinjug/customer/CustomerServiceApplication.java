package com.cinjug.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.IntStream;

@SpringBootApplication
public class CustomerServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(CustomerServiceApplication.class, args);
  }

  @Bean
  public CommandLineRunner initDb(CustomerRepository repository) {
    return args -> IntStream.rangeClosed(1, 3)
        .mapToObj(Customer::create)
        .map(repository::save)
        .forEach(System.out::println);
  }
}

