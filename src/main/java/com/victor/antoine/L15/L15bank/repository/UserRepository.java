package com.victor.antoine.L15.L15bank.repository;

import com.victor.antoine.L15.L15bank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
