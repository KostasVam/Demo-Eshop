package com.example.eshopapp.service;

import com.example.eshopapp.model.Item;
import com.example.eshopapp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    public Item findItem(int itemId) {
        return itemRepository.findItem(itemId);
    }

    public List<Item> getAllItems() {
        return itemRepository.getAllItems();
    }
}
