package com.nhnacademy.shoppingmall.cart.exception;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(Integer cartId) { super(String.format("CartNouFoundException cartId = ",cartId ));
    }
}
