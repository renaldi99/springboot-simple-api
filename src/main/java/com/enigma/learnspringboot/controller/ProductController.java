package com.enigma.learnspringboot.controller;

import com.enigma.learnspringboot.constant.ApiUrlConstant;
import com.enigma.learnspringboot.constant.ResponseMessage;
import com.enigma.learnspringboot.entity.Product;
import com.enigma.learnspringboot.service.ProductService;
import com.enigma.learnspringboot.utils.PageResponseWrapper;
import com.enigma.learnspringboot.utils.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping
//    public Product createProduct(@RequestBody Product product) {
//        return productService.saveProduct(product);
//    }

    @PostMapping
    public ResponseEntity<Response<Product>> createProduct(@RequestBody Product product) {
        Response<Product> response = new Response<>();
        String message = String.format(ResponseMessage.DATA_INSERTED, "product");
        response.setMessage(message);
        response.setData(productService.saveProduct(product));
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
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
//    @GetMapping
//    public Page<Product> getProductByPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
//                                          @RequestParam(name = "size", defaultValue = "5") Integer sizePage,
//                                          @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
//                                          @RequestParam(name = "direct", defaultValue = "asc") String direct) {
//        Sort sort = Sort.by(Sort.Direction.fromString(direct), sortBy);
//        Pageable pageable = PageRequest.of(page, sizePage, sort);
//        return productService.getProductByPage(pageable);
//    }

    // refactor pageable
    @GetMapping
    public PageResponseWrapper<Product> getProductByPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                         @RequestParam(name = "size", defaultValue = "5") Integer sizePage,
                                                         @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
                                                         @RequestParam(name = "direct", defaultValue = "asc") String direct) {
        Sort sort = Sort.by(Sort.Direction.fromString(direct), sortBy);
        Pageable pageable = PageRequest.of(page, sizePage, sort);
        Page<Product> productPage = productService.getProductByPage(pageable);
        return new PageResponseWrapper<>(productPage);
    }

    @PostMapping("/picture")
    public void saveProductPicture(@RequestPart(name = "picture", required = false) MultipartFile file,
                                   @RequestPart(name = "product") String product) {
        Product product1 = new Product();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            product1 = objectMapper.readValue(product, Product.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        productService.saveProductPicture(product1, file);
    }
}
