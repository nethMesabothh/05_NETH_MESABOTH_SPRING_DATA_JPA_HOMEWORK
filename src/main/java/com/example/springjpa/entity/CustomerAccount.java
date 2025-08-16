package com.example.springjpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer_accounts", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
@Entity
public class CustomerAccount {

  @Id
  private Long customerId;

  @Column(name = "username", nullable = false, unique = true)
  @NotBlank(message = "Name cannot be blank")
  private String username;
  private String password;

  @Column(name = "is_active")
  private Boolean isActive;

  @OneToOne
  @MapsId
  @JoinColumn(name = "customer_id")
  private Customer customer;
}
