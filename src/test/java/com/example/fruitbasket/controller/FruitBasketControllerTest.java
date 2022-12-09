package com.example.fruitbasket.controller;

import com.example.fruitbasket.exception.FruitTypeNotFoundException;
import com.example.fruitbasket.model.FruitBasket;
import com.example.fruitbasket.service.FruitBasketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.VerificationModeFactory;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = FruitBasketController.class)
public class FruitBasketControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private FruitBasketService fruitBasketService;

    @Test
    public void testGetFruitsWithFruitsLoaded() throws Exception {
        List<FruitBasket> fruitBaskets = Arrays.asList(
                new FruitBasket("apple", 10, new BigDecimal("9.99")),
                new FruitBasket("banana", 20, new BigDecimal("19.99")));

        given(fruitBasketService.getAllFruits()).willReturn(fruitBaskets);

        mvc.perform(get("/fruits")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
        verify(fruitBasketService, VerificationModeFactory.times(1)).getAllFruits();
    }

    @Test()
    public void testGetFruitsWithNoFruitsLoaded() throws Exception {
        List<FruitBasket> fruitBaskets = Collections.emptyList();

        given(fruitBasketService.getAllFruits()).willReturn(fruitBaskets);

        mvc.perform(get("/fruits")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof FruitTypeNotFoundException));
        verify(fruitBasketService, VerificationModeFactory.times(1)).getAllFruits();
    }

    @Test
    public void testgetSumOfFruitsByName_ValidFruit() throws Exception {
        String fruitName = "banana";
        given(fruitBasketService.getSumOfFruitsByName(fruitName)).willReturn(new BigDecimal(39.98));

        mvc.perform(get("/calcSumByFruitbyName/{fruitName}", fruitName)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(fruitBasketService, VerificationModeFactory.times(1)).getSumOfFruitsByName(fruitName);
    }

    @Test()
    public void testgetSumOfFruitsByName_InValidFruit() throws Exception {
        String fruitName = "banana1";
        mvc.perform(get("/calcSumByFruitbyName/{fruitName}", fruitName)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof FruitTypeNotFoundException));
        verify(fruitBasketService, VerificationModeFactory.times(1)).getSumOfFruitsByName(fruitName);
    }
}
