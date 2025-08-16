package com.example.springjpa.dto.payload;

import com.example.springjpa.dto.response.APIPagination;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagedPayload<T> {
  private List<T> items;
  private APIPagination pagination;

  public PagedPayload(Page<T> page) {
    this.items = page.getContent();
    this.pagination = new APIPagination(page);
  }
}
