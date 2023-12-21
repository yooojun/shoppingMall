package com.nhnacademy.shoppingmall.category.exception;

public class CategoryAlreadyExistsException extends RuntimeException{
    public CategoryAlreadyExistsException(Integer id){
        super(String.format("category Already exist:%d",id));
    }
}
