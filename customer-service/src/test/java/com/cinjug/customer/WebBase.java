package com.cinjug.customer;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.cinjug.customer.Customer.create;
import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class WebBase {

  @InjectMocks
  private CustomerController customerController;
  @Mock
  private CustomerRepository repository;

  @Before
  public void setup() {
    List<Customer> customers = asList(create(1), create(2), create(3));
    given(repository.findAll()).willReturn(customers);
    RestAssuredMockMvc.standaloneSetup(customerController);
  }

}