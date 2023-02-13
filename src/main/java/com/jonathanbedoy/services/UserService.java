package com.jonathanbedoy.services;

import com.jonathanbedoy.models.User;
import com.jonathanbedoy.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
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
}