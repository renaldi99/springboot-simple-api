package com.enigma.learnspringboot.controller;

import com.enigma.learnspringboot.constant.ApiUrlConstant;
import com.enigma.learnspringboot.entity.Product;
import com.enigma.learnspringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiUrlConstant.PRODUCT)
public class ProductController {
    // service
    @Autowired
    ProductService productService;

//    @GetMapping("/products")
//    public List<Product> getAllProduct() {
//        return productService.getAllProduct();
//    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable String productId) {
        return productService.getProductById(productId);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping
    public void updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
    }

    @DeleteMapping
    public void deleteProductById(@RequestParam String productId) {
        productService.deleteProductById(productId);
    }

    // get product by page
    @GetMapping
    public Page<Product> getProductByPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                          @RequestParam(name = "size", defaultValue = "5") Integer sizePage,
                                          @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
                                          @RequestParam(name = "direct", defaultValue = "asc") String direct) {
        Sort sort = Sort.by(Sort.Direction.fromString(direct), sortBy);
        Pageable pageable = PageRequest.of(page, sizePage, sort);
        return productService.getProductByPage(pageable);
    }
}
