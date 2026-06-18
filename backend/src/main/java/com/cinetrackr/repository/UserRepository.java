package com.cinetrackr.repository;

import com.cinetrackr.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    boolean existsByEmail(String email);
}