package com.victor.antoine.L15.L15bank.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.victor.antoine.L15.L15bank.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByIban(String iban);
    
}
