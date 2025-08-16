package com.example.springjpa.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomerSortProperty {
  ID("customerId"),
  NAME("name");

  private final String field;
}
