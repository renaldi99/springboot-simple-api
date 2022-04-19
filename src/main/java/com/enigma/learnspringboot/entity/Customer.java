package com.enigma.learnspringboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter@Getter@AllArgsConstructor
@Table(name = "mst_customer")
public class Customer {
    // uuid
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "customer_id")
    private String id;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private String status;
    private String userName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // hidden data json for password
    private String password;

    // relation
//    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
//    private List<Purchase> purchases = new ArrayList<>();

    public Customer() {
    }
}
