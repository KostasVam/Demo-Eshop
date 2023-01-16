package com.example.eshopapp.service;

import com.example.eshopapp.model.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface BasketService {
    void addItem(Map<Integer, Item> basket, int itemId);
    void updateQuantity(Map<Integer, Item> basket, int itemId, int quantity);
    void removeItem(Map<Integer, Item> basket, int itemId);
    BigDecimal calculateTotal(Map<Integer, Item> basket);
    BigDecimal calculateShippingCost(Map<Integer, Item> basket);
    BigDecimal calculateDiscount(Map<Integer, Item> basket);
    BigDecimal calculateFinalPrice(Map<Integer, Item> basket);
    List<Item> getAllItems();
}
