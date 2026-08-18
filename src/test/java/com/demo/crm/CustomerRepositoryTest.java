package com.demo.crm;

import com.demo.TestcontainersConfiguration;
import com.demo.crm.entity.Customer;
import com.demo.crm.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void findByName_from_existingCustomer_then_OK() {
        // Arrange
        Customer customer = new Customer("Alice", BigDecimal.valueOf(100.0));
        Customer saved = customerRepository.save(customer);

        // Act
        Optional<Customer> found = customerRepository.findByName("Alice");

        // Assert
        assertThat(saved.getId()).isNotNull();
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Alice");
    }
}
