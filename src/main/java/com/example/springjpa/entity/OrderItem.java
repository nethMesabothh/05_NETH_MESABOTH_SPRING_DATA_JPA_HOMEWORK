package com.example.springjpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_items")
@Entity
public class OrderItem {

  @EmbeddedId
  private OrderItemId id;

  @ManyToOne
  @MapsId("orderId")
  @JoinColumn(name = "order_id")
  private Order order;

  @ManyToOne
  @MapsId("productId")
  @JoinColumn(name = "product_id")
  private Product product;

  private Integer quantity;

}
