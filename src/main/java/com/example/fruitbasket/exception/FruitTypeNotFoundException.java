package com.example.fruitbasket.exception;

public class FruitTypeNotFoundException extends RuntimeException{
    public FruitTypeNotFoundException() {
        super();
    }
    public FruitTypeNotFoundException(final String message) {
        super(message);
    }
}
