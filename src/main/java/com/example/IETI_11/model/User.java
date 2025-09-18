package com.example.IETI_11.model;

import com.example.IETI_11.service.UsersService;

public class User {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private UsersService usersService = new UsersService();

    // Constructor
    public User() {}
    public User(String id, String name, String lastName, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
    public User(UserDto dto) {
        this.id = String.valueOf((usersService.getAllUsers().size() + 1));
        this.name = dto.getName();
        this.lastName = dto.getLastName();
        this.email = dto.getEmail();
        this.phone = dto.getPhone();
        this.password = dto.getPassword();
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
