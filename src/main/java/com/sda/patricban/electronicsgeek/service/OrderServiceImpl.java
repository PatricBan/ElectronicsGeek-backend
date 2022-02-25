package com.sda.patricban.electronicsgeek.service;


import com.sda.patricban.electronicsgeek.model.Order;
import com.sda.patricban.electronicsgeek.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService{


    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Long idOrder) {
        Optional<Order> order = orderRepository.findByIdOrder(idOrder);
        if(!(order.isPresent())){
            throw new IllegalArgumentException("OrderNotFound");
        }
        return order;
    }

    @Override
    public Order updateOrder(Order order) {
        Optional<Order> orderFromDb = orderRepository.findByIdOrder(order.getIdOrder());
        if(!(orderFromDb.isPresent())){
            throw new IllegalArgumentException("Order not found");
        }
        return orderRepository.save(order);
    }

    @Override
    public Order addOrder(Order order) {
        Optional<Order> orderFromDb = orderRepository.findByIdOrder(order.getIdOrder());
        if(orderFromDb.isPresent()){
            throw new IllegalArgumentException("Order already exists");
        }
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrderByIdOrder(Long idOrder) {
        Optional<Order> orderFromDb = orderRepository.findByIdOrder(idOrder);
        if(!(orderFromDb.isPresent())){
            throw new IllegalArgumentException("Order not found");
        }
        orderRepository.deleteById(idOrder);
    }
}
