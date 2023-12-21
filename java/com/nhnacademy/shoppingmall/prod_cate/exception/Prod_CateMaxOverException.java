package com.nhnacademy.shoppingmall.prod_cate.exception;

public class Prod_CateMaxOverException extends RuntimeException{
    public Prod_CateMaxOverException(Integer productId) { super(String.format("only category 3 per ProductId = %d",productId));
    }
}
