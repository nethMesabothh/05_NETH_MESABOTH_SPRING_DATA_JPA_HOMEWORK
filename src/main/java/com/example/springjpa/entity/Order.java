package com.example.springjpa.entity;

import com.example.springjpa.dto.payload.OrderPayload;
import com.example.springjpa.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@Entity
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long orderId;

  @Column(name = "order_date")
  private LocalDateTime orderDate;

  @Column(name = "total_amount")
  private BigDecimal totalAmount;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderItem> orderItems = new ArrayList<>();

  public OrderPayload toResponse() {
    return new OrderPayload(
            this.orderId,
            this.orderDate,
            this.totalAmount,
            this.status,
            this.customer != null ? this.customer.toResponse() : null,
            this.orderItems != null
                    ? this.orderItems.stream().map(OrderItem::toResponse).toList()
                    : Collections.emptyList()
    );
  }

}
