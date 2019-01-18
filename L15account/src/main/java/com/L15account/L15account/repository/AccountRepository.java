package com.L15account.L15account.repository;


import java.util.List;
import com.L15account.L15account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByIban(String iban);
    Account findById(int id);
}