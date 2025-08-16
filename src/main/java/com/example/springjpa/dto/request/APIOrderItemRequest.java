package com.example.springjpa.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APIOrderItemRequest {
  private Long productId;
  private Integer quantity;
}