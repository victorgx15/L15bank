package com.L15account.L15account.controller;

import com.L15account.L15account.model.Account;
import com.L15account.L15account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountRepository acc;

    //@Autowired
    //private OperationRepository operationRepository;

    //Affiche les comptes d'un client specifique
    @GetMapping(value = "/usr_accounts/{user}")
    public List<Account> findByUser(@PathVariable int user) {
        return acc.findByUser(user);
    }

    @GetMapping(value = "/addAccount")
    public String showAddAccount(Model model, @RequestParam int usr_id) {
        model.addAttribute("usr_id", usr_id);
        return "addAccount";
    }

    @DeleteMapping(value = "/delAccount")
    public void deleAccount(@RequestParam int idd) { acc.deleteById(idd); }

    // Ajouter un compte (a la maniere SAR)
    @PostMapping (value = "/addAccount")
    public ResponseEntity<Account> addAccountToNewUser(@RequestBody Account acb) {
        String accountType = acb.getType();
        double fee, interest;
        if(accountType.equals("Courant")) {
            fee = 25; interest = 0;
        } else if (accountType.equals("Livret A")) {
            fee = 30; interest = 0.25;
        } else if (accountType.equals("LDD")) {
            fee = 10; interest = 0.5;
        } else if (accountType.equals("PEA")) {
            fee = 70; interest = 0;
        } else if (accountType.equals("Assurance Vie")) {
            fee = 100; interest = 1;
        } else if (accountType.equals("Livret Epargne")) {
            fee = 40; interest = 0.1;
        } else {
            fee = 140; interest = 0;
        }
        Account account = new Account(accountType, acb.getUser(), fee, interest);
        acc.save(account);
//        operationRepository.save(new Operation("L15Bank", account.getIban(), 0, "Ouverture de compte", "VIREMENT"));
        return new ResponseEntity<Account>(account, HttpStatus.CREATED);
    }

/*
    public Double getAccountValue(String ibanSrc) {
        List<Operation> list_op = operationRepository.findByIbanSrc(ibanSrc);
        double sum = 0;
        for (Operation op : list_op) {
            sum = sum + op.getValue();
        }
        return sum;
    }
    */

}