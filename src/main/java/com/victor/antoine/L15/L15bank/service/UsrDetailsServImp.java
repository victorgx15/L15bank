package com.victor.antoine.L15.L15bank.service;
/*
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.victor.antoine.L15.L15bank.model.User;
import com.victor.antoine.L15.L15bank.repository.UserRepository;
 
@Service
public class UsrDetailsServImp implements UserDetailsService {
 
    @Autowired
    private UserRepository appUserDAO;
 
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User appUser = this.appUserDAO.findByUsername(userName);
 
        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
 
        System.out.println("Found User: " + appUser);
 
        UserDetails userDetails = (UserDetails) new User(appUser.getUsername(),
        							appUser.getLastName(), appUser.getFirstName(),
        							appUser.getEmail(), appUser.getPassword());
        return userDetails;
    }
 
}*/