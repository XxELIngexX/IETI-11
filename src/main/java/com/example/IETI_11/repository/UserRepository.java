package com.example.IETI_11.repository;

import com.example.IETI_11.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {
}