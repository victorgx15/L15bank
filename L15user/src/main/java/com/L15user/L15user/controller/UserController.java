package com.L15user.L15user.controller;

import com.L15user.L15user.bean.AccountBean;
import com.L15user.L15user.bean.OperationBean;
import com.L15user.L15user.proxy.AccountProxy;
import com.L15user.L15user.proxy.OperationProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Controller
public class UserController {
	
    @Autowired
    private AccountProxy acc;
    
    @Autowired
    private OperationProxy ops;
    
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    // Supprimer un compte
    @RequestMapping(value = "/delAccount")
    public String deleteAccount(@RequestParam int idd) {
        acc.deleAccount(idd);
        return "redirect:/accounts";
    }
    
    //Affiche la liste des comptes
    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public String findByUser(Model model) {
        model.addAttribute("accounts", acc.showAccounts());
        return "accounts";
    }
    
    // Le client ouvre un nouveau compte
    @RequestMapping(value = "/addAccount", method = RequestMethod.POST)
    public String openAccount(@RequestParam String type) {
        AccountBean newAcc = new AccountBean();
        newAcc.setType(type);
        acc.addAccountToNewUser(newAcc);
        return "redirect:/accounts/";
    }
    
    // L'utilisateur modifie un compte
    @RequestMapping(value = "/editAccount")
    public String editAccount(@RequestParam int acid, @RequestParam String type, @RequestParam double fee, @RequestParam double interest) {
        AccountBean newAcc = new AccountBean();
        newAcc.setId(acid);
        newAcc.setInterest(interest);
        newAcc.setType(type);
        newAcc.setFee(fee);
        acc.editAccount(newAcc);
        return "redirect:/accounts/";
    }
    
 // Le client est sur le formulaire d'operations de virement
    @RequestMapping(value = "/transfer/{accountIBAN}", method = RequestMethod.GET)
    public String showTransferForm(Model model, @PathVariable String accountIBAN) {
        model.addAttribute("accountIBAN", accountIBAN);
        model.addAttribute("operation", new OperationBean());
        return "transfer";
    }
    
    // Le client a effectue l'operation de virement
    @RequestMapping(value = "/createTransfer", method = RequestMethod.POST)
    public String doingTransfer(@ModelAttribute("Operation") OperationBean acE, @RequestParam String accId) {
    	acE.setIbanSrc(accId);
    	ops.makeTransfer(acE);
        return "redirect:/operations?iban=" + accId;
    }
    
    // On affiche le formulaire de recherche d'operations
    @RequestMapping(value = "/operation_search", method = RequestMethod.GET)
    public String showOperations(Model model) {
        
        return "operation_search";
    }
    
	 // On affiche le resultat de la recherche d'operations
    @RequestMapping(value = "/operations", method = RequestMethod.GET)
    public String showOperations(Model model, @RequestParam(value = "iban", defaultValue = "") String iban,
    		@RequestParam(value = "date", defaultValue = "") String date, @RequestParam(value = "type", defaultValue = "") String type) {
        model.addAttribute("operations", ops.showOps(iban,date,type));
        model.addAttribute("iban", iban);
        
        double sum = 0;
        for(OperationBean op : ops.showOps(iban,date,type)) {
        	if(op.getIbanDest().equals(iban))
        		sum += op.getValue();
        	else
        		sum -= op.getValue();
        }
        
        model.addAttribute("balance", sum);
        
        return "operations";
    }
    
}