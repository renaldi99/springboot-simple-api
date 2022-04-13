package com.enigma.learnspringboot.service.impl;

import com.enigma.learnspringboot.entity.Customer;
import com.enigma.learnspringboot.repository.CustomerRepository;
import com.enigma.learnspringboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Integer customerId) {
        if (customerRepository.findById(customerId).isPresent()) {
            return customerRepository.findById(customerId).get();
        } else {
            return null;
        }
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Integer customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public Page<Customer> getCustomerByPage(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
}
