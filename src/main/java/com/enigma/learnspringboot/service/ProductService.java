package com.enigma.learnspringboot.service;

import com.enigma.learnspringboot.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> getAllProduct();
    public Product getProductById(String productId);
    public Product saveProduct(Product product);
    public void updateProduct(Product product);
    public void deleteProductById(String productId);
    public Page<Product> getProductByPage(Pageable pageable);
}