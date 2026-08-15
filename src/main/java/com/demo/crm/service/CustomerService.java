package com.demo.crm.service;

import com.demo.crm.entity.Customer;

public interface CustomerService {

    Customer findByName(String name);
}
