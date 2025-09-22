package com.academy.project.api.service.impl;

import com.academy.project.api.entity.Users;
import com.academy.project.api.repository.UserRepository;
import com.academy.project.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<Users> getUserById(UUID id) {
        return userRepository.findById(id);
    }

}
