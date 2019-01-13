package com.victor.antoine.L15.L15bank.controller;

import com.victor.antoine.L15.L15bank.model.Operation;
import com.victor.antoine.L15.L15bank.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public class OperationController {
    @Autowired
    private OperationRepository repository;

    @RequestMapping(value = "/createTransfer", method = RequestMethod.POST)
    public String makeTransfer(Model model, @ModelAttribute("operation") Operation op,
                               @RequestParam int userId) {
        repository.save(new Operation(op.getIban_source(), op.getIban_dest(), op.getValue(), op.getDate(), op.getLabel()));
        return "redirect:/accounts_overview";
    }
}
