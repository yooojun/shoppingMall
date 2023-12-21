package com.nhnacademy.shoppingmall.orders.repository;

import com.nhnacademy.shoppingmall.orders.domain.Orders;
import java.util.List;

public interface OrderRepository {
    Integer save(Orders orders);
    List<Orders> findByUserId(String  user_id);
    List<Orders> findAll();
}
