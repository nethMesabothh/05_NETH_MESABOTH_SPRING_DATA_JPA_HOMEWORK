package com.example.springjpa.dto.request;

import com.example.springjpa.dto.response.APICustomerResponse;
import com.example.springjpa.entity.Customer;
import com.example.springjpa.entity.CustomerAccount;
import com.example.springjpa.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APICustomerRequest {

  private String name;
  private String address;
  private String phoneNumber;
  private String username;
  private String password;


  public Customer toEntity() {
    Customer customer = new Customer();
    customer.setName(this.name);
    customer.setAddress(this.address);
    customer.setPhoneNumber(this.phoneNumber);

    if (this.username != null && this.password != null) {
      CustomerAccount account = new CustomerAccount();
      account.setUsername(this.username);
      account.setPassword(this.password);
      account.setIsActive(true);

      account.setCustomer(customer);
      customer.setAccount(account);
    }
    return customer;
  }

  public Customer toUpdateEntity(Customer existingCustomer) {
    existingCustomer.setName(this.name);
    existingCustomer.setAddress(this.address);
    existingCustomer.setPhoneNumber(this.phoneNumber);

    if (this.username != null && this.password != null) {
      CustomerAccount account = existingCustomer.getAccount();
      if (account == null) {
        account = new CustomerAccount();
        account.setCustomer(existingCustomer);
        existingCustomer.setAccount(account);
      }
      account.setUsername(this.username);
      account.setPassword(this.password);
      account.setIsActive(true);
    }
    return existingCustomer;
  }
}