package com.nhnacademy.shoppingmall.product.service;

import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.domain.ProductJoinCategory;
import java.util.List;

public interface ProductService {
    Product getProduct(Integer productId);
    void saveProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Integer productId);
    Product getProductByModelName(String modelName);
    Product getProductByModelNumber(String modelNumber);
    List<Product> findAll();

    List<Product> findProductById(List<Integer> productIdList);
}
