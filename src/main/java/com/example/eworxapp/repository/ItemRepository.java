package com.example.eworxapp.repository;

import com.example.eworxapp.model.Item;

import java.util.List;

public interface ItemRepository {
    Item findItem(int itemId);
    List<Item> getAllItems();
}
