package com.victor.antoine.L15.L15bank.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.victor.antoine.L15.L15bank.model.Account;
import com.victor.antoine.L15.L15bank.repository.AccountRepository;


@Controller
public class AccountController {
	
	@Autowired
	private AccountRepository acc;

	//Récupérer la liste des produits
    @RequestMapping(value="/Accounts", method=RequestMethod.GET)
    public List<Account> accountsList() {
        return acc.findAll();
    }
    
    @RequestMapping(value= "/accounts_overview", method = RequestMethod.GET)
    public String showAccounts(Model model){
        //String name = (String) model.get("name");
        model.addAttribute("ibass", acc.findAll());
        return "accounts_overview";
    }
    
    @RequestMapping(value = "/addAccount", method = RequestMethod.GET)
    public String showAddAccount(Model model) {
        model.addAttribute("account", new Account());
        return "addAccount";
    }
    
    @RequestMapping(value = "/delAccount", method = RequestMethod.DELETE)
    public String deleteAccount(@RequestParam int ide) {
        acc.deleteById(ide);
        return "redirect:/accounts_overview";
    }
    
    @RequestMapping(value = "/addAccount", method = RequestMethod.POST)
    public String saAcc(Model model, @ModelAttribute("Account") Account acE,
    		@RequestParam int userId) {
        acc.save(new Account(acE.getIban(), acE.getType(), userId));
        return "redirect:/accounts_overview";
    }
	
}