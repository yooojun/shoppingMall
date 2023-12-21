package com.nhnacademy.shoppingmall.category.exception;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(Integer id){
        super(String.format("category Not Found Exist:%d",id));
    }
}
