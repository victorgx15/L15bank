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

import java.util.List;

@Controller
public class OperationController {
    @Autowired
    private OperationRepository operationRepository;
    
    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(value = "/createTransfer", method = RequestMethod.POST)
    public String makeTransfer(Model model, @ModelAttribute("operation") Operation op,
                               @RequestParam String iban) {
        operationRepository.save(new Operation(op.getIbanSrc(), op.getIbanDest(), op.getValue(), op.getDate(), op.getLabel(), "VIREMENT"));
        return "redirect:/account";
    }
    
    
    @RequestMapping(value = "/transfer", method = RequestMethod.GET)
    public String showTransferWindow(Model model, @RequestParam String iban) {
        model.addAttribute("operations", operationRepository.findAll());
        model.addAttribute("balance", getAccountValue(iban));
        model.addAttribute("account", accountRepository.findByIban(iban));
        return "account";
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String showAccount(Model model, @RequestParam String iban) {
        model.addAttribute("operations", operationRepository.findAll());
        model.addAttribute("balance", getAccountValue(iban));
        model.addAttribute("account", accountRepository.findByIban(iban));
        return "account";
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
