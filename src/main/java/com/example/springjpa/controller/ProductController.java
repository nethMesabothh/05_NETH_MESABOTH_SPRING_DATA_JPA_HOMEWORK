package com.example.springjpa.controller;

import com.example.springjpa.dto.payload.CustomerPayload;
import com.example.springjpa.dto.payload.PagedPayload;
import com.example.springjpa.dto.payload.ProductPayload;
import com.example.springjpa.dto.request.APICustomerRequest;
import com.example.springjpa.dto.request.APIProductRequest;
import com.example.springjpa.dto.response.APICustomerResponse;
import com.example.springjpa.dto.response.APIProductResponse;
import com.example.springjpa.enums.CustomerSortProperty;
import com.example.springjpa.enums.ProductSortProperty;
import com.example.springjpa.service.Interface.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

  private final IProductService productService;

  @GetMapping()
  public APIProductResponse<PagedPayload<ProductPayload>> getAllProducts(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "ID") ProductSortProperty sort, @RequestParam(defaultValue = "ASC") Sort.Direction direction) {
    return productService.getAllProducts(page, size, sort, direction);
  }


  @PostMapping()
  public APIProductResponse<ProductPayload> createProduct(@RequestBody APIProductRequest productRequest) {
    return productService.createProduct(productRequest);
  }

  @GetMapping("/{id}")
  public APIProductResponse<ProductPayload> getProductById(@PathVariable Long id) {
    return productService.getProductById(id);
  }

  @PutMapping("/{id}")
  private APIProductResponse<ProductPayload> updateProductById(@RequestBody APIProductRequest productRequest, @PathVariable Long id) {
    return productService.updateProductById(productRequest, id);
  }

  @DeleteMapping("/{id}")
  private String deleteProductById(@PathVariable Long id) {
    return productService.deleteProductById(id);
  }
}


