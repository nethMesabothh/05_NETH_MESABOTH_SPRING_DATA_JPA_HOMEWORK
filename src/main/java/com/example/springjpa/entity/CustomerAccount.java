package com.example.springjpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer_accounts")
@Entity
public class CustomerAccount {

  @Id
  private Long customerId;

  private String username;
  private String password;

  @Column(name = "is_active")
  private Boolean isActive;

  @OneToOne
  @MapsId
  @JoinColumn(name = "customer_id")
  private Customer customer;
}
