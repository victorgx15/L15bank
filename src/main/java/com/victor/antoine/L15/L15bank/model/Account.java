package com.victor.antoine.L15.L15bank.model;

import com.victor.antoine.L15.L15bank.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Account {
	
	private @Id @GeneratedValue int id;
    private String iban, type;
    private int user;

	@Autowired
	OperationRepository operationRepository;

	public Account(String iban, String type, int userId) {
		this.iban = iban;
		this.type = type;
		this.user = userId;
	}
    
    public Account() { }
	public int getId() { return id;	}
	public void setId(int id) { this.id = id; }
	public String getIban() { return iban; }
	public void setIban(String iban) { this.iban = iban; }
	public String getType() { return type; }
	public void setType(String type) { this.type = type; }
	public int getUser() { return user; }
	public void setUser(int userId) { this.user = userId; }

	@Override
	public String toString() {
		return "Account [id=" + id + ", iban=" + iban + ", type=" + type + ", user=" + user + "]";
	}

	public double getValue() {
		List<Operation> list_op = operationRepository.findByIban_source(this.iban);
		double sum = 0;
		for (Operation op : list_op) {
			sum = sum + op.getValue();
		}
		return sum;
	}
}
