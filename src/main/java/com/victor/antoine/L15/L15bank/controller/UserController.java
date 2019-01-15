package com.victor.antoine.L15.L15bank.controller;

import com.victor.antoine.L15.L15bank.model.Account;
import com.victor.antoine.L15.L15bank.model.User;
import com.victor.antoine.L15.L15bank.repository.AccountRepository;
import com.victor.antoine.L15.L15bank.repository.OperationRepository;
import com.victor.antoine.L15.L15bank.repository.UserRepository;

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

    
    @RequestMapping(value= "/usr_overview", method = RequestMethod.GET)
    public String showUsers(Model model){
        model.addAttribute("users", usr.findAll());
        return "usr_overview";
    }
    
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String showAddUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }
    
    @RequestMapping(value = "/delUser", method = RequestMethod.DELETE)
    public String deleteUser(@RequestParam int ide) {
        usr.deleteById(ide);
        return "redirect:/usr_overview";
    }
    
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(Model model, @ModelAttribute("User") User acE) {
        usr.save(new User(acE.getLastName(), acE.getFirstName(), acE.getEmail(), acE.getPassword()));
        return "redirect:/usr_overview";
    }

}