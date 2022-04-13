package com.enigma.learnspringboot.service.impl;

import com.enigma.learnspringboot.entity.Product;
import com.enigma.learnspringboot.repository.ProductRepository;
import com.enigma.learnspringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    // inject repo to service
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Integer productId) {
        // exception if id doesn't exist
        if (productRepository.findById(productId).isPresent()) {
            return productRepository.findById(productId).get();
        } else {
            return null;
        }
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.saveAndFlush(product);
    }

    @Override
    public void deleteProductById(Integer productId) {
        productRepository.deleteById(productId);
    }

    // get product by page / limit

    @Override
    public Page<Product> getProductByPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
