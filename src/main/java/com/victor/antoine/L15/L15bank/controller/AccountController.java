package com.victor.antoine.L15.L15bank.controller;

import com.victor.antoine.L15.L15bank.model.Account;
import com.victor.antoine.L15.L15bank.model.Operation;
import com.victor.antoine.L15.L15bank.repository.AccountRepository;
import com.victor.antoine.L15.L15bank.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class AccountController {
	
	@Autowired
	private AccountRepository acc;

    @Autowired
    private OperationRepository operationRepository;

	//Récupérer la liste des produits
    @RequestMapping(value="/Accounts", method=RequestMethod.GET)
    public List<Account> accountsList() {
        return acc.findAll();
    }
    
    @RequestMapping(value= "/accounts_overview", method = RequestMethod.GET)
    public String showAccounts(Model model){
        //String name = (String) model.get("name");
        model.addAttribute("accounts", acc.findAll());
        model.addAttribute("value", getAccountValue("FR562039"));
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
    	String accountType = acE.getType();
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
        acc.save(new Account("FR76 69308 00046 00000" + acE.getIban(), acE.getType(), userId, fee, interest));
        return "redirect:/accounts_overview";
    }


    public Double getAccountValue(String ibanSrc) {
        List<Operation> list_op = operationRepository.findByIbanSrc(ibanSrc);
        double sum = 0;
        for (Operation op : list_op) {
            sum = sum + op.getValue();
        }
        return sum;
    }

}