package com.jonathanbedoy.ecommerce.controllers;

import com.jonathanbedoy.controllers.OrderController;
import com.jonathanbedoy.dtos.LoginRequest;
import com.jonathanbedoy.models.Item;
import com.jonathanbedoy.models.Order;
import com.jonathanbedoy.models.User;
import com.jonathanbedoy.services.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OrderControllerTesting {

    @InjectMocks
    private OrderController orderController;
    @Mock
    private OrderService orderService;

    private User user;
    private User dbUser;
    private LoginRequest loginRequest;
    private final String fName = "Jonathan";
    private final String lName = "Bedoy";
    private final String email = "jon@gmail.com";
    private final String password = "secretPassword";
    private final Long id = 1l;

    private Order order;
    private Order dbOrder;

    private List<Item> items;
    private Item item;
    private final String description = "A white hat";
    private final String name = "Hat";
    private final int quantity = 34;
    private final double price = 14.99;
    private final String date = "10/12/23";

    @BeforeEach
    public void populateOrderInfo() {
        user = new User();
        user.setEmail(email);
        user.setFirstName(fName);
        user.setLastName(lName);
        user.setPassword(password);

        dbUser = new User(id, email,password, fName,lName);

        loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        item = new Item();
        item.setDescription(description);
        item.setId(id);
        item.setName(name);
        item.setQuantity(quantity);
        item.setPrice(price);
        items = new ArrayList<>();
        items.add(item);
        order = new Order();
        order.setItems(items);
        order.setUser(user);
        order.setDate(date);
        order.setTotal(price);

        dbOrder = new Order(id, dbUser,date,price,items);

    }

    @Test
    public void givenOrder_createOrder_returnDBOrder(){
        Mockito.when(orderService.createOrder(order)).thenReturn(dbOrder);
        Order returnedOrder = orderController.createOrder(order);
        Assertions.assertEquals(returnedOrder.getId(), dbOrder.getId());
    }

    @Test
    public void givenOrderId_findOrder_returnDBOrder(){
        Mockito.when(orderService.findOrderById(id)).thenReturn(dbOrder);
        Order returnedOrder = orderController.getOrderById(id);
        Item returnedItem = returnedOrder.getItems().get(0);
        Assertions.assertEquals(returnedOrder.getId(), dbOrder.getId());
        Assertions.assertEquals(returnedItem.getId(), item.getId());
        Assertions.assertEquals(returnedItem.getName(), item.getName());
        Assertions.assertEquals(returnedItem.getPrice(), item.getPrice());
        Assertions.assertEquals(returnedItem.getDescription(), item.getDescription());
        Assertions.assertEquals(returnedItem.getQuantity(), item.getQuantity());

    }
//
    @Test
    public void givenOrder_UpdateOrder_returnDBOrder(){
        Mockito.when(orderService.updateOrder(order)).thenReturn(dbOrder);
        Order returnedOrder = orderController.updateOrder(order);
        Assertions.assertEquals(dbUser.getId(), returnedOrder.getId());
    }
//
    @Test
    public void givenOrderId_removeOrder_returnTrue(){
        Mockito.when(orderService.deleteOrder(id)).thenReturn(true);
        boolean returnedBoolean = orderController.deleteOrder(id);
        Assertions.assertEquals(returnedBoolean, true);
    }
//
//    @Test
//    public void givenLoginRequest_login_returnUser(){
//        Mockito.when(userService.login(loginRequest)).thenReturn(dbUser);
//        User returnedUser = userController.login(loginRequest);
//        Assertions.assertEquals(dbUser.getId(), returnedUser.getId());
//    }


}
