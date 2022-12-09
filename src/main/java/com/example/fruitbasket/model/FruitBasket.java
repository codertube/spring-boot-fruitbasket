package com.example.fruitbasket.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class FruitBasket {
    String fruitName;
    Integer quantity;
    BigDecimal fruitPrice;
}
