package com.example.eworxapp.service;

import com.example.eworxapp.model.Item;

import java.util.List;

public interface ItemService {
    Item findItem(int itemId);
    List<Item> getAllItems();
}
