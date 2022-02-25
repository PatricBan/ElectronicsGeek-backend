package com.sda.patricban.electronicsgeek.controller;

import com.sda.patricban.electronicsgeek.model.Order;
import com.sda.patricban.electronicsgeek.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/orders")
@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/allOrders")
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orderList = orderService.getAllOrders();
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @GetMapping("/getOrder/{idOrder}")
    public ResponseEntity<Optional<Order>> getOrder(@PathVariable Long idOrder){
        try {
            Optional<Order> orderFromDb = orderService.getOrderById(idOrder);
            return new ResponseEntity<>(orderFromDb, HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createOrder")
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        Order createdOrder = orderService.addOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.OK);
    }

    @PutMapping("/updateOrder")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order){
        Order updatedOrder = orderService.updateOrder(order);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(updatedOrder);
    }

    @DeleteMapping("/deleteOrder/{idOrder}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long idOrder){
        orderService.deleteOrderByIdOrder(idOrder);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
