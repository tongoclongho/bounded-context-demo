package com.demo.crm;

import com.demo.crm.controller.CustomerController;
import com.demo.crm.entity.Customer;
import com.demo.crm.repository.CustomerRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.test.web.servlet.client.assertj.RestTestClientResponse;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest(CustomerController.class)
@AutoConfigureRestTestClient
class CustomerControllerTest {

    @Autowired
    private RestTestClient restTestClient;

    @MockitoBean
    private CustomerRepository customerRepository;

    @Test
    void getCustomerByName_from_existedCustomer_then_OK() {
        // Arrange
        Customer customer = Customer.builder().name("alice").balance(BigDecimal.valueOf(10.0)).build();
        when(customerRepository.findByName(anyString())).thenReturn(Optional.of(customer));

        // Act
        Customer response = restTestClient.get()
                .uri("/customers/{name}", "alice")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Customer.class)
                .returnResult()
                .getResponseBody();

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo("alice");
        assertThat(response.getBalance()).isEqualTo(BigDecimal.valueOf(10.0));
    }

    @Test
    void getCustomerByName_from_existedCustomer_then_OK_V2() {
        // Arrange
        Customer customer = Customer.builder().name("alice").balance(BigDecimal.valueOf(10.0)).build();
        when(customerRepository.findByName(anyString())).thenReturn(Optional.of(customer));

        // Act
        var responseSpec = restTestClient.get()
                .uri("/customers/{name}", "alice").exchange();
        var response = RestTestClientResponse.from(responseSpec);

        // Assert
        assertThat(response).hasStatusOk();
        assertThat(response).bodyJson().extractingPath("$.name").asString().isEqualTo("alice");
    }
}
