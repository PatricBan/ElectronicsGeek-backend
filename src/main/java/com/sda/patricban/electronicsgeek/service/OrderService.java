package com.sda.patricban.electronicsgeek.service;

import com.sda.patricban.electronicsgeek.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllOrders();

    Optional<Order> getOrderById(Long idOrder);

    Order updateOrder(Order order);

    Order addOrder(Order order);

    void deleteOrderByIdOrder(Long idOrder);
}
