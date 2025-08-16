package com.example.springjpa.entity;

import com.example.springjpa.dto.payload.CustomerPayload;
import com.example.springjpa.dto.payload.ProductPayload;
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
  private Long productId;

  private String name;

  @Column(name = "unit_price")
  private Double unitPrice;

  private String description;

  @OneToMany(mappedBy = "product")
  private List<OrderItem> orderItems;

  public ProductPayload toResponse() {
    return new ProductPayload(
            this.productId,
            this.name,
            this.unitPrice,
            this.description
    );
  }

}
