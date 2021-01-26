package com.example.demo.db.repository;

import com.example.demo.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  User findByEmail(String email);
}
