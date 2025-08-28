package com.example.IETI_11.repository;

import com.example.IETI_11.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}