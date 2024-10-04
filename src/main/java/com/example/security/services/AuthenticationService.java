package com.example.security.services;

import com.example.security.dtos.LoginUserDto;
import com.example.security.dtos.RegisterUserDto;
import com.example.security.entities.User;
import com.example.security.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final AuthenticationManager authenticationManager;

  public User signup(RegisterUserDto input) {
    User user = new User();
    user.setEmail(input.getEmail());
    user.setPassword(passwordEncoder.encode(input.getPassword()));
    user.setFullName(input.getFullName());
    return userRepository.save(user);
  }

  public User authenticate(LoginUserDto input) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    input.getEmail(),
                    input.getPassword()
            )
    );

    return userRepository.findByEmail(input.getEmail()).orElseThrow();
  }
}
