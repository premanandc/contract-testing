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
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerClientApplication.class)
@AutoConfigureWireMock
public class CustomerClientWireMockTests {

    private static final ClassPathResource customers = new ClassPathResource("/customers.json");
    @Autowired
    private CustomerClient client;

    @Test
    public void shouldReturnAllCustomers() throws Exception {
        stubFor(get(urlMatching("/customers"))
                .willReturn(aResponse()
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                        .withStatus(HttpStatus.OK.value())
                        .withBody(asJson())));

        Collection<Customer> customers = client.findAll();

        assertThat(customers).hasSize(3)
                .extracting(Customer::getFirstName, Customer::getLastName, Customer::getEmail)
                .containsExactly(
                        tuple("First1", "Last1", "first1@test.com"),
                        tuple("First2", "Last2", "first2@test.com"),
                        tuple("First3", "Last3", "first3@test.com")
                );
    }

    private String asJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(customers.getInputStream()))) {
            return reader.lines().collect(joining());
        }
    }
}
