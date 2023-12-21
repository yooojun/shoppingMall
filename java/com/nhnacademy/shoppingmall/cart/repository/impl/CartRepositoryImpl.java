package com.nhnacademy.shoppingmall.cart.repository.impl;

import com.nhnacademy.shoppingmall.cart.domain.Cart;
import com.nhnacademy.shoppingmall.cart.domain.Cart_Prod;
import com.nhnacademy.shoppingmall.cart.repository.CartRepository;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartRepositoryImpl implements CartRepository {
    @Override
    public Optional<Cart> findCartById(Integer cartId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from ShoppingCart where CartID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cartId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Cart cart = new Cart(rs.getInt("CartID"),
                        rs.getString("user_id"),
                        rs.getInt("Quantity"),
                        rs.getInt("ProductID"),
                        rs.getTimestamp("DateCreated").toLocalDateTime());
                return Optional.of(cart);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public int save(Cart cart) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into ShoppingCart (user_id, Quantity, ProductID, DateCreated) values (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, cart.getUserId());
            statement.setInt(2, cart.getQuantity());
            statement.setInt(3, cart.getProductId());
            statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            int result = statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                Integer cartId = rs.getInt(1);
                cart.setCartId(cartId);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByCartId(Integer cartId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from ShoppingCart where CartID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cartId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Cart cart) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "UPDATE ShoppingCart set Quantity = ? where CartID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cart.getQuantity());
            statement.setInt(2, cart.getCartId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByCartId(Integer cartId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from ShoppingCart where CartID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cartId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public List<Cart> findCartByUserId(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from ShoppingCart where user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();
            List<Cart> cartList = new ArrayList<>();
            while (rs.next()) {
                Cart cart = new Cart(rs.getInt("CartID"),
                        rs.getString("user_id"),
                        rs.getInt("Quantity"),
                        rs.getInt("ProductID"),
                        rs.getTimestamp("DateCreated").toLocalDateTime());
                cartList.add(cart);
            }
            return cartList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Cart> findAll() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from ShoppingCart";
        List<Cart> cartList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Cart cart = new Cart(rs.getInt("CartID"),
                        rs.getString("user_id"),
                        rs.getInt("Quantity"),
                        rs.getInt("ProductID"),
                        rs.getTimestamp("DateCreated").toLocalDateTime());
                cartList.add(cart);
            }
            return cartList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Cart_Prod> findCart_Prod(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from ShoppingCart join Products on ShoppingCart.ProductID = Products.ProductID  where ShoppingCart.user_id = ? Order by ShoppingCart.CartID DESC";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();
            List<Cart_Prod> cartProdList = new ArrayList<>();
            while (rs.next()) {
                Cart_Prod cartProd = new Cart_Prod(rs.getInt("CartId"),
                        rs.getString("ProductImage"),
                        rs.getString("ModelName"),
                        rs.getInt("UnitCost"),
                        rs.getInt("Quantity"));
                cartProdList.add(cartProd);
            }
            return cartProdList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer findCartByProductId(Integer productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from ShoppingCart where ProductID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

}
