package com.enigma.learnspringboot.service.impl;

import com.enigma.learnspringboot.constant.ResponseMessage;
import com.enigma.learnspringboot.entity.Product;
import com.enigma.learnspringboot.exception.DataNotFoundException;
import com.enigma.learnspringboot.exception.NotEnoughStockException;
import com.enigma.learnspringboot.repository.ProductRepository;
import com.enigma.learnspringboot.service.ProductService;
import com.enigma.learnspringboot.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public Product getProductById(String productId) {
        // exception if id doesn't exist
        if (productRepository.findById(productId).isPresent()) {
            return productRepository.findById(productId).get();
        } else {
            String message = String.format(ResponseMessage.DATA_NOT_FOUND, "product", productId);
            throw new DataNotFoundException(message);
        }
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProductById(String productId) {
        productRepository.deleteById(productId);
    }

    // get product by page / limit
    @Override
    public Page<Product> getProductByPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product saveProductPicture(Product product, MultipartFile file) {
        String pathFolderString = "c:\\Users\\dwiag\\Documents\\enigmadaily-springboot\\learn-springboot\\src\\main\\java\\com\\enigma\\learnspringboot\\assets\\";
        Path pathFolder = Paths.get(pathFolderString);
        Path pathFile = Paths.get(pathFolder.toString() + "/" + file.getOriginalFilename() + ".png");
        try {
//            Files.createDirectory(pathFolder);
            file.transferTo(pathFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        product.setProductImage(file.getOriginalFilename());
        return productRepository.save(product);
    }
}
