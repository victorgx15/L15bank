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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class OperationController {
    @Autowired
    private OperationRepository operationRepository;
    
    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(value = "/createTransfer", method = RequestMethod.POST)
    public String makeTransfer(Model model, @ModelAttribute("operation") Operation op) {
        operationRepository.save(op);
        return "redirect:/account?iban=" + op.getIbanSrc();
    }
    
    
    @RequestMapping(value = "/transfer", method = RequestMethod.GET)
    public String showTransferWindow(Model model, @RequestParam String iban) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        model.addAttribute("operation", new Operation(iban, "VIREMENT"));
        model.addAttribute("account", accountRepository.findByIban(iban).get(0));
        return "transfer";
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String showAccount(Model model, @RequestParam String iban) {
        model.addAttribute("operations", operationRepository.findByIbanSrcOrIbanDest(iban, iban));
        model.addAttribute("balance", getAccountValue(iban));
        model.addAttribute("account", accountRepository.findByIban(iban).get(0));
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
