package com.simplilearn.mihneapopa.service;

import com.simplilearn.mihneapopa.exceptions.OrderNotFoundException;
import com.simplilearn.mihneapopa.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAll();
    Order getOrderById(Integer id) throws OrderNotFoundException;
    Order saveOrder(Integer productId, Integer userId);
    Order updateOrder(Order order) throws OrderNotFoundException;
    void deleteOrder(Integer id) throws OrderNotFoundException;
}
