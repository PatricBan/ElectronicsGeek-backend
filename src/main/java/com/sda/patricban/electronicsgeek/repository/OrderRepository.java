package com.sda.patricban.electronicsgeek.repository;

import com.sda.patricban.electronicsgeek.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();
    Optional<Order> findByIdOrder(Long idOrder);
    Optional<Order> findByOrderDate(Long idOrder);

}
