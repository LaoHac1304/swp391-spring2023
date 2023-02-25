package com.laohac.swp391spring2023.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.laohac.swp391spring2023.model.entities.User;


public interface UserRepository extends JpaRepository<User, Integer>  {

    public Optional<User> findByEmail(String email);
    public Optional<User> findByUsername(String username);
    /*  @Transactional
    @Query(value = "UPDATE User u SET u.enabled = 1 WHERE u.id = ?1", nativeQuery = true)
    public void enable(Integer id); */
   

    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    public User findByVerificationCode (String code);
    
}
