package com.nhnacademy.shoppingmall.cart.exception;

public class CartAlreadyExistsException extends RuntimeException{
    public CartAlreadyExistsException(Integer cartId) {
        super(String.format("CartAlreadyExistsException cartID = ",cartId));
    }
}
