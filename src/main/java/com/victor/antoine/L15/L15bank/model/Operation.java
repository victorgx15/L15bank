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
    private double value;
    private String date;
    private String label;
    private String type;
    
    public Operation(String ibanSrc, String ibanDest, double value, String date, String label, String type) {
        assert (type == "VIREMENT" || type == "CB" || type == "CHEQUE");
        this.ibanSrc = ibanSrc;
        this.ibanDest = ibanDest;
        this.value = value;
        this.date = date;
        this.label = label;
        this.type = type;
    }
    
    public Operation() {
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public String getIbanDest() {
        return ibanDest;
    }
    
    public void setIbanDest(String ibanDest) {
        this.ibanDest = ibanDest;
    }

    public String getIbanSrc() {
        return ibanSrc;
    }
    
    public void setIbanSrc(String ibanSrc) {
        this.ibanSrc = ibanSrc;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }
    
    public void setValue(double value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }

    public String getLabel() {
        return label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }


}
