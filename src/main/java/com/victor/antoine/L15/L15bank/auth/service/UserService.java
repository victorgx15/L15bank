package com.victor.antoine.L15.L15bank.auth.service;

import com.victor.antoine.L15.L15bank.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}