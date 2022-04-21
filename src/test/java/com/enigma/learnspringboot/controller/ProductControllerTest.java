package com.enigma.learnspringboot.controller;

import com.enigma.learnspringboot.entity.Product;
import com.enigma.learnspringboot.service.ProductService;
import com.enigma.learnspringboot.utils.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.regex.Matcher;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @MockBean
    ProductService productService;

    @InjectMocks
    ProductController productController;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getProductById() {
    }

    // positive test case
    @Test
    void createProduct() {
        when(productService.saveProduct(any(Product.class))).thenReturn(new Product("001", "Momogi", 2000, 60));
        Product product = new Product("001", "Momogi", 2000, 60);
        ResponseEntity<Response<Product>> product1 = productController.createProduct(product);
        assertThat(product1.getBody().getData().getName()).isEqualTo("Momogi");
    }

    @Test
    void createProductWithResponseHeader() throws Exception {
        given(productService.saveProduct(any(Product.class))).willAnswer(invocationOnMock -> {
            System.out.println("Invoke " + invocationOnMock);
            return invocationOnMock.getArgument(0);
        });

        Product product = new Product("001", "Momogi", 2000, 60);
        this.mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(product)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("data.name", Matchers.is(product.getName())));
    }

    // negative test case
    @Test
    void createProductWithHeaderStatus400() throws Exception {
        this.mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());
    }

    // method convert object to string, cuz we couldn't use toString()
    public static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProductById() {
    }

    @Test
    void getProductByPage() {
    }
}