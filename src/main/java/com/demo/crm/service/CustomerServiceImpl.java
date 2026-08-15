package com.demo.crm.service;

import com.demo.crm.entity.Customer;
import com.demo.crm.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findByName(String name) {
        return customerRepository.findByName(name).orElseThrow(() -> new NoSuchElementException("Not found customer"));
    }
}
