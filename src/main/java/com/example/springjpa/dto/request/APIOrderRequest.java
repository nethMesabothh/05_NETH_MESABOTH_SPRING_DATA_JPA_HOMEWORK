package com.example.springjpa.dto.request;

import com.example.springjpa.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIOrderRequest {
  private List<APIOrderItemRequest> items;

}