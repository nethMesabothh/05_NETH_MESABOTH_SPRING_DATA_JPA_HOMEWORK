package com.example.springjpa.entity;

import com.example.springjpa.dto.payload.ProductPayload;
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

  public ProductPayload toResponse() {
    return new ProductPayload(
            this.product.getProductId(),
            this.product.getName(),
            this.product.getUnitPrice(),
            this.product.getDescription()
    );
  }

}
