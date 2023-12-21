package com.nhnacademy.shoppingmall.prod_cate.repository;

import com.nhnacademy.shoppingmall.category.domain.Category;
import com.nhnacademy.shoppingmall.prod_cate.domain.Prod_Cate;
import com.nhnacademy.shoppingmall.product.domain.Product;
import java.util.List;
import java.util.Optional;

public interface Prod_CateRepository {
    int save(Prod_Cate prodCate);
    int deleteByProduct(Product product);
    int deleteByCategory(Category category);
    int deleteByProd_Cate(Prod_Cate prodCate);
    int update(Prod_Cate prodCate, Integer categoryId);
    int countByProductId(Integer productId);
    int countProd_Cate(Prod_Cate prodCate);

    List<Prod_Cate> findByProductId(Integer productId);
    List<Prod_Cate> findByCategoryId(Integer categoryId);

    Product getProductIdByProd_Cate(Prod_Cate prodCate);
}
