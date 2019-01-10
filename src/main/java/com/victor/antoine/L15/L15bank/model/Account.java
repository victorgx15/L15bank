package com.victor.antoine.L15.L15bank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
    private String iban;
    private int type;

    public String getIban() {
        return iban;
    }

    public int getType() {
        return type;
    }
}
