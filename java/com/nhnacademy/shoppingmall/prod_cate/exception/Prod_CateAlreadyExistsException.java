package com.nhnacademy.shoppingmall.prod_cate.exception;

import com.nhnacademy.shoppingmall.prod_cate.domain.Prod_Cate;

public class Prod_CateAlreadyExistsException extends RuntimeException{
    public Prod_CateAlreadyExistsException(Prod_Cate prodCate) {
        super(String.format("Prod_Cate Already Exist ProductID = %d ,CategoryID = %d", prodCate.getProductId(), prodCate.getCategoryId()));
    }
}
