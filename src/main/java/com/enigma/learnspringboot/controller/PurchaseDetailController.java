package com.enigma.learnspringboot.controller;

import com.enigma.learnspringboot.constant.ApiUrlConstant;
import com.enigma.learnspringboot.entity.PurchaseDetail;
import com.enigma.learnspringboot.service.PurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiUrlConstant.PURCHASEDETAIL)
public class PurchaseDetailController {
    @Autowired
    PurchaseDetailService purchaseDetailService;

    @PostMapping
    public PurchaseDetail savePurchaseDetail(@RequestBody PurchaseDetail purchaseDetail) {
        return purchaseDetailService.savePurchaseDetail(purchaseDetail);
    }
}
