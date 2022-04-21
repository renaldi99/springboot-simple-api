package com.enigma.learnspringboot.controller;

import com.enigma.learnspringboot.constant.ApiUrlConstant;
import com.enigma.learnspringboot.constant.ResponseMessage;
import com.enigma.learnspringboot.dto.CustomerSearchDTO;
import com.enigma.learnspringboot.entity.Customer;
import com.enigma.learnspringboot.entity.Product;
import com.enigma.learnspringboot.repository.CustomerRepository;
import com.enigma.learnspringboot.service.CustomerService;
import com.enigma.learnspringboot.specification.CustomerSpecification;
import com.enigma.learnspringboot.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.CUSTOMER)
public class CustomerController {
    @Autowired
    CustomerService customerService;

//    @GetMapping("/customers")
//    public List<Customer> getAllProduct() {
//        return customerService.getAllCustomer();
//    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable String customerId) {
        return customerService.getCustomerById(customerId);
    }

//    @PostMapping
//    public Customer saveCustomer(@RequestBody Customer customer) {
//        return customerService.saveCustomer(customer);
//    }

    //    @PostMapping
    public ResponseEntity<Response<Customer>> saveCustomer(@RequestBody Customer customer) {
        Response<Customer> response = new Response<>();
        String message = String.format(ResponseMessage.DATA_INSERTED, "customer");
        response.setMessage(message);
        response.setData(customerService.saveCustomer(customer));
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PutMapping
    public void updateCustomer(@RequestBody Customer customer) {
        customerService.updateCustomer(customer);
    }

    @DeleteMapping
    public void deleteProductById(@RequestParam String customerId) {
        customerService.deleteCustomerById(customerId);
    }

//    @GetMapping
//    public Page<Customer> getCustomerByPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
//                                            @RequestParam(name = "size", defaultValue = "5") Integer sizePage,
//                                            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
//                                            @RequestParam(name = "direct", defaultValue = "asc") String direct) {
//        Sort sort = Sort.by(Sort.Direction.fromString(direct), sortBy);
//        Pageable pageable = PageRequest.of(page, sizePage, sort);
//        return customerService.getCustomerByPage(pageable);
//    }

    //    @GetMapping
    public Page<Customer> searchCustomerByPage(@RequestBody CustomerSearchDTO customerSearchDTO,
                                               @RequestParam(name = "page", defaultValue = "0") Integer page,
                                               @RequestParam(name = "size", defaultValue = "5") Integer sizePage,
                                               @RequestParam(name = "sortBy", defaultValue = "firstName") String sortBy,
                                               @RequestParam(name = "direct", defaultValue = "asc") String direct) {
        Sort sort = Sort.by(Sort.Direction.fromString(direct), sortBy);
        Pageable pageable = PageRequest.of(page, sizePage, sort);
        return customerService.getCustomerByPage(pageable, customerSearchDTO);
    }

    @GetMapping
    public List<Customer> getCustomerByStatus(@RequestParam String status) {
        return customerService.getCustomerByStatus(status);
    }

    @PostMapping
    public void updateCustomerStatus(@RequestParam String status, @RequestParam String customerId) {
        customerService.updateCustomerStatus(status, customerId);
    }
}
