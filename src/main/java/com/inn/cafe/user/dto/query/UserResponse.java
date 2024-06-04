package com.inn.cafe.user.dto.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
  private Integer id;
  private String firstName;
  private String lastName;
  private String contactNumber;
  private String username;
  private String email;

}
