package com.example.eshopapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import com.example.eshopapp.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


class BasketServiceTest {

    @Mock
    private ItemService itemService;
    @InjectMocks
    private BasketService basketService;

    private Map<Integer, Item> basket;

    @BeforeEach
    public void setUp() {
        basket = new HashMap<>();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddItem() {
        Item item1 = new Item(1, "Item 1", BigDecimal.valueOf(10.0),  BigDecimal.valueOf(0.1));
        when(itemService.findItem(anyInt())).thenReturn(item1);
        basketService.addItem(basket, 1);

        assertEquals(1, basket.size());
        assertEquals(item1, basket.get(item1.getId()));
    }

    @Test
    public void testRemoveItem() {
        Item item1 = new Item(1, "Item 1", BigDecimal.valueOf(10.0), BigDecimal.valueOf(0.1));
        basket.put(item1.getId(), item1);
        basketService.removeItem(basket, 1);

        assertEquals(0, basket.size());
    }

    @Test
    public void testUpdateQuantity() {
        Item item1 = new Item(1, "Item 1", BigDecimal.valueOf(10.0), BigDecimal.valueOf(0.1));
        basket.put(item1.getId(), item1);
        basketService.updateQuantity(basket, 1, 5);

        assertEquals(5, basket.get(item1.getId()).getQuantity());
    }

    @Test
    public void testCalculateTotal() {
        Map<Integer, Item> basket = new HashMap<>();
        Item item1 = new Item(1, "item1", new BigDecimal("10.00"), BigDecimal.valueOf(2));
        Item item2 = new Item(2, "item2", new BigDecimal("5.00"), BigDecimal.valueOf(3));
        basket.put(item1.getId(), item1);
        basket.put(item2.getId(), item2);

        BigDecimal total = basketService.calculateTotal(basket);

        assertEquals(new BigDecimal("25.00"), total);
    }


    @Test
    void testCalculateShippingCost() {
        // Create test basket
        Map<Integer, Item> basket = new HashMap<>();
        Item item1 = new Item(1, "Item 1", new BigDecimal("20"), BigDecimal.valueOf(1));
        item1.setWeightFactor(BigDecimal.valueOf(0.5));
        Item item2 = new Item(2, "Item 2", new BigDecimal("10"), BigDecimal.valueOf(2));
        item2.setWeightFactor(BigDecimal.valueOf(0.2));
        basket.put(1, item1);
        basket.put(2, item2);

        BigDecimal expectedShippingCost = new BigDecimal("5").multiply(item1.getWeightFactor()).add(new BigDecimal("5").multiply(item2.getWeightFactor()).multiply(new BigDecimal(2)));
        assertEquals(expectedShippingCost, basketService.calculateShippingCost(basket));
    }

    @Test
    void testCalculateDiscount() {
        // Create test basket
        Map<Integer, Item> basket = new HashMap<>();
        Item item1 = new Item(1, "Item 1", new BigDecimal("20"),  BigDecimal.valueOf(1));
        Item item2 = new Item(2, "Item 2", new BigDecimal("10"),  BigDecimal.valueOf(2));
        basket.put(1, item1);
        basket.put(2, item2);

        BigDecimal total = item1.getPrice().add(item2.getPrice().multiply(new BigDecimal(2)));
        BigDecimal expectedDiscount = new BigDecimal("0.0");
        if (total.compareTo(new BigDecimal("100")) >= 0) {
            expectedDiscount = total.multiply(new BigDecimal("0.1")).setScale(2, RoundingMode.HALF_UP);
        }
        assertEquals(expectedDiscount, basketService.calculateDiscount(basket));
    }

    @Test
    void testCalculateFinalPrice() {
        Map<Integer, Item> basket = new HashMap<>();

        // Add items to basket
        Item item1 = new Item(1, "item1", new BigDecimal("10.0"), BigDecimal.valueOf(0.2));
        basket.put(item1.getId(), item1);
        Item item2 = new Item(2, "item2", new BigDecimal("20.0"), BigDecimal.valueOf(0.5));
        basket.put(item2.getId(), item2);

        BigDecimal finalPrice = basketService.calculateFinalPrice(basket);

        // The total of the basket is 10 + 20 = €30.
        // The weight factor of item1 is 0.2 and of item2 is 0.5. Therefore the shipping cost should be 5 * (0.2 + 0.5) = €2.5
        // The final price should be 30 + 2.5 = €32.5
        BigDecimal expectedFinalPrice = new BigDecimal("32.5");
        assertEquals(expectedFinalPrice, finalPrice);
    }
}
