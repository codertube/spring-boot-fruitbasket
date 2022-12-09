package com.example.fruitbasket.controller;

import com.example.fruitbasket.exception.FruitTypeNotFoundException;
import com.example.fruitbasket.model.FruitBasket;
import com.example.fruitbasket.service.FruitBasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/")
public class FruitBasketController {
    @Autowired
    FruitBasketService fruitBasketService;

    @GetMapping("fruits")
    public ResponseEntity<List<FruitBasket>> getAllCategories(){
        List<FruitBasket> fruitBaskets = Arrays.asList(
                new FruitBasket("apple", 10, new BigDecimal("9.99")),
                new FruitBasket("banana", 20, new BigDecimal("19.99")));
        if(fruitBaskets.isEmpty()){
            throw new FruitTypeNotFoundException("No Fruit Types found");
        }
        return new ResponseEntity<>(fruitBaskets, HttpStatus.OK);
    }

    @GetMapping("calcSumByFruitbyName/{fruitName}")
    public ResponseEntity<BigDecimal> getFruitsSumByName(@PathVariable("fruitName") String fruitName) {
        BigDecimal fruitSumByName = fruitBasketService.getSumOfFruitsByName(fruitName);
        if(fruitSumByName== null || fruitSumByName.compareTo(BigDecimal.ZERO) == 0){
            throw new FruitTypeNotFoundException("No Fruit Types found");
        }
        return new ResponseEntity<>(fruitSumByName, HttpStatus.OK);
    }
}
