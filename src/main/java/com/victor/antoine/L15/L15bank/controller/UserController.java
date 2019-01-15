package com.victor.antoine.L15.L15bank.controller;

import com.victor.antoine.L15.L15bank.model.Account;
import com.victor.antoine.L15.L15bank.model.Operation;
import com.victor.antoine.L15.L15bank.model.User;
import com.victor.antoine.L15.L15bank.repository.AccountRepository;
import com.victor.antoine.L15.L15bank.repository.OperationRepository;
import com.victor.antoine.L15.L15bank.repository.UserRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserRepository usr;
    
    @Autowired
    private AccountRepository acc;
    
    @Autowired
    private OperationRepository ops;
    
    @RequestMapping(value= "/users_overview", method = RequestMethod.GET)
    public String showUsers(Model model){
        model.addAttribute("users", usr.findAll());
        return "users_overview";
    }
    
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String showAddUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }
    
    @RequestMapping(value = "/delUser", method = RequestMethod.DELETE)
    public String deleteUser(@RequestParam int ide) {
        usr.deleteById(ide);
        return "redirect:/users_overview";
    }
    
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(Model model, @ModelAttribute("User") User acE) {
    	User newUsr = new User(acE.getLastName(), acE.getFirstName(), acE.getEmail(), acE.getPassword());
        usr.save(newUsr);
        Account newAc = new Account("FR76 69308 00046 00000" + newUsr.getId(),
        							"Courant", newUsr.getId(), 25, 0);
        acc.save(newAc);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ops.save(new Operation("L15bank", newAc.getIban(), 80,
        		dateFormat.format(new Date()), "Prime de bienvenue"));
        return "redirect:/users_overview";
    }

}