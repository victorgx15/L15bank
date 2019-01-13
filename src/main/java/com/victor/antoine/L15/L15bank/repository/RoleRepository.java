package com.victor.antoine.L15.L15bank.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.victor.antoine.L15.L15bank.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByUserId(int userId);
}
