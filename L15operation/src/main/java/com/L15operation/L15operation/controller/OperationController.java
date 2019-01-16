package com.L15operation.L15operation.controller;

import com.L15operation.L15operation.model.Operation;
import com.L15operation.L15operation.repository.OperationRepository;
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
import java.util.List;

@RestController
public class OperationController {
    @Autowired
    private OperationRepository operationRepository;
    
    //@Autowired
    //private AccountRepository accountRepository;

    @RequestMapping(value = "/createTransfer", method = RequestMethod.POST)
    public String makeTransfer(Model model, @ModelAttribute("ibanSrc") String ibanSrc, @ModelAttribute("ibanDest") String ibanDest, @ModelAttribute("value") Double value, @ModelAttribute("date") String date, @ModelAttribute("label") String label) {
        operationRepository.save(new Operation(ibanSrc, ibanDest, value, date, label, "VIREMENT"));
        return "redirect:/account?iban=" + ibanSrc;
    }
    
    /*
    @RequestMapping(value = "/transfer", method = RequestMethod.GET)
    public String showTransferWindow(Model model, @RequestParam String iban) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        model.addAttribute("operation", new Operation(iban, "VIREMENT"));
        model.addAttribute("account", accountRepository.findByIban(iban).get(0));
        return "transfer";
    }
	*/
    
    @RequestMapping(value = "/operations/{iban}", method = RequestMethod.GET)
    public List<Operation> operationsList(@PathVariable String iban) {
        return operationRepository.findByIbanSrcOrIbanDest(iban, iban);
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
