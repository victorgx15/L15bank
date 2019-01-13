package com.victor.antoine.L15.L15bank.auth.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
