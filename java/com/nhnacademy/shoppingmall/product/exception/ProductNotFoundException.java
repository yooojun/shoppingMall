package com.nhnacademy.shoppingmall.product.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(Integer productId){
        super(String.format("product not found:%d",productId));
    }


}
