package com.example.springjpa.controller;

import com.example.springjpa.dto.payload.OrderPayload;
import com.example.springjpa.dto.payload.PagedPayload;
import com.example.springjpa.dto.request.APIOrderRequest;
import com.example.springjpa.dto.response.APIOrderResponse;
import com.example.springjpa.enums.CustomerSortProperty;
import com.example.springjpa.enums.OrderSortProperty;
import com.example.springjpa.enums.OrderStatus;
import com.example.springjpa.service.Interface.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

  private final IOrderService orderService;

  @GetMapping("/customer/{customer_id}")
  public APIOrderResponse<PagedPayload<OrderPayload>> getAllOrders(@PathVariable("customer_id") Long customerId, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "ID") OrderSortProperty sort, @RequestParam(defaultValue = "ASC") Sort.Direction direction) {
    return orderService.getAllOrders(customerId, page, size, sort, direction);
  }

  @PostMapping("/customer/{customer_id}")
  public APIOrderResponse<OrderPayload> createOder(@RequestBody APIOrderRequest orderRequest, @PathVariable("customer_id") Long customerId) {
    return orderService.createOder(customerId, orderRequest);
  }

  @PutMapping("/{order_id}/status")
  public APIOrderResponse<OrderPayload> updateStatusOrderById(@PathVariable("order_id") Long orderId,
                                                              @RequestParam("status") OrderStatus status) {
    return orderService.updateStatusOrderById(orderId, status);
  }

  @GetMapping("/{order_id}")
  public APIOrderResponse<OrderPayload> getOrderById(@PathVariable("order_id") Long orderId) {
    return orderService.getOrderById(orderId);
  }

  @DeleteMapping("/{order_id}")
  public String deleteOrderById(@PathVariable("order_id") Long orderId) {
    return orderService.deleteOrderById(orderId);
  }

}
