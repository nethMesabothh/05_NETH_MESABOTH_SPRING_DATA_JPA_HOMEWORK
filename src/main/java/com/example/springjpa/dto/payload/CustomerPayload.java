package com.example.springjpa.dto.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPayload {
  private Long customerId;
  private String name;
  private String address;
  private String phoneNumber;
  private String username;
  private String password;
  private boolean isActive;

}