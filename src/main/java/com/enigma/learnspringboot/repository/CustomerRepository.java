package com.enigma.learnspringboot.repository;

import com.enigma.learnspringboot.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>, JpaSpecificationExecutor {
//    public Page<Customer> findAll(Specification<Customer> customerSpecification, Pageable pageable);

    // native query
//    @Query(value = "SELECT * from mst_customer c WHERE c.status = ?1", nativeQuery = true)

    // Jpa Query
    @Query("SELECT c from Customer as c WHERE c.status = ?1")
    public List<Customer> getCustomerByStatus(String status);

    @Transactional
    @Modifying
    @Query(value = "UPDATE mst_customer c SET status = ?1 WHERE c.customer_id = ?2", nativeQuery = true)
    public void updateCustomerStatus(String status, String customerId);
    public List<Customer> findCustomerByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(@Param("firstName") String firstName,
                                                                                                     @Param("lastName") String lastName);
}
