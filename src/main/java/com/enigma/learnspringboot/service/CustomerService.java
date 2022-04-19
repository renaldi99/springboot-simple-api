package com.enigma.learnspringboot.service;

import com.enigma.learnspringboot.dto.CustomerSearchDTO;
import com.enigma.learnspringboot.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomerService {
    public List<Customer> getAllCustomer();
    public Customer getCustomerById(String customerId);
    public Customer saveCustomer(Customer customer);
    public void updateCustomer(Customer customer);
    public void deleteCustomerById(String customerId);
    public Page<Customer> getCustomerByPage(Pageable pageable, CustomerSearchDTO customerSearchDTO);
    public List<Customer> getCustomerByName(String firstName, String lastName);
}
