package com.example.eshopapp.controller;

import com.example.eshopapp.model.Item;
import com.example.eshopapp.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BasketController {

    public static final String SESSION_BASKET_KEY = "basket";
    @Autowired
    private BasketService basketService;

    @GetMapping("/basket")
    public ModelAndView showBasket(HttpSession session) {
        Map<Integer, Item> basket = getBasketFromSession(session);

        ModelAndView model = new ModelAndView("basket");
        model.addObject("basket", basket);
        model.addObject("items", basketService.getAllItems());
        model.addObject("total", basketService.calculateTotal(basket));
        model.addObject("shippingCost", basketService.calculateShippingCost(basket));
        model.addObject("discount", basketService.calculateDiscount(basket));
        model.addObject("finalPrice", basketService.calculateFinalPrice(basket));

        return model;
    }

    @PostMapping("/basket/add")
    public String addToBasket(HttpSession session, @RequestParam("itemId") int itemId) {
        Map<Integer, Item> basket = getBasketFromSession(session);
        basketService.addItem(basket, itemId);
        return "redirect:/basket";
    }

    @PostMapping("/updateBasket")
    public ModelAndView updateBasket(@RequestParam("itemId") int itemId,
                                     @RequestParam("quantity") int quantity,
                                     HttpSession session) {
        Map<Integer, Item> basket = getBasketFromSession(session);
        basketService.updateQuantity(basket, itemId, quantity);
        return showBasket(session);
    }

    @PostMapping("/basket/remove")
    public String removeFromBasket(HttpSession session, @RequestParam("itemId") int itemId) {
        Map<Integer, Item> basket = getBasketFromSession(session);
        basketService.removeItem(basket, itemId);
        return "redirect:/basket";
    }

    private Map<Integer, Item> getBasketFromSession(HttpSession session) {
        Object basketObject = session.getAttribute(SESSION_BASKET_KEY);
        if (basketObject instanceof Map) {
            return (Map<Integer, Item>) basketObject;
        } else {
            Map<Integer, Item> basket = new HashMap<>();
            session.setAttribute(SESSION_BASKET_KEY, basket);
            return basket;
        }
    }
}
