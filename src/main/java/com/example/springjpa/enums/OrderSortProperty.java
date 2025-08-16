package com.example.springjpa.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderSortProperty {
  ID("orderId"),
  ORDER_DATE("orderDate"),
  TOTAL_AMOUNT("totalAmount");

  private final String field;
}
