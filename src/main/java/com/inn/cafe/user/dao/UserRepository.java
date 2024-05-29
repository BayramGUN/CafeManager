package com.inn.cafe.user.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inn.cafe.user.data.User;

public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByEmail(String email);
  Optional<User> findByUsername(String username);
  Optional<User> findByContactNumber(String contactNumber);
  Boolean existsByEmail(String email);
  Boolean existsByContactNumber(String contactNumber);
  Boolean existsByUsername(String username);
}

