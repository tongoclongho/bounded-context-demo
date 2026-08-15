package com.demo.crm;

import com.demo.crm.entity.Customer;
import com.demo.crm.repository.CustomerRepository;
import com.demo.crm.service.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class) // Enables Mockito support in JUnit 5
class CustomerServiceTest {

    @Mock // Creates a mock instance of dependency of the target service
    private CustomerRepository customerRepository;

    @InjectMocks // Instantiates the target service and injects mocks
    private CustomerServiceImpl customerService;

    @Test
    void findByName_from_existingCustomer_then_ok() {
        // Arrange
        Customer customer = new Customer("alice", BigDecimal.valueOf(100.0));
        Mockito.when(customerRepository.findByName(ArgumentMatchers.anyString())).thenReturn(Optional.of(customer));

        // Act
        Customer found = customerService.findByName("alice");

        // Assert
        Mockito.verify(customerRepository, times(1)).findByName(ArgumentMatchers.anyString());
        Assertions.assertDoesNotThrow(() -> customerService.findByName(anyString()));
        Assertions.assertEquals("alice", found.getName());
    }
}
