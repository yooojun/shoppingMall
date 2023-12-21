package com.nhnacademy.shoppingmall.orderdetail.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.orderdetail.domain.OrderDetails;
import com.nhnacademy.shoppingmall.orderdetail.repository.OrderDetailRepository;
import com.nhnacademy.shoppingmall.orders.domain.Orders;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailRepositoryImpl implements OrderDetailRepository {
    @Override
    public int save(OrderDetails orderDetails) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into OrderDetails (OrderID, ProductID, Quantity, UnitCost) values (?, ?, ? ,?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, orderDetails.getOrderId());
            statement.setInt(2, orderDetails.getProductId());
            statement.setInt(3, orderDetails.getQuantity());
            statement.setInt(4, orderDetails.getUnitCost());
            int result = statement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderDetails> findByOrderId(Integer orderId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from OrderDetails where OrderID =? ORDER BY OrderID ";
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                orderDetailsList.add(new OrderDetails(
                        rs.getInt("OrderID"),
                        rs.getInt("ProductID"),
                        rs.getInt("Quantity"),
                        rs.getInt("UnitCost")
                ));
            }
            return orderDetailsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderDetails> findAll() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from OrderDetails ORDER BY OrderID ";
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                orderDetailsList.add(new OrderDetails(
                        rs.getInt("OrderID"),
                        rs.getInt("ProductID"),
                        rs.getInt("Quantity"),
                        rs.getInt("UnitCost")
                ));
            }
            return orderDetailsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
