package com.example.springjpa.repository;

import com.example.springjpa.entity.OrderItem;
import com.example.springjpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
