package com.enigma.learnspringboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Setter@Getter@AllArgsConstructor
@Table(name = "mst_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
    @SequenceGenerator(name = "customer_generator", sequenceName = "customer_id_seq", allocationSize = 1)
    private Integer id;
    private String name;
    private String address;
    private Integer phone;
    @Column(name = "birthdate")
    private Date birthDate;
    private String status;
    private String username;
    private String password;

    public Customer() {
    }
}
