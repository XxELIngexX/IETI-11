package com.example.IETI_11.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.IETI_11.exeption.UserNotFoundException;
import com.example.IETI_11.model.User;
import com.example.IETI_11.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
        userRepository.save(user);
        return user;
    }

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }
    public void updateUser(String id, User user) {
        Optional<User> old = userRepository.findById(id);
        old = Optional.ofNullable(user);
        userRepository.save(old.orElse(user));

    }
    public void deleteById(String id) {

        userRepository.deleteById(id);
    }


    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
