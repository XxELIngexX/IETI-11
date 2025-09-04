package com.example.IETI_11.service;

import java.util.ArrayList;
import java.util.Optional;

import com.example.IETI_11.exeption.UserNotFoundException;
import com.example.IETI_11.model.User;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private ArrayList<User> users = new ArrayList<>();

    public User save(User user) {
        users.add(user);
        return user;
    }

    public ArrayList<User> getAllUsers() {

        return users;
    }

    public Optional<User> findById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return Optional.of(user);
            }
        }
        return Optional.empty(); // User not found
    }
    public void updateUser(String id, User user) {
        User currentUser = findById(id).get();
        if (currentUser != null){
            currentUser = user;
            save(currentUser);
        }
        else {
            throw new UserNotFoundException("User not found");
        }
    }
    public void deleteById(String id) {
        users.remove(findById(id).get());
    }


}
