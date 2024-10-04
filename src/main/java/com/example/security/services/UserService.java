package com.example.security.services;

import com.example.security.entities.User;
import com.example.security.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public List<User> allUsers() {
    List<User> users = new ArrayList<>();

    userRepository.findAll().forEach(users::add);

    return users;
  }
}
