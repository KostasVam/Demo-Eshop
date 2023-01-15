package com.example.eshopapp.service;

import com.example.eshopapp.model.Item;
import com.example.eshopapp.repository.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceImplTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService = new ItemServiceImpl();

    private Item item1;
    private Item item2;

    @Before
    public void setUp() {
        item1 = new Item(1, "item1", new BigDecimal("10.99"));
        item2 = new Item(2, "item2", new BigDecimal("5.99"));
    }

    @Test
    public void testGetAllItems() {
        //Arrange
        when(itemRepository.getAllItems()).thenReturn(Arrays.asList(item1, item2));

        //Act
        List<Item> items = itemService.getAllItems();

        //Assert
        assertEquals(2, items.size());
        assertEquals(item1, items.get(0));
        assertEquals(item2, items.get(1));
    }

    @Test
    public void testGetItem() {
        //Arrange
        when(itemRepository.findItem(1)).thenReturn(item1);

        //Act
        Item item = itemService.findItem(1);

        //Assert
        assertEquals(item1, item);
    }

    @Test
    public void testGetItem_ItemNotFound() {
        // Arrange
        int itemId = 1;
        when(itemService.findItem(1)).thenReturn(item1);

        // Act
        Item item = itemService.findItem(itemId);

        // Assert
        assertNull(item);
    }

}
