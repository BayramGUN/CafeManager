package com.inn.cafe.user.service.command;



import com.inn.cafe.user.dto.command.CreateUserRequest;
import com.inn.cafe.user.dto.command.UpdateUserRequest;


public interface UserCommandService {
  public String createUserAsAdmin(CreateUserRequest request);
  public String update(Integer id, UpdateUserRequest request);
  public String softDelete(Integer id);
}
