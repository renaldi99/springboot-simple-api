package com.enigma.learnspringboot.service;

import com.enigma.learnspringboot.entity.Purchase;

public interface PurchaseService {
    public Purchase transaction(Purchase purchase);
}
