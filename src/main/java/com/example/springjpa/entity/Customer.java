package com.example.springjpa.entity;

import java.util.*;

import com.example.springjpa.dto.payload.CustomerPayload;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long customerId;

  private String name;
  private String address;

  @Column(name = "phone_number")
  private String phoneNumber;

  @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
  private CustomerAccount account;

  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<Order> orders;

  public CustomerPayload toResponse() {
    return new CustomerPayload(
            this.customerId,
            this.name,
            this.address,
            this.phoneNumber,
            (this.account != null) ? this.account.getUsername() : null,
            (this.account != null) ? this.account.getPassword() : null,
            (this.account != null) ? this.account.getIsActive() : false
    );
  }
}
