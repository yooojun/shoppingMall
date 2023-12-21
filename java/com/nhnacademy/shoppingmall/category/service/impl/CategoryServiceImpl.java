package com.nhnacademy.shoppingmall.category.service.impl;

import com.nhnacademy.shoppingmall.category.domain.Category;
import com.nhnacademy.shoppingmall.category.exception.CategoryAlreadyExistsException;
import com.nhnacademy.shoppingmall.category.exception.CategoryNotFoundException;
import com.nhnacademy.shoppingmall.category.repository.CategoryRepository;
import com.nhnacademy.shoppingmall.category.service.CategoryService;
import com.nhnacademy.shoppingmall.product.exception.ProductAlreadyExistsException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategory(Integer categoryId) {
        Optional<Category> category = categoryRepository.findCategoryById(categoryId);
        if(category.isEmpty()){
            return null;
        }
        return category.get();
    }

    @Override
    public Category getCategoryByCategoryName(String categoryName) {
        Optional<Category> category = categoryRepository.findCategoryByCategoryName(categoryName);
        if(category.isEmpty()){
            return null;
        }
        return category.get();
    }

    @Override
    public Category getCategoryByParentCategoryNumber(Integer parentID) {
        Optional<Category> category = categoryRepository.findCategoryByParentId(parentID);
        if(category.isEmpty()){
            return null;
        }
        return category.get();
    }

    @Override
    public void saveCategory(Category category) {
        if (categoryRepository.countByCategoryName(category.getCategoryName()) > 0) {
            throw new CategoryAlreadyExistsException(category.getCategoryId());
        }
        int result = categoryRepository.save(category);
        if (result < 1) {
            throw new RuntimeException("save fail");
        }
    }

    @Override
    public void updateCategory(Category category) {
        if (categoryRepository.countByCategoryId(category.getCategoryId())< 1) {
            throw new CategoryNotFoundException(category.getCategoryId());
        }
        int result = categoryRepository.update(category);
        if (result < 1) {
            throw new RuntimeException("update fail");
        }
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        if (categoryRepository.countByCategoryId(categoryId) < 1) {
            throw new CategoryNotFoundException(categoryId);
        }
        int result = categoryRepository.deleteByCategoryId(categoryId);
        if (result < 1) {
            throw new RuntimeException("delete fail");
        }
    }

    @Override
    public List<Category> findAll() {
        List<Category> categoryList = categoryRepository.findAll();
        if(categoryList.isEmpty()){
            return Collections.emptyList();
        }
        return categoryList;
    }
}
