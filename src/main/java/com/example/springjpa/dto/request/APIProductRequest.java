package com.example.springjpa.dto.request;

import com.example.springjpa.entity.Customer;
import com.example.springjpa.entity.CustomerAccount;
import com.example.springjpa.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIProductRequest {
  private String name;
  private Double unitPrice;
  private String description;


  public Product toEntity() {
    Product product = new Product();
    product.setName(this.name);
    product.setUnitPrice(this.unitPrice);
    product.setDescription(this.description);

    return product;
  }

  public Product toUpdateEntity(Product existingProduct) {
    existingProduct.setName(this.name);
    existingProduct.setUnitPrice(this.unitPrice);
    existingProduct.setDescription(this.description);

    return existingProduct;
  }
}