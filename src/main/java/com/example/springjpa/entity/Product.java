package com.example.springjpa.entity;

import lombok.*;
import jakarta.persistence.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long product_id;

  private String name;

  @Column(name = "unit_price")
  private Double unitPrice;

  private String description;

  @OneToMany(mappedBy = "product")
  private List<OrderItem> orderItems;

}
