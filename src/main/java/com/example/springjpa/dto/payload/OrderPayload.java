package com.example.springjpa.dto.payload;

import com.example.springjpa.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPayload {
  private Long orderId;
  private LocalDateTime orderDate;
  private BigDecimal totalAmount;
  private OrderStatus status;

  private CustomerPayload customerResponse;
  private List<ProductPayload> productResponses;
}