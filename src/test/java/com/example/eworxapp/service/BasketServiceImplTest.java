package com.example.eworxapp.service;

import com.example.eworxapp.model.Item;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BasketServiceImplTest {

    @InjectMocks
    private BasketServiceImpl basketServiceImpl;

    @Mock
    private ItemServiceImpl itemService;

    @BeforeEach
    void setUp() {
        basketServiceImpl = new BasketServiceImpl();
    }

    @Test
    public void testAddItem() {
        Map<Integer, Item> basket = new HashMap<>();
        Item item1 = new Item(1, "item1", BigDecimal.valueOf(20),  BigDecimal.valueOf(0.5));

        when(itemService.findItem(item1.getId())).thenReturn(item1);
        basketServiceImpl.addItem(basket, item1.getId());
        Assertions.assertEquals(1, basket.size());
        Assertions.assertEquals(item1, basket.get(item1.getId()));

        Item item2 = new Item(1, "item1", BigDecimal.valueOf(20),  BigDecimal.valueOf(0.5));
        when(itemService.findItem(item2.getId())).thenReturn(item2);
        basketServiceImpl.addItem(basket, item2.getId());
        Assertions.assertEquals(1, basket.size());
        Assertions.assertEquals(2, basket.get(item1.getId()).getQuantity());
    }

    @Test
    public void testRemoveItem() {
        Map<Integer, Item> basket = new HashMap<>();
        Item item1 = new Item(1, "item1", BigDecimal.valueOf(20),  BigDecimal.valueOf(0.5));
        Item item2 = new Item(2, "item2", BigDecimal.valueOf(15),  BigDecimal.valueOf(0.2));
        basket.put(item1.getId(), item1);
        basket.put(item2.getId(), item2);
        basketServiceImpl.removeItem(basket, 1);
        Assertions.assertEquals(1, basket.size());
        Assertions.assertEquals(item2, basket.get(item2.getId()));
    }

    @Test
    public void testCalculateTotal() {
        Map<Integer, Item> basket = new HashMap<>();
        Item item1 = new Item(1, "item1", BigDecimal.valueOf(20),  BigDecimal.valueOf(0.5));
        Item item2 = new Item(2, "item2", BigDecimal.valueOf(15),  BigDecimal.valueOf(0.2));
        basket.put(item1.getId(), item1);
        basket.put(item2.getId(), item2);
        BigDecimal total = basketServiceImpl.calculateTotal(basket);
        Assertions.assertEquals(BigDecimal.valueOf(35), total, String.valueOf(0.001));
    }

    @Test
    public void testCalculateShippingCost() {
        Map<Integer, Item> basket = new HashMap<>();
        Item item1 = new Item(1, "item1", BigDecimal.valueOf(20),  BigDecimal.valueOf(0.5));
        Item item2 = new Item(2, "item2", BigDecimal.valueOf(15),  BigDecimal.valueOf(0.2));
        basket.put(item1.getId(), item1);
        basket.put(item2.getId(), item2);
        BigDecimal shippingCost = basketServiceImpl.calculateShippingCost(basket);
        Assertions.assertEquals( BigDecimal.valueOf(0.3), shippingCost, String.valueOf(0.001));
    }
}

