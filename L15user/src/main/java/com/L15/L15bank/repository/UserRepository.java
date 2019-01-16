package com.victor.antoine.L15.L15bank.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.victor.antoine.L15.L15bank.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
