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

	//Methode test
    @RequestMapping(value="/Accounts", method=RequestMethod.GET)
    public List<Account> accountsList() {
        return acc.findAll();
    }
    
    //Affiche les comptes de tous les clients
    @RequestMapping(value= "/accounts_overview", method = RequestMethod.GET)
    public String showAccounts(Model model){
        model.addAttribute("accounts", acc.findAll());
        return "accounts_overview";
    }
    
    //Affiche les comptes d'un client specifique
    @RequestMapping(value = "/usr_accounts", method = RequestMethod.GET)
    public String showUserAccounts(Model model, @RequestParam int usr_id) {
        model.addAttribute("accounts", acc.findByUser(usr_id));
        model.addAttribute("usr_id", usr_id);
        return "usr_accounts";
    }
    
    @RequestMapping(value = "/addAccount", method = RequestMethod.GET)
    public String showAddAccount(Model model, @RequestParam int usr_id) {
        model.addAttribute("account", new Account());
        model.addAttribute("usr_id", usr_id);
        return "addAccount";
    }
    
    @RequestMapping(value = "/delAccount", method = RequestMethod.DELETE)
    public String deleteAccount(@RequestParam int ide) {
        acc.deleteById(ide);
        return "redirect:/accounts_overview";
    }
    
    @RequestMapping(value = "/addAccount", method = RequestMethod.POST)
    public String saAcc(Model model, @RequestParam String type,
    		@RequestParam int userId) {
    	String accountType = type;
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
    	Account account = new Account(type, userId, fee, interest);
        acc.save(account);
    
        operationRepository.save(new Operation("L15Bank", account.getIban(), 0, "Ouverture de compte", "VIREMENT"));
        return "redirect:/usr_accounts?usr_id=" + userId;
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