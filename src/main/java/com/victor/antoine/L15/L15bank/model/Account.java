package com.victor.antoine.L15.L15bank.model;

import com.victor.antoine.L15.L15bank.controller.AccountController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Account {

	private static int generalID = 1;
	private @Id int id;
    private String iban, type;
    private int user;
    
    // Les frais de tenue de compte
    private double fee;
    
    // Le taux d'interet
    private double interest;

	/**
	 * @Autowired private OperationRepository operationRepository;
	 **/

	public Account(String iban, String type, int userId, double fee, double interest) {
		this.iban = iban;
		this.type = type;
		this.user = userId;
		this.fee = fee;
		this.id = generalID++;
		this.interest = interest;
	}

	public Account(String type, int userId, double fee, double interest) {
		this.id = generalID++;
		this.iban = ibanGenerator(id);
		this.type = type;
		this.user = userId;
		this.fee = fee;
		this.interest = interest;
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
	public double getFee() { return fee; }
	public void setFee(double fee) { this.fee = fee; }
	public double getInterest() { return interest; }
	public void setInterest(double interest) { this.interest = interest; }

	@Override
	public String toString() {
		return "Account [id=" + id + ", iban=" + iban + ", type=" + type + ", user=" + user + "]";
	}

	public Double getValue() {
		AccountController accountController = new AccountController();
		return accountController.getAccountValue(iban);
	}
    
    public String ibanGenerator(int accountid) {
        String countryCode = "FR76";
        String bankCode = "40712";
        String counterCode = "80364";
        System.out.println(this.id);
        String accountNumber = String.format("%011d", this.id);
        String ribKey = String.format("%02d", 97 - ((89 * Integer.parseInt(bankCode) + 15 * Integer.parseInt(counterCode) + 3 * accountid) % 97));
        return countryCode + " " + bankCode + " " + accountNumber + " " + ribKey;
    }
}
