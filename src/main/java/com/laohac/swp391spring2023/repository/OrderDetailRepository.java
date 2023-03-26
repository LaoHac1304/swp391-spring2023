package com.laohac.swp391spring2023.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.laohac.swp391spring2023.model.Status;
import com.laohac.swp391spring2023.model.entities.OrderDetail;
import com.laohac.swp391spring2023.model.entities.User;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    public Optional<List<OrderDetail>> findByCustomer(User user);

    public Optional<OrderDetail> findById(int id);
    public List<OrderDetail> findByStatus(Status status);

    
}
