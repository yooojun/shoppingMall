package com.nhnacademy.shoppingmall.product.repository.impl;

import com.nhnacademy.shoppingmall.category.domain.Category;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.domain.ProductJoinCategory;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public Optional<Product> findProductById(Integer productId) {

        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from Products where ProductID = ?";
        log.debug("sql : {} ", sql);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Product product = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ModelNumber"),
                        rs.getString("ModelName"),
                        rs.getString("ProductImage"),
                        rs.getInt("UnitCost"),
                        rs.getString("Description")
                );
                return Optional.of(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Product> findProductByModelName(String modelName){
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from Products where ModelName = ?";
        log.debug("sql : {} ", sql);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, modelName);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Product product = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ModelNumber"),
                        rs.getString("ModelName"),
                        rs.getString("ProductImage"),
                        rs.getInt("UnitCost"),
                        rs.getString("Description")
                );
                return Optional.of(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Product> findProductByModelNumber(String modelNumber){
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from Products where ModelNumber = ?";
        log.debug("sql : {} ", sql);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, modelNumber);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Product product = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ModelNumber"),
                        rs.getString("ModelName"),
                        rs.getString("ProductImage"),
                        rs.getInt("UnitCost"),
                        rs.getString("Description")
                );
                return Optional.of(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public int save(Product product) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into Products ( ModelNumber, ModelName, ProductImage, UnitCost, Description) values (?, ?, ? ,?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, product.getModelNumber());
            statement.setString(2, product.getModelName());
            statement.setString(3, product.getProductImage());
            statement.setInt(4, product.getUnitCost());
            statement.setString(5, product.getDescription());

            int result = statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                int productID = rs.getInt(1);
                product.setProductId(productID);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByProductId(Integer productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from Products where ProductID = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Product product) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "UPDATE Products set ModelNumber=? , ModelName=?, ProductImage=?, UnitCost=?, Description=? where ProductID=?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, product.getModelNumber());
            statement.setString(2, product.getModelName());
            statement.setString(3, product.getProductImage());
            statement.setInt(4, product.getUnitCost());
            statement.setString(5, product.getDescription());
            statement.setInt(6, product.getProductId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByProductId(Integer productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from Products where ProductID = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findAll() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from Products ORDER BY ProductID DESC ";
        List<Product> productList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ModelNumber"),
                        rs.getString("ModelName"),
                        rs.getString("ProductImage"),
                        rs.getInt("UnitCost"),
                        rs.getString("Description"));
                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
