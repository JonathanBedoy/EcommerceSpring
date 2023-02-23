package com.jonathanbedoy.ecommerce.controllers;

import com.jonathanbedoy.controllers.UserController;
import com.jonathanbedoy.dtos.LoginRequest;
import com.jonathanbedoy.models.User;
import com.jonathanbedoy.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserControllerTesting {
    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;

    private User user;
    private User dbUser;
    private LoginRequest loginRequest;
    private final String fName = "Jonathan";
    private final String lName = "Bedoy";
    private final String email = "jon@gmail.com";
    private final String password = "secretPassword";
    private final Long id = 1l;

    @BeforeEach
    public void populateParams(){
        user = new User();
        user.setEmail(email);
        user.setFirstName(fName);
        user.setLastName(lName);
        user.setPassword(password);

        dbUser = new User(id, email,password, fName,lName);

        loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);
    }

    @Test
    public void givenUser_createUser_returnDBUser(){
        Mockito.when(userService.createUser(user)).thenReturn(dbUser);
        User returnedUser = userController.createUser(user);
        Assertions.assertEquals(returnedUser.getId(), dbUser.getId());
    }

    @Test
    public void givenUserId_findUser_returnDBUser(){
        Mockito.when(userService.findUserById(id)).thenReturn(dbUser);
        User returnedUser = userController.getUser(id);
        Assertions.assertEquals(returnedUser.getId(), dbUser.getId());
    }

    @Test
    public void givenUser_UpdateUser_returnUser(){
        Mockito.when(userService.updateUser(user)).thenReturn(dbUser);
        User returnedUser = userController.updateUser(user);
        Assertions.assertEquals(dbUser.getId(), returnedUser.getId());
    }

    @Test
    public void givenUserId_removeUser_returnTrue(){
        Mockito.when(userService.removeUserById(id)).thenReturn(true);
        boolean returnedBoolean = userController.deleteUser(id);
        Assertions.assertEquals(returnedBoolean, true);
    }

    @Test
    public void givenLoginRequest_login_returnUser(){
        Mockito.when(userService.login(loginRequest)).thenReturn(dbUser);
        User returnedUser = userController.login(loginRequest);
        Assertions.assertEquals(dbUser.getId(), returnedUser.getId());
    }
}
