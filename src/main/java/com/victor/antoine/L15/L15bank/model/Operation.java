package com.victor.antoine.L15.L15bank.model;

import javax.persistence.*;

@Entity
public class Operation {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(name = "IBANSRC")
    private String ibanSrc;
    @Column(name = "IBANDEST")
    private String ibanDest;

    public Operation(String ibanSrc, String ibanDest, double value, String date, String label) {
        this.ibanSrc = ibanSrc;
        this.ibanDest = ibanDest;
        this.value = value;
        this.date = date;
        Label = label;
    }

    public Operation() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIbanDest() {
        return ibanDest;
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

    public void setIbanDest(String ibanDest) {
        this.ibanDest = ibanDest;
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
