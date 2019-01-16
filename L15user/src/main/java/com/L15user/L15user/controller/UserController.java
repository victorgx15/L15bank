package com.L15user.L15user.controller;

import com.L15user.L15user.bean.AccountBean;
import com.L15user.L15user.model.User;
import com.L15user.L15user.proxy.AccountProxy;
import com.L15user.L15user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {

    @Autowired
    private UserRepository usr;
   
    @Autowired
    private AccountProxy acc;
    
    /*
    @Autowired
    private OperationRepository ops;
    */
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
    
    //Affiche les comptes d'un client specifique
    @RequestMapping(value = "/usr_accounts/{user}", method = RequestMethod.GET)
    public String findByUser(Model model, @PathVariable int user) {
        model.addAttribute("accounts", acc.findByUser(user));
        model.addAttribute("user", user);
        return "usr_accounts";
    }
    
  //Affiche tous les comptes
    @RequestMapping(value = "/accounts_overview", method = RequestMethod.GET)
    public String showUserAccounts(Model model) {
        model.addAttribute("accounts", acc.findAll());
        return "accounts_overview";
    }

    /*
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(Model model, @ModelAttribute("User") User acE) {
    	User newUsr = new User(acE.getLastName(), acE.getFirstName(), acE.getEmail(), acE.getPassword());
        usr.save(newUsr);
        Account newAc = new Account("Courant", newUsr.getId(), 25, 0);
        acc.save(newAc);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ops.save(new Operation("L15bank", newAc.getIban(), 80,
                dateFormat.format(new Date()), "Prime de bienvenue", "VIREMENT"));
        return "redirect:/users_overview";
    }
    */
}