package com.example.IETI_11.model;


public class UserDto {
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String password;

    public UserDto(String name, String lastName, String email, String phoneNumber, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phoneNumber;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
}

