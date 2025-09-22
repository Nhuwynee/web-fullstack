package com.academy.project.api.service;

import com.academy.project.api.entity.Users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {
    List<Users> getAllUsers();
    Optional<Users> getUserById(UUID id);
}
