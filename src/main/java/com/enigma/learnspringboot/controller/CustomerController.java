package com.enigma.learnspringboot.controller;

import com.enigma.learnspringboot.entity.Customer;
import com.enigma.learnspringboot.entity.Product;
import com.enigma.learnspringboot.repository.CustomerRepository;
import com.enigma.learnspringboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

//    @GetMapping("/customers")
//    public List<Customer> getAllProduct() {
//        return customerService.getAllCustomer();
//    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomerById(@PathVariable Integer customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PostMapping("/customers")
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @PutMapping("/customers")
    public void updateCustomer(@RequestBody Customer customer) {
        customerService.updateCustomer(customer);
    }

    @DeleteMapping("/customers")
    public void deleteProductById(@RequestParam Integer customerId) {
        customerService.deleteCustomerById(customerId);
    }

    @GetMapping("/customers")
    public Page<Customer> getCustomerByPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                            @RequestParam(name = "size", defaultValue = "5") Integer sizePage,
                                            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
                                            @RequestParam(name = "direct", defaultValue = "asc") String direct) {
        Sort sort = Sort.by(Sort.Direction.fromString(direct), sortBy);
        Pageable pageable = PageRequest.of(page, sizePage, sort);
        return customerService.getCustomerByPage(pageable);
    }
}
