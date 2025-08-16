package com.example.springjpa.service.Implement;

import java.util.*;

import com.example.springjpa.dto.payload.CustomerPayload;
import com.example.springjpa.dto.payload.PagedPayload;
import com.example.springjpa.dto.request.APICustomerRequest;
import com.example.springjpa.dto.response.APICustomerResponse;
import com.example.springjpa.entity.Customer;
import com.example.springjpa.entity.CustomerAccount;
import com.example.springjpa.enums.CustomerSortProperty;
import com.example.springjpa.exception.NotFoundException;
import com.example.springjpa.repository.CustomerAccountRepository;
import com.example.springjpa.repository.CustomerRepository;
import com.example.springjpa.service.Interface.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerAccountRepository customerAccountRepository;

  @Override
  public APICustomerResponse<CustomerPayload> createCustomer(APICustomerRequest customerRequest) {

    if (customerAccountRepository.existsByUsername(customerRequest.getUsername())) {
      throw new IllegalArgumentException("Customer username must be unique");
    }

    Customer customer = customerRequest.toEntity();
    CustomerPayload customerPayloads = customerRepository.save(customer).toResponse();

    return new APICustomerResponse<>("Fetch Custom Successfully", customerPayloads, HttpStatus.OK, LocalDateTime.now());
  }

  @Override
  public APICustomerResponse<PagedPayload<CustomerPayload>> getAllCustomers(int page, int size, CustomerSortProperty sort, Sort.Direction direction) {
    Page<Customer> customers = customerRepository.findAll(PageRequest.of(page - 1, size, Sort.by(direction, sort.getField())));

    Page<CustomerPayload> customerPayloads = customers.map(Customer::toResponse);

    return new APICustomerResponse<>("Fetch Custom Successfully", new PagedPayload<>(customerPayloads), HttpStatus.OK, LocalDateTime.now());

  }

  @Override
  public APICustomerResponse<CustomerPayload> getCustomerById(Long id) {

    Customer customer = customerRepository.findById(id).orElse(null);

    if (customer == null) {
      throw new NotFoundException("Custom " + id + " not found!");
    }

    return new APICustomerResponse<>("Fetch Custom Successfully", customer.toResponse(), HttpStatus.OK, LocalDateTime.now());

  }

  @Override
  public APICustomerResponse<CustomerPayload> updateCustomerById(APICustomerRequest customerRequest, Long id) {

    Customer existingCustomer = customerRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Customer " + id + " not found!"));

    // Check if username is unique (ignore current customer's username)
    if (customerRequest.getUsername() != null) {
      Optional<CustomerAccount> customerAccount = customerAccountRepository
              .findByUsername(customerRequest.getUsername());

      if (customerAccount.isPresent() && !customerAccount.get().getCustomerId().equals(id)) {
        throw new NotFoundException("Customer has the same username as other!");
      }
    }

    Customer updatedCustomer = customerRequest.toUpdateEntity(existingCustomer);

    return new APICustomerResponse<>("Customer updated successfully",
            customerRepository.save(updatedCustomer).toResponse(),
            HttpStatus.OK,
            LocalDateTime.now());
  }

  @Override
  public String deleteCustomerById(Long id) {
    Customer existingCustomer = customerRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Customer " + id + " not found!"));


    customerRepository.delete(existingCustomer);

    return "Customer Deleted!";
  }


}
