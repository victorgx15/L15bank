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
    EntityManager em;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private AccountRepository accountRepository;

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
    
    @RequestMapping(value = "/operation_search", method = RequestMethod.GET)
    public String showOperationSearch(Model model, @RequestParam(value = "errorMessage", defaultValue = " ") String errorMessage) {
        model.addAttribute("errorMessage", errorMessage);
        return "operation_search";
    }
    
    
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String showAccount(Model model, @ModelAttribute("ibanSrc") String ibanSrc, @ModelAttribute("ibanDest") String ibanDest, @ModelAttribute("date") String date, @ModelAttribute("label") String label, @ModelAttribute("type") String type) {
        
        if (ibanSrc.isEmpty() && ibanDest.isEmpty())
            return "redirect:/operation_search?errorMessage='Il faut fournir au moins un iban'";
        
        String strSQL = "SELECT o FROM Operation o WHERE ";
            boolean flagFirstCondition = true;
            if (!ibanSrc.isEmpty()) {
                if (flagFirstCondition) {
                    strSQL = strSQL + "IBANSRC='" + ibanSrc + "'";
                    flagFirstCondition = false;
                } else {
                    strSQL = strSQL + " AND IBANSRC='" + ibanSrc + "'";
                }
            }
            if (!ibanDest.isEmpty()) {
                if (flagFirstCondition) {
                    strSQL = strSQL + "IBANDEST='" + ibanDest + "'";
                    flagFirstCondition = false;
                } else {
                    strSQL = strSQL + " AND IBANDEST='" + ibanDest + "'";
                }
            }
        if (!date.isEmpty()) {
            if (flagFirstCondition) {
                strSQL = strSQL + "DATE='" + date + "'";
                flagFirstCondition = false;
            } else {
                strSQL = strSQL + " AND DATE='" + date + "'";
            }
        }
        if (!label.isEmpty()) {
            if (flagFirstCondition) {
                strSQL = strSQL + "LABEL='" + label + "'";
                flagFirstCondition = false;
            } else {
                strSQL = strSQL + " AND LABEL='" + label + "'";
            }
        }
        if (!date.isEmpty()) {
            if (flagFirstCondition) {
                strSQL = strSQL + "TYPE='" + type + "'";
                flagFirstCondition = false;
            } else {
                strSQL = strSQL + " AND TYPE='" + type + "'";
            }
        }
        TypedQuery<Operation> query = em.createQuery(strSQL, Operation.class);
        List<Operation> results = query.getResultList();
        if (results.size() == 0) return "redirect:/operation_search?errorMessage='Aucun resultat'";
        String iban;
        if (!ibanSrc.isEmpty()) iban = ibanSrc;
        else iban = ibanDest;
        model.addAttribute("operations", results);
        model.addAttribute("balance", getAccountValue(results, iban));
        model.addAttribute("account", accountRepository.findByIban(iban).get(0));
        return "account";
    }
    
    
    public Double getAccountValue(String ibanSrc) {
        return getAccountValue(operationRepository.findByIbanSrcOrIbanDest(ibanSrc, ibanSrc), ibanSrc);
    }
    
    public Double getAccountValue(List<Operation> listOperations, String ibanSrc) {
        double sum = 0;
        for (Operation op : listOperations) {
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
