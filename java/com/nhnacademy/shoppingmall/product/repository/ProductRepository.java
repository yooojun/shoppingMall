package com.nhnacademy.shoppingmall.product.repository;

import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.domain.ProductJoinCategory;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findProductById(Integer productId);
    Optional<Product> findProductByModelName(String modelName);
    Optional<Product> findProductByModelNumber(String modelNumber);
    int save(Product product);
    int deleteByProductId(Integer productId);
    int update(Product product);
    int countByProductId(Integer productId);
    List<Product> findAll();

}
