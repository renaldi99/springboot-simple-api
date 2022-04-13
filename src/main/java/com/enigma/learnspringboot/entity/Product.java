package com.enigma.learnspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter@Getter@AllArgsConstructor
@Table(name = "mst_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    @SequenceGenerator(name = "product_generator", sequenceName = "product_id_seq", allocationSize = 1)
    private Integer id;
    private String name;
    @Column(name = "product_price")
    private Integer productPrice;
    private Integer stock;

    public Product() {
    }
}
