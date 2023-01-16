package com.example.eshopapp.repository;

import com.example.eshopapp.model.Item;

import java.util.List;

public interface ItemRepository {
    Item findItem(int itemId);
    List<Item> getAllItems();
}
