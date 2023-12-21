package com.nhnacademy.shoppingmall.orderdetail.service.impl;

import com.nhnacademy.shoppingmall.orderdetail.domain.OrderDetails;
import com.nhnacademy.shoppingmall.orderdetail.repository.OrderDetailRepository;
import com.nhnacademy.shoppingmall.orderdetail.repository.impl.OrderDetailRepositoryImpl;
import com.nhnacademy.shoppingmall.orderdetail.service.OrderDetailService;
import com.nhnacademy.shoppingmall.orders.domain.Orders;
import com.nhnacademy.shoppingmall.orders.service.OrderService;
import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailService {
    OrderDetailRepositoryImpl orderDetailRepository;

    public OrderDetailServiceImpl(OrderDetailRepositoryImpl orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }


    @Override
    public void save(OrderDetails orderDetails) {
        int result = orderDetailRepository.save(orderDetails);
        if(result < 1){
            throw new RuntimeException("save fail");
        }
    }

    @Override
    public List<OrderDetails> findByOrderId(Integer orderId) {
        List<OrderDetails> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if(orderDetailList.isEmpty()){
            return null;
        }
        return orderDetailList;
    }

    @Override
    public List<OrderDetails> findAll() {
        List<OrderDetails> orderDetailsList = orderDetailRepository.findAll();
        if(orderDetailsList.isEmpty()){
            return null;
        }
        return orderDetailsList;
    }
}
