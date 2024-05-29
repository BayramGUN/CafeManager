package com.inn.cafe.auth.service;

import com.inn.cafe.auth.dto.command.AuthenticationRequest;
import com.inn.cafe.auth.dto.command.PasswordRequest;
import com.inn.cafe.auth.dto.command.RegisterRequest;
import com.inn.cafe.auth.dto.query.AuthenticationResponse;

public interface AuthenticationService {
  AuthenticationResponse register(RegisterRequest request);

  AuthenticationResponse authenticate(AuthenticationRequest request);

  AuthenticationResponse forgotPassword(PasswordRequest request);
}
