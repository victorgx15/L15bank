package com.victor.antoine.L15.L15bank.repository;
import org.springframework.stereotype.Repository;

import com.victor.antoine.L15.L15bank.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepImp implements AccountRepository {
	
    public static List<Account> accounts = new ArrayList<>();
    
    static {
        accounts.add(new Account(1, new String("Ordinateur portable"), 350));
        accounts.add(new Account(2, new String("Aspirateur Robot"), 500)); 
        accounts.add(new Account(3, new String("Table de Ping Pong"), 750));
    }

    @Override
    public List<Account> findAll() {
        return accounts;
    }
    
    @Override
    public Account findById(int id) {
        for (Account acc : accounts) {  
            if(acc.getId() == id){
                return acc;
            }
        }
        return null;
    }

    @Override
    public Account save(Account acc) {
        accounts.add(acc);
        return acc;
    }

	@Override
	public List<Account> findByLastName(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}
}