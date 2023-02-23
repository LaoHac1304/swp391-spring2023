package com.laohac.swp391spring2023.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laohac.swp391spring2023.model.entities.User;


public interface UserRepository extends JpaRepository<User, Integer>  {

    public Optional<User> findByEmail(String email);
    public Optional<User> findByUsername(String username);
    
}
