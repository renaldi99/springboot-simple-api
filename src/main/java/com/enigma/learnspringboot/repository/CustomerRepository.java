package com.enigma.learnspringboot.repository;

import com.enigma.learnspringboot.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>, JpaSpecificationExecutor {
//    public Page<Customer> findAll(Specification<Customer> customerSpecification, Pageable pageable);
    public List<Customer> findCustomerByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(@Param("firstName") String firstName,
                                                                                                     @Param("lastName") String lastName);
}
