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
        .mapToObj(CustomerServiceApplication::customerForTesting)
        .map(repository::save)
        .forEach(System.out::println);
  }

  static Customer customerForTesting(int i) {
    return new Customer("First" + i, "Last" + i, "first" + i + "@test.com");
  }
}

