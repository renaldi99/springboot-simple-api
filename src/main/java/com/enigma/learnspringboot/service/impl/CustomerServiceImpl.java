package com.enigma.learnspringboot.service.impl;

import com.enigma.learnspringboot.constant.ResponseMessage;
import com.enigma.learnspringboot.dto.CustomerSearchDTO;
import com.enigma.learnspringboot.entity.Customer;
import com.enigma.learnspringboot.exception.DataNotFoundException;
import com.enigma.learnspringboot.repository.CustomerRepository;
import com.enigma.learnspringboot.service.CustomerService;
import com.enigma.learnspringboot.specification.CustomerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    public Customer getCustomerById(String customerId) {
        if (!customerRepository.findById(customerId).isPresent()) {
            String message = String.format(ResponseMessage.DATA_NOT_FOUND, "customer", customerId);
            throw new DataNotFoundException(message);
        }
        return customerRepository.findById(customerId).get();
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
    public void deleteCustomerById(String customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public Page<Customer> getCustomerByPage(Pageable pageable, CustomerSearchDTO customerSearchDTO) {
        Specification<Customer> customerSpecification = CustomerSpecification.getSpecification(customerSearchDTO);
        return customerRepository.findAll(customerSpecification, pageable);
    }

    @Override
    public List<Customer> getCustomerByName(String firstName, String lastName) {
        return customerRepository.findCustomerByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName);
    }

    @Override
    public List<Customer> getCustomerByStatus(String status) {
        return customerRepository.getCustomerByStatus(status);
    }

    @Override
    public void updateCustomerStatus(String status, String customerId) {
        customerRepository.updateCustomerStatus(status, customerId);
    }
}
