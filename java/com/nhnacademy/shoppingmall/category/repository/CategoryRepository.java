package com.nhnacademy.shoppingmall.category.repository;

import com.nhnacademy.shoppingmall.category.domain.Category;
import com.nhnacademy.shoppingmall.user.domain.User;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Optional<Category> findCategoryById(Integer categoryId);
    Optional<Category> findCategoryByCategoryName(String categoryName);

    Optional<Category> findCategoryByParentId(Integer categoryId);
    int save(Category category);
    int deleteByCategoryId(Integer categoryId);
    int update(Category category);
    int countByCategoryId(Integer category);
    int countByCategoryName(String categoryName);

    List<Category> findAll();
}
