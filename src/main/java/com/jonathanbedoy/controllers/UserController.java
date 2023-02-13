package com.jonathanbedoy.controllers;

import com.jonathanbedoy.models.User;
import com.jonathanbedoy.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService uService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return uService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@RequestParam Long id) {
        return uService.findUserById(id);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return uService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteUser(@RequestParam Long id) {
        return uService.removeUserById(id);
    }
}
