package com.example.springjpa.repository;

import com.example.springjpa.entity.Order;
import com.example.springjpa.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  Page<Order> findAllByCustomerCustomerId(Long customerId, Pageable pageable);
}
