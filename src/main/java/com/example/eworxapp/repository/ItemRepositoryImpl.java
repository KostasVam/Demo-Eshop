package com.example.eworxapp.repository;

import com.example.eworxapp.model.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemRepositoryImpl implements ItemRepository{
    private final List<Item> items;

    public ItemRepositoryImpl() {
        items = new ArrayList<>();
        items.addAll(createDummyItems());
    }

    private List<Item> createDummyItems() {
        return List.of(new Item(1, "item1",  BigDecimal.valueOf(20), BigDecimal.valueOf(0.5)),
                new Item(2, "item2",  BigDecimal.valueOf(15), BigDecimal.valueOf(0.2)),
                new Item(3, "item3",  BigDecimal.valueOf(25), BigDecimal.valueOf(0.8)));
    }

    @Override
    public Item findItem(int itemId) {
        return items.stream()
                .filter(item -> item.getId() == itemId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Item> getAllItems() {
        return items;
    }
}
