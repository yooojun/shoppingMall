package com.nhnacademy.shoppingmall.product.exception;

public class ProductAlreadyExistsException extends RuntimeException{
    public ProductAlreadyExistsException(Integer productId){
        super(String.format("product already exist:%d",productId));
    }

}
