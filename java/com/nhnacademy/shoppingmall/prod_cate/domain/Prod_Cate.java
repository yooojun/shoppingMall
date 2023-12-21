package com.nhnacademy.shoppingmall.prod_cate.domain;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter

public class Prod_Cate {
    private Integer productId;
    private Integer categoryId;

    public Prod_Cate(Integer productId, Integer categoryId) {
        this.productId = productId;
        this.categoryId = categoryId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prod_Cate)) return false;
        Prod_Cate prodCate = (Prod_Cate) o;
        return Objects.equals(productId, prodCate.productId) && Objects.equals(categoryId, prodCate.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, categoryId);
    }

    @Override
    public String toString() {
        return "Prod_Cate{" +
                "productId=" + productId +
                ", categoryId=" + categoryId +
                '}';
    }
}
