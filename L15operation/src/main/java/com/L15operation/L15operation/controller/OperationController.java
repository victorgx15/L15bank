package com.L15operation.L15operation.controller;

import com.L15operation.L15operation.model.Operation;
import com.L15operation.L15operation.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Collections;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@RestController
public class OperationController {
    @Autowired
    private OperationRepository operationRepository;
    
    @Autowired
    EntityManager em;

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
    
    @RequestMapping(value = "/operation_search", method = RequestMethod.GET)
    public String showOperationSearch(Model model, @RequestParam(value = "errorMessage", defaultValue = " ") String errorMessage) {
        model.addAttribute("errorMessage", errorMessage);
        return "operation_search";
    }
    
    
    @GetMapping(value = "/operations")
    public List<Operation> showOps(@RequestParam(value = "iban", defaultValue = "") String iban,
    		@RequestParam(value = "date", defaultValue = "") String date, @RequestParam(value = "type", defaultValue = "") String type) {
        
    	List<Operation> ops = new ArrayList<Operation>();
    	int noCriteria = 0;
        if (!iban.isEmpty()) { ops.addAll(operationRepository.findByIbanSrcOrIbanDest(iban, iban)); noCriteria++; }
        if (!date.isEmpty()) { ops.addAll(operationRepository.findByDate(date)); noCriteria++; }
        if (!type.isEmpty()) { ops.addAll(operationRepository.findByType(type)); noCriteria++; }
        
        Set<Operation> hs1 = new LinkedHashSet<>(ops);
        List<Operation> fina = new ArrayList<Operation>();
        for(Operation o: hs1) {
        	if(Collections.frequency(ops, o) == noCriteria)
        		fina.add(o);
        }
        
        return fina;
    }

}
