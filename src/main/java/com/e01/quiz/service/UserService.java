package com.e01.quiz.service;


import com.e01.quiz.entity.User;
import com.e01.quiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
    }

    public User getUserByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new RuntimeException("user not found"));
    }


}