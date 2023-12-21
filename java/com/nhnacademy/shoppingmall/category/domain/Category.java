package com.nhnacademy.shoppingmall.category.domain;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
    private Integer categoryId;
    private String categoryName;
    private Integer parentCategoryId;


    public Category(Integer categoryId, String categoryName, Integer parentCategoryId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.parentCategoryId = parentCategoryId;
    }

    public Category(String categoryName) {
        this(0,categoryName, 0);
    }

    public Category(String categoryName, Integer parentCategoryId) {
        this(0, categoryName, parentCategoryId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equals(getCategoryId(), category.getCategoryId()) &&
                Objects.equals(getCategoryName(), category.getCategoryName()) &&
                Objects.equals(getParentCategoryId(), category.getParentCategoryId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategoryId(), getCategoryName(), getParentCategoryId());
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", parentCategoryId=" + parentCategoryId +
                '}';
    }
}
