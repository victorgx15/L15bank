package com.victor.antoine.L15.L15bank.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.victor.antoine.L15.L15bank.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByIban(String iban);
    List<Account> findByUser(int user);
}
