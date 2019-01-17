package com.L15user.L15user.controller;

import com.L15user.L15user.bean.AccountBean;
import com.L15user.L15user.bean.OperationBean;
import com.L15user.L15user.model.User;
import com.L15user.L15user.proxy.AccountProxy;
import com.L15user.L15user.proxy.OperationProxy;
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
    
    @Autowired
    private OperationProxy ops;
    
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

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
    
    @RequestMapping(value = "/delAccount")
    public String deleteAccount(@RequestParam int idd, @RequestParam int idu) {
        acc.deleAccount(idd);
        return "redirect:/usr_accounts/" + idu;
    }
    
    //Affiche les comptes d'un client specifique
    @RequestMapping(value = "/usr_accounts/{user}", method = RequestMethod.GET)
    public String findByUser(Model model, @PathVariable int user) {
        model.addAttribute("accounts", acc.findByUser(user));
        model.addAttribute("user", user);
        return "usr_accounts";
    }
    
    // Le client est sur le formulaire d'ouverture d'un compte bancaire
    @RequestMapping(value = "/addAccount/{usr_id}", method = RequestMethod.GET)
    public String showOpenAccount(Model model, @PathVariable int usr_id) {
        model.addAttribute("usr_id", usr_id);
        return "addAccount";
    }
    
    // Le client ouvre un nouveau compte
    @RequestMapping(value = "/addAccount", method = RequestMethod.POST)
    public String openAccount(@RequestParam int userId, @RequestParam String type) {
        AccountBean newAcc = new AccountBean();
        newAcc.setUser(userId);
        newAcc.setType(type);
        acc.addAccountToNewUser(newAcc);
        return "redirect:/usr_accounts/" + userId;
    }
    
 // Le client ouvre un nouveau compte
    @RequestMapping(value = "/editAccount", method = RequestMethod.POST)
    public String editAccount(@RequestParam int userId, @RequestParam int acid, @RequestParam String type, @RequestParam double fee, @RequestParam double interest) {
        AccountBean newAcc = new AccountBean();
        newAcc.setId(acid);
        newAcc.setInterest(interest);
        newAcc.setType(type);
        newAcc.setFee(fee);
        acc.editAccount(newAcc);
        return "redirect:/usr_accounts/" + userId;
    }

   // Inscription d'un nouveau client
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(Model model, @ModelAttribute("User") User acE) {
    	User newUsr = new User(acE.getLastName(), acE.getFirstName(), acE.getEmail(), acE.getPassword());
        usr.save(newUsr);
        AccountBean newAcc = new AccountBean();
        newAcc.setUser(newUsr.getId());
        newAcc.setType("Courant");
        AccountBean newAB = acc.addAccountToNewUser(newAcc);
        //ops.save(new Operation("L15bank", newAB.getIban(), 80, dateFormat.format(new Date()), "Prime de bienvenue", "VIREMENT"));
        return "redirect:/users_overview";
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
        return "redirect:/users_overview";
    }
    
    // On affiche le formulaire de recherche d'operations
    @RequestMapping(value = "/operation_search", method = RequestMethod.GET)
    public String showOperations(Model model) {
        model.addAttribute("operations", new OperationBean());
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