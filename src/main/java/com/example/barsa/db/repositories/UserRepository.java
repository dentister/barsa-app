package com.example.barsa.db.repositories;

import com.example.barsa.db.entities.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByLogin(String login);
}
