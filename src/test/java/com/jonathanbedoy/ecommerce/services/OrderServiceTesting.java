package com.jonathanbedoy.ecommerce.services;

import com.jonathanbedoy.dtos.LoginRequest;
import com.jonathanbedoy.models.Item;
import com.jonathanbedoy.models.Order;
import com.jonathanbedoy.models.User;
import com.jonathanbedoy.repositories.OrderRepository;
import com.jonathanbedoy.repositories.UserRepository;
import com.jonathanbedoy.services.OrderService;
import com.jonathanbedoy.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrderServiceTesting {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

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
    public void populateUserInfo() {
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
    public void givenOrder_createOrder_returnOrder(){
        Mockito.when(orderRepository.save(order)).thenReturn(dbOrder);

        Order returnedOrder = orderService.createOrder(order);

        Assertions.assertEquals(dbOrder.getId(), returnedOrder.getId());
        Assertions.assertEquals(dbOrder.getUser(), returnedOrder.getUser());
        Assertions.assertEquals(dbOrder.getDate(), returnedOrder.getDate());
        Assertions.assertEquals(dbOrder.getTotal(), returnedOrder.getTotal());
        Assertions.assertEquals(dbOrder.getItems(), returnedOrder.getItems());


    }

    @Test
    public void givenOrderId_findOrderInDB_returnOrder() {
        Mockito.when(orderRepository.findById(id)).thenReturn(Optional.of(dbOrder));
        Order returnedOrder = orderService.findOrderById(id);

        Assertions.assertEquals(dbOrder.getId(), returnedOrder.getId());
    }
//
    @Test
    public void givenOrder_updateOrderInDB_returnOrder(){
        Mockito.when(orderRepository.save(order)).thenReturn(dbOrder);
        Order returnedOrder = orderService.updateOrder(order);

        Assertions.assertEquals(dbOrder.getId(), returnedOrder.getId());
    }
//
    @Test
    public void givenOrderId_removeOrderFromDB_returnTrue(){
        boolean returnedBoolean = orderService.deleteOrder(id);

        Assertions.assertEquals(true, returnedBoolean);
    }

}
