package com.nhnacademy.shoppingmall.orderdetail.service;

import com.nhnacademy.shoppingmall.orderdetail.domain.OrderDetails;
import java.util.List;

public interface OrderDetailService {
    void save (OrderDetails orderDetails);
    List<OrderDetails> findByOrderId(Integer orderId);
    List<OrderDetails> findAll();
}
