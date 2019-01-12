package com.victor.antoine.L15.L15bank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
	
	private @Id @GeneratedValue int id;
    private String iban;
    private int type;
    
    public Account(int id, String iban, int type) {
		this.id = id;
		this.iban = iban;
		this.type = type;
	}
    
    public Account() {
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
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", iban=" + iban + ", type=" + type + "]";
	}

	public void setType(int type) {
		this.type = type;
	}
}
