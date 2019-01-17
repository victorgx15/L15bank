package com.L15user.L15user.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

public class AccountBean {

    private @Id int id;
    private String iban, type;
    private int user;

    // Les frais de tenue de compte
    private double fee;

    // Le taux d'interet
    private double interest;

    public AccountBean() { }
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
}