package com.nhnacademy.shoppingmall.orderdetail.repository;

import com.nhnacademy.shoppingmall.orderdetail.domain.OrderDetails;
import java.util.List;

public interface OrderDetailRepository {
    int save(OrderDetails orderDetails);
    List<OrderDetails> findByOrderId(Integer orderId);
    List<OrderDetails> findAll();
}
