package com.nhnacademy.shoppingmall.product.domain;

import com.nhnacademy.shoppingmall.category.domain.Category;
import java.util.Objects;

public class ProductJoinCategory {
    Product product;
    Category category;

    public ProductJoinCategory(Product product, Category category) {
        this.product = product;
        this.category = category;
    }

    public Product getProduct() {
        return product;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductJoinCategory)) return false;
        ProductJoinCategory that = (ProductJoinCategory) o;
        return Objects.equals(getProduct(), that.getProduct()) && Objects.equals(getCategory(), that.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduct(), getCategory());
    }

    @Override
    public String toString() {
        return "ProductJoinCategory{" +
                "product=" + product +
                ", category=" + category +
                '}';
    }
}
