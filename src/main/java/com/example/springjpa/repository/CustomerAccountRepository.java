package com.example.springjpa.repository;

import com.example.springjpa.entity.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {
  boolean existsByUsername(String username);

  Optional<CustomerAccount> findByUsername(String username);
}
