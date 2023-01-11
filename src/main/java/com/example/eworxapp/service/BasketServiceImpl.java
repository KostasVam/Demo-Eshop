package com.example.eworxapp.service;

import com.example.eworxapp.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

@Service
public class BasketServiceImpl implements BasketService{

    @Autowired
    private ItemService itemService;

    @Override
    public void addItem(Map<Integer, Item> basket, int itemId) {
        Item item = itemService.findItem(itemId);
        if (basket.containsKey(itemId)) {
            item.setQuantity(item.getQuantity() + 1);
        } else {
            item.setQuantity(1);
        }
        basket.put(itemId, item);
    }

    @Override
    public void updateQuantity(Map<Integer, Item> basket, int itemId, int newQuantity) {
        if (newQuantity <= 0) {
            removeItem(basket, itemId);
        } else {
            basket.get(itemId).setQuantity(newQuantity);
        }
    }

    @Override
    public void removeItem(Map<Integer, Item> basket, int itemId) {
        basket.remove(itemId);
        itemService.findItem(itemId).setQuantity(0);
    }

    @Override
    public BigDecimal calculateTotal(Map<Integer, Item> basket) {
        BigDecimal total = BigDecimal.ZERO;
        for (Map.Entry<Integer, Item> entry : basket.entrySet()) {
            total = total.add(entry.getValue().getPrice().multiply(BigDecimal.valueOf(entry.getValue().getQuantity())));
        }
        return total.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public BigDecimal calculateShippingCost(Map<Integer, Item> basket) {
        BigDecimal totalWeightFactor = BigDecimal.ZERO;
        for (Map.Entry<Integer, Item> entry : basket.entrySet()) {
            totalWeightFactor = totalWeightFactor.add(entry.getValue().getWeightFactor()
                    .multiply(BigDecimal.valueOf(entry.getValue().getQuantity())));
        }
        BigDecimal cost = BigDecimal.valueOf(5);
        return cost.multiply(totalWeightFactor).setScale(2, RoundingMode.HALF_EVEN);
    }


    @Override
    public BigDecimal calculateDiscount(Map<Integer, Item> basket) {
        BigDecimal total = calculateTotal(basket);
        if (total.compareTo(BigDecimal.valueOf(100)) >= 0) {
            return total.multiply(BigDecimal.valueOf(0.1)).setScale(2, RoundingMode.HALF_EVEN);
        } else {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal calculateFinalPrice(Map<Integer, Item> basket) {
        BigDecimal total = calculateTotal(basket);
        BigDecimal discount = calculateDiscount(basket);
        BigDecimal finalPrice = total.subtract(discount);
        BigDecimal shippingCost = calculateShippingCost(basket);
        return finalPrice.add(shippingCost).setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }
}
