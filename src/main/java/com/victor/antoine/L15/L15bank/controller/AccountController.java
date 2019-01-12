package com.victor.antoine.L15.L15bank.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victor.antoine.L15.L15bank.model.Account;
import com.victor.antoine.L15.L15bank.model.AccountForm;
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
    public String showTodos(Model model){
        //String name = (String) model.get("name");
        model.addAttribute("ibass", acc.findAll());
        return "accounts_overview";
    }
    
    @RequestMapping(value = "/addAccount", method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {
        AccountForm accForm = new AccountForm();
        model.addAttribute("accountForm", accForm);
        return "addAccount";
    }
    
    @RequestMapping(value = "/addAccount", method = RequestMethod.POST)
    public String saAcc(Model model, @ModelAttribute("AccountFrom") AccountForm acEs) {
        acc.save(new Account(acEs.getIban(), acEs.getType()));
        return "redirect:/accounts_overview";
    }
	
}