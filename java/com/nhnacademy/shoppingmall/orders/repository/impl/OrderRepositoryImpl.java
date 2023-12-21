package com.nhnacademy.shoppingmall.orders.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.orders.domain.Orders;
import com.nhnacademy.shoppingmall.orders.repository.OrderRepository;
import com.nhnacademy.shoppingmall.product.domain.Product;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public Integer save(Orders orders) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into Orders (OrderID, user_id, OrderDate, ShipDate) values (?, ?, ? ,?)";
        try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1, 0);
            statement.setString(2, orders.getUser_id());
            statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            statement.setTimestamp(4,Timestamp.valueOf(orders.getShipDate()));
            int result = statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                int orderID = rs.getInt(1);
                orders.setOrderId(orderID);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Orders> findByUserId(String  user_id) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from Orders where user_id =? ORDER BY OrderID ";
        List<Orders> orderList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user_id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                orderList.add(new Orders(
                        rs.getInt("OrderID"),
                        rs.getString("user_id"),
                        rs.getTimestamp("OrderDate").toLocalDateTime(),
                        rs.getTimestamp("ShipDate").toLocalDateTime()
                ));
            }
            return orderList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Orders> findAll() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from Orders ORDER BY OrderID ";
        List<Orders> orderList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                orderList.add(new Orders(
                        rs.getInt("OrderID"),
                        rs.getString("user_id"),
                        rs.getTimestamp("OrderDate").toLocalDateTime(),
                        rs.getTimestamp("ShipDate").toLocalDateTime()
                ));
            }
            return orderList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
