package com.inn.cafe.utils.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseUtils {
  private int status;
  private String message;
  private String timestamp;
}
