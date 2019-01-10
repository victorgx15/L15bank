package com.victor.antoine.L15.L15bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Greeting {
    @RequestMapping(value="/Produits", method=RequestMethod.GET)
    public String listeProduits() {
        return "Un exemple de produit";
    }
}