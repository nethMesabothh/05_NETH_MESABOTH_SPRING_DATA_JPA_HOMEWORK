package com.example.springjpa.service.Interface;

import com.example.springjpa.dto.payload.PagedPayload;
import com.example.springjpa.dto.payload.ProductPayload;
import com.example.springjpa.dto.request.APIProductRequest;
import com.example.springjpa.dto.response.APIProductResponse;
import com.example.springjpa.enums.CustomerSortProperty;
import com.example.springjpa.enums.ProductSortProperty;
import org.springframework.data.domain.Sort;

public interface IProductService {


  APIProductResponse<ProductPayload> createProduct(APIProductRequest productRequest);

  APIProductResponse<PagedPayload<ProductPayload>> getAllProducts(int page, int size, ProductSortProperty sort, Sort.Direction direction);

  APIProductResponse<ProductPayload> getProductById(Long id);

  APIProductResponse<ProductPayload> updateProductById(APIProductRequest productRequest, Long id);

  String deleteProductById(Long id);
}
