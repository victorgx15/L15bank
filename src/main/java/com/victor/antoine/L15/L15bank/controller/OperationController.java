package com.victor.antoine.L15.L15bank.controller;

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

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class OperationController {
    @Autowired
    private OperationRepository operationRepository;
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    EntityManager em;

    @RequestMapping(value = "/createTransfer", method = RequestMethod.POST)
    public String makeTransfer(Model model, @ModelAttribute("ibanSrc") String ibanSrc, @ModelAttribute("ibanDest") String ibanDest, @ModelAttribute("value") Double value, @ModelAttribute("date") String date, @ModelAttribute("label") String label) {
        operationRepository.save(new Operation(ibanSrc, ibanDest, value, date, label, "VIREMENT"));
        return "redirect:/account?iban=" + ibanSrc;
    }
    
    
    @RequestMapping(value = "/transfer", method = RequestMethod.GET)
    public String showTransferWindow(Model model, @RequestParam String iban) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        model.addAttribute("operation", new Operation(iban, "VIREMENT"));
        model.addAttribute("account", accountRepository.findByIban(iban).get(0));
        return "transfer";
    }
    
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String showAccount(Model model, @RequestParam String ibanScr) {
        model.addAttribute("operations", operationRepository.findByIbanSrcOrIbanDest(ibanScr, ibanScr));
        model.addAttribute("balance", getAccountValue(ibanScr));
        model.addAttribute("account", accountRepository.findByIban(ibanScr).get(0));
        return "account";
    }
    
    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String showAccount(Model model, @ModelAttribute("ibanSrc") String ibanSrc, @ModelAttribute("ibanDest") String ibanDest, @ModelAttribute("value") Double value, @ModelAttribute("date") String date, @ModelAttribute("label") String label, @ModelAttribute("type") String type) {
        
        String strSQL = "SELECT * FROM Operation";
        if (!ibanSrc.isEmpty() || !ibanDest.isEmpty() || value != 0 || !date.isEmpty() || !label.isEmpty() || !type.isEmpty()) {
            strSQL = strSQL + " WHERE";
            boolean flagFirstCondition = true;
            if (!ibanSrc.isEmpty()) {
                if (flagFirstCondition) {
                    strSQL = strSQL + "IBANSRC=\"" + ibanSrc + "\"";
                } else {
                    strSQL = strSQL + " AND IBANSRC=\"" + ibanSrc + "\"";
                }
            }
            if (!ibanDest.isEmpty()) {
                if (flagFirstCondition) {
                    strSQL = strSQL + "IBANDEST=\"" + ibanDest + "\"";
                } else {
                    strSQL = strSQL + " AND IBANDEST=\"" + ibanDest + "\"";
                }
            }
            
        }
        strSQL = strSQL + ";";
        TypedQuery<Operation> query = em.createQuery(strSQL, Operation.class);
        List<Operation> results = query.getResultList();
        
        model.addAttribute("operations", results);
        model.addAttribute("balance", 999);
        model.addAttribute("account", accountRepository.findByIban(ibanSrc).get(0));
        return "account";
    }
    
    
    public Double getAccountValue(String ibanSrc) {
        List<Operation> list_op = operationRepository.findByIbanSrcOrIbanDest(ibanSrc, ibanSrc);
        double sum = 0;
        for (Operation op : list_op) {
            if (op.getIbanSrc().equals(ibanSrc)) {
                sum = sum - op.getValue();
            }
            if (op.getIbanDest().equals(ibanSrc)) {
                sum = sum + op.getValue();
            }
        }
        return sum;
    }
}
