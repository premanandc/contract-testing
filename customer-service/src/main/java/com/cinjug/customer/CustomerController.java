package com.cinjug.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

/**
 * The customer controller
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {
  private final CustomerRepository repository;

  public CustomerController(CustomerRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public ResponseEntity<List<Customer>> all() {
    List<Customer> all = StreamSupport.stream(repository.findAll().spliterator(), false).collect(toList());
    return ResponseEntity.ok(all);
  }

  @GetMapping("/{customerId}")
  public ResponseEntity<?> byId(@PathVariable Long customerId) {
    return repository.findById(customerId)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}
