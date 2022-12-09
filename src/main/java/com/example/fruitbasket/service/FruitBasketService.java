package com.example.fruitbasket.service;

import com.example.fruitbasket.model.FruitBasket;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class FruitBasketService {

    List<FruitBasket> fruitBaskets = Arrays.asList(
            new FruitBasket("apple", 10, new BigDecimal("9.99")),
            new FruitBasket("banana", 20, new BigDecimal("19.99")),
            new FruitBasket("orange", 10, new BigDecimal("29.99")),
            new FruitBasket("watermelon", 10, new BigDecimal("29.99")),
            new FruitBasket("papaya", 20, new BigDecimal("9.99")),
            new FruitBasket("apple", 10, new BigDecimal("9.99")),
            new FruitBasket("banana", 10, new BigDecimal("19.99")),
            new FruitBasket("apple", 20, new BigDecimal("9.99")));

    public List<FruitBasket> getAllFruits() {
        return fruitBaskets;
    }

    public BigDecimal getSumOfFruitsByName(String fruitName) {
        return fruitBaskets.stream()
                .filter(fruitBasket -> fruitBasket.getFruitName().equals(fruitName))
                .reduce(BigDecimal.ZERO,
                        (count, fruitBasket) -> count.add(fruitBasket.getFruitPrice()),
                        BigDecimal::add);
    }
}
