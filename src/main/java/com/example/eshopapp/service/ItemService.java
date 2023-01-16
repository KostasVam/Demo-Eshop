package com.example.eshopapp.service;

import com.example.eshopapp.model.Item;

import java.util.List;

public interface ItemService {
    Item findItem(int itemId);
    List<Item> getAllItems();
}
