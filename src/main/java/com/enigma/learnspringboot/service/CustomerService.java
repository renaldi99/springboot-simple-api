package com.enigma.learnspringboot.service;

import com.enigma.learnspringboot.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomerService {
    public List<Customer> getAllCustomer();
    public Customer getCustomerById(Integer customerId);
    public Customer saveCustomer(Customer customer);
    public void updateCustomer(Customer customer);
    public void deleteCustomerById(Integer customerId);
    public Page<Customer> getCustomerByPage(Pageable pageable);
}
