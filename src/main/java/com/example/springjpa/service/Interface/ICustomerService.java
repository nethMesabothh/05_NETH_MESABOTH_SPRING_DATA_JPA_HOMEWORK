package com.example.springjpa.service.Interface;

import com.example.springjpa.dto.payload.CustomerPayload;
import com.example.springjpa.dto.payload.PagedPayload;
import com.example.springjpa.dto.request.APICustomerRequest;
import com.example.springjpa.dto.response.APICustomerResponse;
import com.example.springjpa.enums.CustomerSortProperty;
import org.springframework.data.domain.Sort;

public interface ICustomerService {
  APICustomerResponse<CustomerPayload> createCustomer(APICustomerRequest customerRequest);


  APICustomerResponse<PagedPayload<CustomerPayload>> getAllCustomers(int page, int size, CustomerSortProperty sort, Sort.Direction direction);

  APICustomerResponse<CustomerPayload> getCustomerById(Long id);

  APICustomerResponse<CustomerPayload> updateCustomerById(APICustomerRequest customerRequest, Long id);

  String deleteCustomerById(Long id);
}
