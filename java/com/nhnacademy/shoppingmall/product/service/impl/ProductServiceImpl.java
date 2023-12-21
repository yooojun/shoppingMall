package com.nhnacademy.shoppingmall.product.service.impl;

import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.domain.ProductJoinCategory;
import com.nhnacademy.shoppingmall.product.exception.ProductAlreadyExistsException;
import com.nhnacademy.shoppingmall.product.exception.ProductNotFoundException;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProduct(Integer productId) {
        Optional<Product> product = productRepository.findProductById(productId);
        if (product.isEmpty()) {
            return null;
        }
        return product.get();
    }

    @Override
    public Product getProductByModelName(String modelName) {
        Optional<Product> product = productRepository.findProductByModelName(modelName);
        if (product.isEmpty()) {
            return null;
        }
        return product.get();
    }

    @Override
    public Product getProductByModelNumber(String modelNumber) {
        Optional<Product> product = productRepository.findProductByModelName(modelNumber);
        if (product.isEmpty()) {
            return null;
        }
        return product.get();
    }

    @Override
    public List<Product> findAll() {
        List<Product> productList = productRepository.findAll();
        if (productList.isEmpty()) {
            return null;
        }
        return productList;

    }

    @Override
    public List<Product> findProductById(List<Integer> productIdList) {

        List<Product> productList = new ArrayList<>();
        for (Integer id : productIdList) {
            productList.add(productRepository.findProductById(id).get());
        }

        return productList;
    }


    @Override
    public void saveProduct(Product product) {
        if (productRepository.countByProductId(product.getProductId()) > 0) {
            throw new ProductAlreadyExistsException(product.getProductId());
        }
        int result = productRepository.save(product);
        if (result < 1) {
            throw new RuntimeException("save fail");
        }
    }

    @Override
    public void updateProduct(Product product) {
        if (productRepository.countByProductId(product.getProductId()) < 1) {
            throw new ProductNotFoundException(product.getProductId());
        }
        int result = productRepository.update(product);
        if (result < 1) {
            throw new RuntimeException("update fail");
        }
    }

    @Override
    public void deleteProduct(Integer productId) {
        if (productRepository.countByProductId(productId) < 1) {
            throw new ProductNotFoundException(productId);
        }
        int result = productRepository.deleteByProductId(productId);
        if (result < 1) {
            throw new RuntimeException("delete fail");
        }

    }


}
