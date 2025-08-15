package com.example.springjpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class OrderItemId implements Serializable {
  @Column(name = "order_id")
  private Long orderId;

  @Column(name = "product_id")
  private Long productId;
}
