package com.cinjug.customer.client;

import lombok.Data;

@Data
public class Customer {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
}
