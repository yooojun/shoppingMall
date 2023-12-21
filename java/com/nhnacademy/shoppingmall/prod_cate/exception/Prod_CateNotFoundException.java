package com.nhnacademy.shoppingmall.prod_cate.exception;

import com.nhnacademy.shoppingmall.prod_cate.domain.Prod_Cate;

public class Prod_CateNotFoundException extends RuntimeException{
    public Prod_CateNotFoundException(Prod_Cate prodCate) {
        super(String.format("Prod_Cate Not Found Exception ProductID = %d , CategoryID = %d", prodCate.getProductId(), prodCate.getCategoryId()));
    }
}
