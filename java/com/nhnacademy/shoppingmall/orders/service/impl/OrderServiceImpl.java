package com.nhnacademy.shoppingmall.orders.service.impl;

import com.nhnacademy.shoppingmall.orders.domain.Orders;
import com.nhnacademy.shoppingmall.orders.repository.OrderRepository;
import com.nhnacademy.shoppingmall.orders.repository.impl.OrderRepositoryImpl;
import com.nhnacademy.shoppingmall.orders.service.OrderService;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepositoryImpl orderRepository) {
       this.orderRepository = orderRepository;
    }

    @Override
    public void save(Orders orders) {
        Integer result = orderRepository.save(orders);
        if(result<1){
            throw new RuntimeException("save fail");
        }
    }

    @Override
    public List<Orders> findByUserId(String user_id) {
        List<Orders> orderList = orderRepository.findByUserId(user_id);
        if(orderList.isEmpty())
            return null;
        return orderList;
    }

    @Override
    public List<Orders> findAll() {
        List<Orders> orderList = orderRepository.findAll();
        if(orderList.isEmpty())
            return null;
        return orderList;
    }
}
