package com.jonathanbedoy.ecommerce.services;

import com.jonathanbedoy.dtos.LoginRequest;
import com.jonathanbedoy.models.User;
import com.jonathanbedoy.repositories.UserRepository;
import com.jonathanbedoy.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserServiceTesting {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User user;
    private User dbUser;
    private LoginRequest loginRequest;
    private final String fName = "Jonathan";
    private final String lName = "Bedoy";
    private final String email = "jon@gmail.com";
    private final String password = "secretPassword";
    private final Long id = 1l;




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
    }

    @Test
    public void givenUser_createUser_returnUser(){
        Mockito.when(userRepository.save(user)).thenReturn(dbUser);

        User returnedUsed = userService.createUser(user);

        Assertions.assertEquals(dbUser.getId(), returnedUsed.getId());
        Assertions.assertEquals(dbUser.getEmail(), returnedUsed.getEmail());
        Assertions.assertEquals(dbUser.getFirstName(), returnedUsed.getFirstName());
        Assertions.assertEquals(dbUser.getLastName(), returnedUsed.getLastName());
        Assertions.assertEquals(dbUser.getPassword(), returnedUsed.getPassword());

    }

    @Test
    public void givenUserId_findUserInDB_returnUser() {
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(dbUser));
        User returnedUsed = userService.findUserById(id);

        Assertions.assertEquals(dbUser.getId(), returnedUsed.getId());
    }

    @Test
    public void givenUser_updateUserInDB_returnUser(){
        Mockito.when(userRepository.save(user)).thenReturn(dbUser);
        User returnedUsed = userService.updateUser(user);

        Assertions.assertEquals(dbUser.getId(), returnedUsed.getId());
    }

    @Test
    public void givenUserId_removeUserFromDB_returnTrue(){
        boolean returnedBoolean = userService.removeUserById(id);

        Assertions.assertEquals(true, returnedBoolean);
    }

    @Test
    public void givenUserEmailPassword_verifyIfCorrect_returnUser(){
        Mockito.when(userRepository.findByEmail(email)).thenReturn(dbUser);
        User returnedUsed = userService.login(loginRequest);

        Assertions.assertEquals(dbUser.getId(), returnedUsed.getId());
    }

}
