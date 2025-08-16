package com.example.springjpa.dto.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPayload {
  private Long productId;
  private String name;
  private Double unitPrice;
  private String description;
}