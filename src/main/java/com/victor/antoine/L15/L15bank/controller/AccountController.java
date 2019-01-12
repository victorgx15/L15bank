package com.victor.antoine.L15.L15bank.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.victor.antoine.L15.L15bank.model.Account;
import com.victor.antoine.L15.L15bank.repository.AccountRepository;


@RestController
public class AccountController {
	
	@Autowired
	private AccountRepository acc;

	//Récupérer la liste des produits
    @RequestMapping(value="/Accounts", method=RequestMethod.GET)
    public List<Account> listeProduits() {
        return acc.findAll();
    }

    //Récupérer un produit par son Id
    @GetMapping(value="/Accounts/{id}")
    public Optional<Account> displayAccount(@PathVariable int id) {
    	return acc.findById(id);
    }
	
}