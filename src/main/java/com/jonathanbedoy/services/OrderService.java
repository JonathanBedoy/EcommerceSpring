package com.jonathanbedoy.services;

import com.jonathanbedoy.models.Item;
import com.jonathanbedoy.models.Order;
import com.jonathanbedoy.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private final OrderRepository oRepo;

    public Order createOrder(Order order) {
//        List<Item> items = order.getItems();
//        order.setItems(new ArrayList<>());
//        oRepo.save(order);
//        order.setItems(items);
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
