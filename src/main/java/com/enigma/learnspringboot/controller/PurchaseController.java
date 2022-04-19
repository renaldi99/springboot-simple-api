package com.enigma.learnspringboot.controller;

import com.enigma.learnspringboot.constant.ApiUrlConstant;
import com.enigma.learnspringboot.entity.Purchase;
import com.enigma.learnspringboot.entity.PurchaseDetail;
import com.enigma.learnspringboot.service.PurchaseDetailService;
import com.enigma.learnspringboot.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiUrlConstant.PURCHASE)
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;

    @PostMapping
    public Purchase transaction(@RequestBody Purchase purchase) {
        return purchaseService.transaction(purchase);
    }
}
