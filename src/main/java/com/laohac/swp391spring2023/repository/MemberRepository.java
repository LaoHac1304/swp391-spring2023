package com.laohac.swp391spring2023.repository;

import java.util.Optional;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.laohac.swp391spring2023.model.entities.User;



public interface MemberRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByUsername(String username);
    public List<User> findAllByRole(String role);
    
}
