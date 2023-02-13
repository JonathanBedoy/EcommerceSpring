package com.jonathanbedoy.services;

import com.jonathanbedoy.models.Order;
import com.jonathanbedoy.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository oRepo;

    public Order createOrder(Order order) {
        return oRepo.save(order);
    }

    public Order findOrderById(Long id) {
        return oRepo.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public Order updateOrder (Order order) {
        return createOrder(order);
    }

    public boolean deleteOrder(Long id) {
        oRepo.deleteById(id);
        return true;
    }
}
