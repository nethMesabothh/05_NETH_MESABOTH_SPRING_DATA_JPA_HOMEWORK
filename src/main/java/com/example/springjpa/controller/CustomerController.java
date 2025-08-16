package com.example.springjpa.controller;

import com.example.springjpa.dto.payload.CustomerPayload;
import com.example.springjpa.dto.payload.PagedPayload;
import com.example.springjpa.dto.request.APICustomerRequest;
import com.example.springjpa.dto.response.APICustomerResponse;
import com.example.springjpa.enums.CustomerSortProperty;
import com.example.springjpa.service.Interface.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

  private final ICustomerService customerService;

  @GetMapping()
  private APICustomerResponse<PagedPayload<CustomerPayload>> getAllCustomers(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "ID") CustomerSortProperty sort, @RequestParam(defaultValue = "ASC") Sort.Direction direction) {
    return customerService.getAllCustomers(page, size, sort, direction);
  }

  @PostMapping()
  private APICustomerResponse<CustomerPayload> createCustomer(@RequestBody APICustomerRequest customerRequest) {
    return customerService.createCustomer(customerRequest);
  }

  @GetMapping("/{id}")
  private APICustomerResponse<CustomerPayload> getCustomerById(@PathVariable Long id) {
    return customerService.getCustomerById(id);
  }

  @PutMapping("/{id}")
  private APICustomerResponse<CustomerPayload> updateCustomerById(@RequestBody APICustomerRequest customerRequest, @PathVariable Long id) {
    return customerService.updateCustomerById(customerRequest, id);
  }

  @DeleteMapping("/{id}")
  private String deleteCustomerById(@PathVariable Long id) {
    return customerService.deleteCustomerById(id);
  }
}
