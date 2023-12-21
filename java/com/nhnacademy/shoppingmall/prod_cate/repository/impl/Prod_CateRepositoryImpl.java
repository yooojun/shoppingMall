package com.nhnacademy.shoppingmall.prod_cate.repository.impl;

import com.nhnacademy.shoppingmall.category.domain.Category;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.prod_cate.domain.Prod_Cate;
import com.nhnacademy.shoppingmall.prod_cate.repository.Prod_CateRepository;
import com.nhnacademy.shoppingmall.product.domain.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Prod_CateRepositoryImpl implements Prod_CateRepository {

    @Override
    public int save(Prod_Cate prodCate) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into Product_Category values(?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1,prodCate.getProductId());
            statement.setInt(2, prodCate.getCategoryId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            log.debug("sql = {]",sql);
            throw new RuntimeException(e);
        }

    }




    @Override
    public int deleteByProduct(Product product) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from Product_Category where ProductID = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, product.getProductId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            log.debug("sql={}", sql);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByCategory(Category category) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from Product_Category where CategoryID = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, category.getCategoryId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            log.debug("sql={}", sql);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByProd_Cate(Prod_Cate prodCate) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from Product_Category where ProductID = ? and CategoryID = ";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, prodCate.getProductId());
            statement.setInt(2, prodCate.getCategoryId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            log.debug("sql={}", sql);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Prod_Cate prodCate, Integer categoryId) {
        Connection connection =DbConnectionThreadLocal.getConnection();
        String sql = "update Product_Category set CategoryID = ? where ProductID =? and CategoryID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, categoryId);
            statement.setInt(2, prodCate.getProductId());
            statement.setInt(3, prodCate.getCategoryId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            log.debug("sql={}",sql );
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByProductId(Integer productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from Product_Category where ProductID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,productId);
            ResultSet rs = statement.executeQuery();
            int count = 0;
            if(rs.next()){
                count = rs.getInt(1);
            }
            return count;
        } catch (SQLException e) {
            log.debug("sql ={}", sql);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countProd_Cate(Prod_Cate prodCate) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from Product_Category where ProductID = ? and CategoryId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,prodCate.getProductId());
            statement.setInt(2, prodCate.getCategoryId());
            ResultSet rs = statement.executeQuery();
            int count =0;
            if(rs.next()){
                count = rs.getInt(1);
            }
            return count;
        } catch (SQLException e) {
            log.debug("sql:{}",sql);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Prod_Cate> findByProductId(Integer productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from Product_Category where ProductID =? ";
        List<Prod_Cate> prod_cateList = new ArrayList<>();
        try (PreparedStatement statement =connection.prepareStatement(sql)){
            statement.setInt(1, productId);
            ResultSet rs =statement.executeQuery();
            while (rs.next()){
                prod_cateList.add(new Prod_Cate(rs.getInt("ProductID"),
                        rs.getInt("CategoryID")));
            }
            return prod_cateList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Prod_Cate> findByCategoryId(Integer categoryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from Product_Category where CategoryID =? ";
        List<Prod_Cate> prod_cateList = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryId);
            ResultSet rs =statement.executeQuery();
            while (rs.next()){
                prod_cateList.add(new Prod_Cate(rs.getInt("ProductID"),
                        rs.getInt("CategoryID")));
            }
            return prod_cateList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getProductIdByProd_Cate(Prod_Cate prodCate) {

        return null;
    }
}
