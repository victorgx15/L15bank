package com.victor.antoine.L15.L15bank.model;

import javax.persistence.*;

@Entity
public class Operation {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(name = "IBANSRC")
    private String ibanSrc;
    private String iban_dest;

    public Operation(String ibanSrc, String iban_dest, double value, String date, String label) {
        this.ibanSrc = ibanSrc;
        this.iban_dest = iban_dest;
        this.value = value;
        this.date = date;
        Label = label;
    }

    public Operation() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIban_dest(String iban_dest) {
        this.iban_dest = iban_dest;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private double value;
    private String date;
    private String Label;

    public void setLabel(String label) {
        Label = label;
    }

    public String getIbanSrc() {
        return ibanSrc;
    }

    public int getId() {
        return id;
    }

    public void setIbanSrc(String ibanSrc) {
        this.ibanSrc = ibanSrc;
    }

    public String getIban_dest() {
        return iban_dest;
    }

    public double getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }

    public String getLabel() {
        return Label;
    }


}
