package com.enigma.learnspringboot.service.impl;

import com.enigma.learnspringboot.entity.Product;
import com.enigma.learnspringboot.exception.DataNotFoundException;
import com.enigma.learnspringboot.repository.ProductRepository;
import com.enigma.learnspringboot.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setup() {
        product = new Product("001", "Momogi", 2000, 60);
    }

    @AfterEach
    void cleanUp() {
        product = new Product();
    }

    @Test
    void getAllProduct() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product("001", "Momogi", 2000, 60);
        Product product2 = new Product("002", "Citato", 8000, 10);
        Product product3 = new Product("003", "Qitela", 10000, 10);
        products.add(product1);
        products.add(product2);
        products.add(product3);

        when(productRepository.findAll()).thenReturn(products);
        List<Product> productList = productService.getAllProduct();
        assertEquals(3, productList.size());
    }

    // positive test case
    @Test
    void getProductById() {
        when(productRepository.findById("001")).thenReturn(Optional.of(Optional.of(new Product("001", "Momogi", 2000, 60)).get()));
        Product product = productService.getProductById("001");
        assertEquals("Momogi", product.getName());
    }

    // negative test case
    @Test
    void getProductByIdReturnNotFoundException() {
        Throwable ex = assertThrows(DataNotFoundException.class, () -> productService.getProductById("002"));
        System.out.println(ex.getMessage());
        assertEquals("Resource data product with ID 002 not found", ex.getMessage());
    }

    @Test
    void saveProduct() {
        productService.saveProduct(product);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void saveProductSuccess() {
        Product dummyProduct = new Product("001", "Momogi", 2000, 60);
        when(productRepository.save(dummyProduct)).thenReturn(dummyProduct);
//        Product dummy = new Product("001", "Momogi", 2000, 60);
        Product product1 = productService.saveProduct(dummyProduct);
        assertEquals(dummyProduct.getId(), product1.getId());
        assertEquals("Momogi", product.getName());
    }

    @Test
    void updateProduct() {
        Product updateProduct = new Product("001", "Richeese", 2000, 60);
        productService.updateProduct(updateProduct);
        verify(productRepository, times(1)).save(updateProduct);
    }

    @Test
    void deleteProductById() {
        productService.deleteProductById("001");
        verify(productRepository, times(1)).deleteById("001");
    }

    @Test
    void getProductByPage() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("name"));
        Page<Product> productPage = productService.getProductByPage(pageable);
        when(productRepository.findAll(pageable)).thenReturn(productPage);
        assertEquals(productRepository.findAll(pageable), productPage);
    }
}