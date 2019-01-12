package com.victor.antoine.L15.L15bank.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;

import com.victor.antoine.L15.L15bank.model.Account;

public interface AccountRepository {

    List<Account> findByLastName(String lastName);
    
    public List<Account> findAll();
    public Account findById(int id);
    public Account save(Account acc);
}
