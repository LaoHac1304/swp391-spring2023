package com.laohac.swp391spring2023.repository;

import java.util.Optional;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laohac.swp391spring2023.model.entities.User;


@Repository
public interface MemberRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByUsername(String username);
    public List<User> findAllByRole(String role);
    
}
