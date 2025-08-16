package com.example.springjpa.service.Implement;

import com.example.springjpa.dto.payload.OrderPayload;
import com.example.springjpa.dto.payload.PagedPayload;
import com.example.springjpa.dto.payload.ProductPayload;
import com.example.springjpa.dto.request.APIOrderItemRequest;
import com.example.springjpa.dto.request.APIOrderRequest;
import com.example.springjpa.dto.request.APIProductRequest;
import com.example.springjpa.dto.response.APIOrderResponse;
import com.example.springjpa.dto.response.APIProductResponse;
import com.example.springjpa.entity.*;
import com.example.springjpa.enums.OrderSortProperty;
import com.example.springjpa.enums.OrderStatus;
import com.example.springjpa.enums.ProductSortProperty;
import com.example.springjpa.exception.NotFoundException;
import com.example.springjpa.repository.CustomerRepository;
import com.example.springjpa.repository.OrderItemRepository;
import com.example.springjpa.repository.OrderRepository;
import com.example.springjpa.repository.ProductRepository;
import com.example.springjpa.service.Interface.IOrderService;
import com.example.springjpa.service.Interface.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

  private final OrderRepository orderRepository;
  private final CustomerRepository customerRepository;
  private final ProductRepository productRepository;


  @Override
  public APIOrderResponse<OrderPayload> createOder(Long customerId, APIOrderRequest orderRequest) {
    Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException("Customer " + customerId + " not found"));

    Order order = new Order();
    order.setCustomer(customer);
    order.setOrderDate(LocalDateTime.now());
    order.setStatus(OrderStatus.PENDING);

    BigDecimal totalAmount = BigDecimal.ZERO;

    for (APIOrderItemRequest itemRequest : orderRequest.getItems()) {
      Product product = productRepository.findById(itemRequest.getProductId()).orElseThrow(() -> new NotFoundException("Product " + itemRequest.getProductId() + " not found"));

      OrderItem orderItem = new OrderItem();

      // FIX: Instantiate the composite key
      orderItem.setId(new OrderItemId());

      orderItem.setOrder(order);
      orderItem.setProduct(product);
      orderItem.setQuantity(itemRequest.getQuantity());

      order.getOrderItems().add(orderItem);

      totalAmount = totalAmount.add(BigDecimal.valueOf(product.getUnitPrice()).multiply(BigDecimal.valueOf(itemRequest.getQuantity())));
    }

    order.setTotalAmount(totalAmount);

    Order savedOrder = orderRepository.save(order);


    return new APIOrderResponse<>("Order create successfully", savedOrder.toResponse(), HttpStatus.CREATED, LocalDateTime.now());
  }

  @Override
  public APIOrderResponse<PagedPayload<OrderPayload>> getAllOrders(Long customerId, int page, int size, OrderSortProperty sort, Sort.Direction direction) {

    if (!customerRepository.existsById(customerId)) {
      throw new NotFoundException("Customer " + customerId + " not found");
    }

    Pageable pageRequest = PageRequest.of(page - 1, size, Sort.by(direction, sort.getField()));

    Page<Order> orders = orderRepository.findAllByCustomerCustomerId(customerId, pageRequest);

    Page<OrderPayload> orderPayloads = orders.map(Order::toResponse);

    return new APIOrderResponse<>("Fetched orders for customer " + customerId + " successfully", new PagedPayload<>(orderPayloads), HttpStatus.OK, LocalDateTime.now());
  }

  @Override
  public APIOrderResponse<OrderPayload> updateStatusOrderById(Long orderId, OrderStatus status) {
    Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order with ID " + orderId + " not found."));

    if (order.getStatus() == OrderStatus.SHIPPED || order.getStatus() == OrderStatus.CANCELLED) {
      throw new NotFoundException("Cannot update status of an order that is already " + order.getStatus() + ".");
    }

    order.setStatus(status);

    Order updatedOrder = orderRepository.save(order);

    return new APIOrderResponse<>("Order status updated successfully", updatedOrder.toResponse(), HttpStatus.OK, LocalDateTime.now());
  }

  @Override
  public APIOrderResponse<OrderPayload> getOrderById(Long orderId) {
    Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order with ID " + orderId + " not found."));

    return new APIOrderResponse<>("Order fetched successfully", order.toResponse(), HttpStatus.OK, LocalDateTime.now());
  }

  @Override
  public String deleteOrderById(Long orderId) {
    if (!orderRepository.existsById(orderId)) {
      throw new NotFoundException("Order with ID " + orderId + " not found. Cannot delete.");
    }
    orderRepository.deleteById(orderId);

    return "Deleted Order Successfully!";
  }
}
