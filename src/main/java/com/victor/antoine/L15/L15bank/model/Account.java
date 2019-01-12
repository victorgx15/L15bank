package com.victor.antoine.L15.L15bank.model;

import javax.persistence.Entity;
import lombok.Data;
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
    
    public Account(int id, String iban, int type) {
		this.id = id;
		this.iban = iban;
		this.type = type;
	}
    
    public Account() {
		this.id = 1;
		this.iban = "DE56";
		this.type = 3;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
