package com.laohac.swp391spring2023.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laohac.swp391spring2023.model.entities.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    public Optional<Member> findByUsername(String username);
    
}
