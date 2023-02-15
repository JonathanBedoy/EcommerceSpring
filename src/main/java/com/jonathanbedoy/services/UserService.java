package com.jonathanbedoy.services;

import com.jonathanbedoy.dtos.LoginRequest;
import com.jonathanbedoy.models.User;
import com.jonathanbedoy.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository urepo;

    public User createUser(User user) {
        return urepo.save(user);
    }

    public User findUserById(Long id) {
        return urepo.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public User updateUser (User user) {
        return createUser(user);
    }

    public boolean removeUserById(Long id) {
        urepo.deleteById(id);
        return true;
    }

    public User login(LoginRequest loginRequest) {
        User user = urepo.findByEmail(loginRequest.getEmail());

        if (user == null) return null;

        if (user.getPassword().equals(loginRequest.getPassword())) {
            return user;
        }
        return null;
    }
}
