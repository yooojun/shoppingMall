package com.nhnacademy.shoppingmall.category.service;

import com.nhnacademy.shoppingmall.category.domain.Category;
import com.nhnacademy.shoppingmall.product.domain.Product;
import java.util.List;

public interface CategoryService {
    Category getCategory(Integer categoryId);
    Category getCategoryByCategoryName(String categoryName);
    Category getCategoryByParentCategoryNumber(Integer parentID);
    void saveCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(Integer categoryId);

    List<Category> findAll();
}
