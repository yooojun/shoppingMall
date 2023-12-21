package com.nhnacademy.shoppingmall.category.repository.impl;

import com.nhnacademy.shoppingmall.category.domain.Category;
import com.nhnacademy.shoppingmall.category.repository.CategoryRepository;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CategoryRepositoryImpl implements CategoryRepository {
    @Override
    public Optional<Category> findCategoryById(Integer categoryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from Categories where CategoryID = ?";
        log.debug("sql : {} ", sql);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Category category = new Category(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName"),
                        rs.getInt("ParentCategoryID")
                );
                return Optional.of(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Category> findCategoryByCategoryName(String categoryName) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from Categories where CategoryName = ?";
        log.debug("sql : {} ", sql);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, categoryName);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Category category = new Category(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName"),
                        rs.getInt("ParentCategoryID")
                );
                return Optional.of(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Category> findCategoryByParentId(Integer categoryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from Categories where CategoryID = ?";
        log.debug("sql : {} ", sql);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Category category = new Category(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName"),
                        rs.getInt("ParentCategoryID")
                );
                return Optional.of(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public int save(Category category) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into Categories (CategoryID, CategoryName, ParentCategoryID ) values (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, category.getCategoryId());
            statement.setString(2, category.getCategoryName());
            if(category.getParentCategoryId() != 0){
                statement.setInt(3, category.getParentCategoryId());
            } else {
                statement.setString(3, null);
            }
            int result = statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int categoryID = rs.getInt(1);
                category.setCategoryId(categoryID);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByCategoryId(Integer categoryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from Categories where CategoryID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Category category) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "UPDATE Categories set CategoryName=?, ParentCategoryID=? where CategoryID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category.getCategoryName());
            if(category.getParentCategoryId() != 0){
                statement.setInt(2, category.getParentCategoryId());
            } else {
                statement.setString(2, null);
            }
            statement.setInt(3, category.getCategoryId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByCategoryId(Integer categoryID) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from Categories where categoryID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByCategoryName(String categoryName) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from Categories where categoryName = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, categoryName);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Category> findAll() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from Categories";
        List<Category> categoryList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName"),
                        rs.getInt("ParentCategoryId"));
                categoryList.add(category);
            }
            return categoryList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
