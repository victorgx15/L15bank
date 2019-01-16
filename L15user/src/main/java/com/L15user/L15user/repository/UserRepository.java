package com.L15user.L15user.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.L15user.L15user.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
