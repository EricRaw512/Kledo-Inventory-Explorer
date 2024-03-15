package com.example.kledo.productkledo.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.kledo.productkledo.dao.UserRepository;
import com.example.kledo.productkledo.entity.Role;
import com.example.kledo.productkledo.entity.User;
import com.example.kledo.productkledo.model.UserForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

    public void saveNewUser(@Valid UserForm userForm) {
        User user = new User(userForm, passwordEncoder.encode(userForm.getPassword()), Role.USER);
        userRepository.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

}
