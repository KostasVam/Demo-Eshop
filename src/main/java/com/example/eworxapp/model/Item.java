package com.example.eworxapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    private int id;
    private String name;
    private BigDecimal price;
    private BigDecimal weightFactor;
    private int quantity;

    public Item(int id, String name, BigDecimal price, BigDecimal weightFactor) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.weightFactor = weightFactor;
        this.quantity = 1;
    }

    public void incrementQuantity() {
        this.quantity++;
    }
}
