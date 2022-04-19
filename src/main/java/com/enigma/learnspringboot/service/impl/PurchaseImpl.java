package com.enigma.learnspringboot.service.impl;

import com.enigma.learnspringboot.constant.ResponseMessage;
import com.enigma.learnspringboot.entity.Product;
import com.enigma.learnspringboot.entity.Purchase;
import com.enigma.learnspringboot.entity.PurchaseDetail;
import com.enigma.learnspringboot.exception.NotEnoughStockException;
import com.enigma.learnspringboot.repository.PurchaseRepository;
import com.enigma.learnspringboot.service.ProductService;
import com.enigma.learnspringboot.service.PurchaseDetailService;
import com.enigma.learnspringboot.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Member;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

@Service
public class PurchaseImpl implements PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    PurchaseDetailService purchaseDetailService;

    @Autowired
    ProductService productService;

    @Override
    @Transactional
    public Purchase transaction(Purchase purchase) {
        Purchase purchase1 = purchaseRepository.save(purchase);
        BigDecimal grandTotal = new BigDecimal("0.0");

        for (PurchaseDetail p :
                purchase.getPurchaseDetails()) {
            p.setPurchase(purchase1);
            Product product = productService.getProductById(p.getProduct().getId());
            if (product.getStock() < p.getQuantity()) {
                throw new NotEnoughStockException(ResponseMessage.DATA_NOT_ENOUGH);
            } else {
                product.setStock(product.getStock() - p.getQuantity());
            }
            p.setPriceSell(product.getProductPrice() * p.getQuantity());
            grandTotal = grandTotal.add(BigDecimal.valueOf(p.getPriceSell()));
            p.getProduct().setName(product.getName());

            purchaseDetailService.savePurchaseDetail(p);
        }

        System.out.println("Result Toltal: " + grandTotal);
        return purchase;
    }
}
