package com.example.springjpa.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductSortProperty {
  UNIT_PRICE("unitPrice"),
  ID("productId"),
  NAME("name");

  private final String field;
}
