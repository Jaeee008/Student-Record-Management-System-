package com.example.studentdb.service;

import com.example.studentdb.repository.UserRepository;

public class AuthService {

    private final UserRepository userRepository = new UserRepository();

    public boolean signUp(String username, String password) throws Exception {
        if (username == null || username.trim().isEmpty()) {
            throw new Exception("Username is required.");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new Exception("Password is required.");
        }

        if (userRepository.usernameExists(username.trim())) {
            throw new Exception("Username already exists.");
        }

        userRepository.createUser(username.trim(), password.trim());
        return true;
    }

    public boolean login(String username, String password) throws Exception {
        if (username == null || username.trim().isEmpty()) {
            throw new Exception("Username is required.");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new Exception("Password is required.");
        }

        return userRepository.login(username.trim(), password.trim());
    }
}