package com.enigma.learnspringboot.specification;

import com.enigma.learnspringboot.dto.CustomerSearchDTO;
import com.enigma.learnspringboot.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CustomerSpecification {
    public static Specification<Customer> getSpecification(CustomerSearchDTO customerSearchDTO) {
        return new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                // search firstname
                if (!(customerSearchDTO.getSearchCustomerFirstName() == null)) {
                    Predicate customerFirstNamePredicate = criteriaBuilder.like(root.get("firstName"), "%" + customerSearchDTO.getSearchCustomerFirstName().toLowerCase() + "%");
                    predicates.add(customerFirstNamePredicate);
                }
                // search lastname
                if (!(customerSearchDTO.getSearchCustomerLastName() == null)) {
                    Predicate customerLastNamePredicate = criteriaBuilder.like(root.get("lastName"), "%" + customerSearchDTO.getSearchCustomerLastName().toLowerCase() + "%");
                    predicates.add(customerLastNamePredicate);
                }
                Predicate[] arrayPredicate = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(arrayPredicate);
            }
        };
    }
}
