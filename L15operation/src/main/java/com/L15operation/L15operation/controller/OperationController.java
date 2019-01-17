package com.L15operation.L15operation.controller;

import com.L15operation.L15operation.model.Operation;
import com.L15operation.L15operation.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public ResponseEntity<Operation> makeTransfer(@RequestBody Operation op) {
    	Operation opss = new Operation(op.getIbanSrc(), op.getIbanDest(), op.getValue(), op.getLabel(), "VIREMENT");
        operationRepository.save(opss);
        return new ResponseEntity<Operation>(opss, HttpStatus.CREATED);
    }
    
    
    @RequestMapping(value = "/transfer/{iban}", method = RequestMethod.GET)
    public String showTransferWindow(Model model, @PathVariable String iban) {
        model.addAttribute("accountIBAN", iban);
        return "transfer";
    }
	
    
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
