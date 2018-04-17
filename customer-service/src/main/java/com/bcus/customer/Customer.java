package com.bcus.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
class Customer {
  @Id
  @GeneratedValue
  private Long id;
  @NotBlank
  private String firstName;
  @NotBlank
  private String lastName;
  @Email
  private String email;

  private Customer(@NotBlank String firstName,
                   @NotBlank String lastName,
                   @Email String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  static Customer create(int i) {
    return new Customer("First" + i, "Last" + i, "first" + i + "@test.com");
  }
}
