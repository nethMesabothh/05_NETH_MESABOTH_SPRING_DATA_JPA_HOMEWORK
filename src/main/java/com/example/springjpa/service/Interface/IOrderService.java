package com.example.springjpa.service.Interface;

import com.example.springjpa.dto.payload.OrderPayload;
import com.example.springjpa.dto.payload.PagedPayload;
import com.example.springjpa.dto.payload.ProductPayload;
import com.example.springjpa.dto.request.APIOrderRequest;
import com.example.springjpa.dto.request.APIProductRequest;
import com.example.springjpa.dto.response.APIOrderResponse;
import com.example.springjpa.dto.response.APIProductResponse;
import com.example.springjpa.enums.OrderSortProperty;
import com.example.springjpa.enums.OrderStatus;
import com.example.springjpa.enums.ProductSortProperty;
import org.springframework.data.domain.Sort;

public interface IOrderService {

  APIOrderResponse<OrderPayload> createOder(Long id, APIOrderRequest orderRequest);

  APIOrderResponse<PagedPayload<OrderPayload>> getAllOrders(Long customerId, int page, int size, OrderSortProperty sort, Sort.Direction direction);

  APIOrderResponse<OrderPayload> updateStatusOrderById(Long orderId, OrderStatus status);

  APIOrderResponse<OrderPayload> getOrderById(Long orderId);

  String deleteOrderById(Long orderId);
}
