package com.example.springjpa.service.Implement;

import com.example.springjpa.dto.payload.CustomerPayload;
import com.example.springjpa.dto.payload.PagedPayload;
import com.example.springjpa.dto.payload.ProductPayload;
import com.example.springjpa.dto.request.APIProductRequest;
import com.example.springjpa.dto.response.APICustomerResponse;
import com.example.springjpa.dto.response.APIProductResponse;
import com.example.springjpa.entity.Customer;
import com.example.springjpa.entity.CustomerAccount;
import com.example.springjpa.entity.Product;
import com.example.springjpa.enums.CustomerSortProperty;
import com.example.springjpa.enums.ProductSortProperty;
import com.example.springjpa.exception.NotFoundException;
import com.example.springjpa.repository.ProductRepository;
import com.example.springjpa.service.Interface.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

  private final ProductRepository productRepository;

  @Override
  public APIProductResponse<ProductPayload> createProduct(APIProductRequest productRequest) {

    Product product = productRequest.toEntity();

    return new APIProductResponse<>("Fetch Custom Successfully", productRepository.save(product).toResponse(), HttpStatus.OK, LocalDateTime.now());
  }

  @Override
  public APIProductResponse<PagedPayload<ProductPayload>> getAllProducts(int page, int size, ProductSortProperty sort, Sort.Direction direction) {
    Page<Product> products = productRepository.findAll(PageRequest.of(page - 1, size, Sort.by(direction, sort.getField())));

    Page<ProductPayload> productPayloads = products.map(Product::toResponse);
    return new APIProductResponse<>("Fetch Custom Successfully", new PagedPayload<>(productPayloads), HttpStatus.OK, LocalDateTime.now());

  }

  @Override
  public APIProductResponse<ProductPayload> getProductById(Long id) {
    Product product = productRepository.findById(id).orElse(null);

    if (product == null) {
      throw new NotFoundException("product " + id + " not found!");
    }

    return new APIProductResponse<>("Fetch Product Successfully", product.toResponse(), HttpStatus.OK, LocalDateTime.now());
  }

  @Override
  public APIProductResponse<ProductPayload> updateProductById(APIProductRequest productRequest, Long id) {
    Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Customer " + id + " not found!"));


    Product updateProduct = productRequest.toUpdateEntity(existingProduct);

    return new APIProductResponse<>("Product updated successfully",
            productRepository.save(updateProduct).toResponse(),
            HttpStatus.OK,
            LocalDateTime.now());
  }

  @Override
  public String deleteProductById(Long id) {
    Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Customer " + id + " not found!"));

    productRepository.delete(existingProduct);

    return "Customer Deleted!";
  }


}
