package com.jonathanbedoy.controllers;

import com.jonathanbedoy.models.Order;
import com.jonathanbedoy.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
@CrossOrigin("http://localhost:4200")

public class OrderController {
    @Autowired
    private final OrderService oService;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return oService.createOrder(order);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return oService.findOrderById(id);
    }

    @PutMapping
    public Order updateOrder(@RequestBody Order order) {
        return oService.updateOrder(order);
    }

    @DeleteMapping("{id}")
    public Boolean deleteOrder(@PathVariable Long id) {
        return oService.deleteOrder(id);
    }
}
