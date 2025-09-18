package com.example.IETI_11.controller;



import com.example.IETI_11.exeption.UserNotFoundException;
import com.example.IETI_11.model.User;
import com.example.IETI_11.service.UsersService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User ignore = usersService.crete(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    @GetMapping("/")
    public List<User> getAllUsers() {
        return usersService.getAllUsers();
    }
    @GetMapping("/{id}")
    public User findById(@PathVariable String id) throws UserNotFoundException {

        return usersService.findById(id).orElseThrow( () -> new UserNotFoundException(id));
    }
    @PutMapping("/{id}")
    public void updateUser(@PathVariable String id, @RequestBody User user) {
        usersService.updateUser(id,user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        User currentUser = usersService.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        usersService.deleteById(currentUser.getId());
    }

}
