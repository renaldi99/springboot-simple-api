package com.enigma.learnspringboot.service.impl;

import com.enigma.learnspringboot.entity.PurchaseDetail;
import com.enigma.learnspringboot.repository.PurchaseDetailRepository;
import com.enigma.learnspringboot.service.PurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseDetailImpl implements PurchaseDetailService {
    @Autowired
    PurchaseDetailRepository purchaseDetailRepository;

    @Override
    public PurchaseDetail savePurchaseDetail(PurchaseDetail purchaseDetail) {
        return purchaseDetailRepository.save(purchaseDetail);
    }
}
