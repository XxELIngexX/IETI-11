package com.example.IETI_11.controller;



import com.example.IETI_11.exeption.UserNotFoundException;
import com.example.IETI_11.model.User;
import com.example.IETI_11.service.UsersService;

import java.util.ArrayList;

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
        User ignore = usersService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    @GetMapping("/")
    public ArrayList<User> getAllUsers() {
        return usersService.getAllUsers();
    }
    @GetMapping("/{id}")
    public User findById(@PathVariable String id) throws UserNotFoundException {

        return usersService.findById(id).orElseThrow( () -> new UserNotFoundException(id));
    }
    @PutMapping("/{id}")
    public void updateUser(@PathVariable String id, @RequestBody User user) {
        User currentUser = usersService.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        // copiamos los valores del nuevo user al existente
        currentUser.setName(user.getName());
        currentUser.setLastName(user.getLastName());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhone(user.getPhone());

        usersService.save(currentUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        User currentUser = usersService.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        usersService.deleteById(currentUser.getId());
    }

}
