package com.enigma.learnspringboot.service.impl;

import com.enigma.learnspringboot.entity.Customer;
import com.enigma.learnspringboot.repository.CustomerRepository;
import com.enigma.learnspringboot.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerServiceImpl customerService;

    @Test
    void getAllCustomer() {
        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer("01", "dea", "azelea", Date.valueOf("2001-05-12"), "mahasiswa", "dea", "123");
        Customer customer2 = new Customer("02", "ashe", "nata", Date.valueOf("2002-07-11"), "mahasiswa", "ashe", "123");
        customers.add(customer1);
        customers.add(customer2);

        when(customerRepository.findAll()).thenReturn(customers);
        List<Customer> customerList = customerService.getAllCustomer();
        assertEquals(2, customerList.size());
    }

    // positive test case
    @Test
    void getCustomerById() {
        when(customerRepository.findById("01")).thenReturn(Optional.of(Optional.of(new Customer("01", "dea", "azelea", Date.valueOf("2001-05-12"), "mahasiswa", "dea", "123")).get()));
        Customer customer = customerService.getCustomerById("01");
        assertEquals("01", customer.getId());
    }

    // negative test case
    void getCustomerByIdReturnNotFoundException() {
    }

    @Test
    void saveCustomer() {
        Customer customer = new Customer("01", "dea", "azelea", Date.valueOf("2001-05-12"), "mahasiswa", "dea", "123");
        customerService.saveCustomer(customer);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void updateCustomer() {
        Customer updateCustomer = new Customer("01", "rea", "azelea", Date.valueOf("2001-05-12"), "mahasiswa", "dea", "123");
        customerService.updateCustomer(updateCustomer);
        verify(customerRepository, times(1)).save(updateCustomer);
    }

    @Test
    void deleteCustomerById() {
        customerService.deleteCustomerById("01");
        verify(customerRepository, times(1)).deleteById("01");
    }

    @Test
    void getCustomerByPage() {
    }

    @Test
    void getCustomerByName() {
    }
}