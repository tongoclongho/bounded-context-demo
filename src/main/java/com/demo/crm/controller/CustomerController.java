package com.demo.crm.controller;

import com.demo.crm.entity.Customer;
import com.demo.crm.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @GetMapping("")
    public ResponseEntity<List<Customer>> getCustomer() {

        List<Customer> customerList = new ArrayList<>();
        customerRepository.findAll().forEach(customerList::add);

        return ResponseEntity.ok(customerList);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Customer> getCustomerByName(@PathVariable("name") String name) {

        Optional<Customer> found = customerRepository.findByName(name);

        return ResponseEntity.ok(found.orElse(null));
    }

    @PostMapping("/{name}")
    public ResponseEntity<Customer> postCustomer(@PathVariable("name") String name) {
        Customer created = customerRepository.save(new Customer(name, BigDecimal.ONE));
        return ResponseEntity.ok(created);
    }
}
