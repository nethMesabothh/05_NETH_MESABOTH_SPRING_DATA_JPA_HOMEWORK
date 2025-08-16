package com.example.springjpa.dto.response;


import lombok.*;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIPagination {
  private int pageSize;
  private long totalElements;
  private int totalPages;
  private int currentPage;

  public APIPagination(Page<?> page) {
    this.totalElements = page.getTotalElements();
    this.currentPage = page.getNumber() + 1; // Spring is 0-based, API is 1-based
    this.pageSize = page.getSize();
    this.totalPages = page.getTotalPages();
  }
}


