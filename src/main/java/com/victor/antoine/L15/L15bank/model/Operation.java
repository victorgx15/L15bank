package com.victor.antoine.L15.L15bank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Operation {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    private String iban_source;
    private String iban_dest;
    private double value;
    private String date;
    private String Label;

    public Operation(int id, String iban_source, String iban_dest, double value, String date, String label) {
        this.id = id;
        this.iban_source = iban_source;
        this.iban_dest = iban_dest;
        this.value = value;
        this.date = date;
        Label = label;
    }

    public int getId() {
        return id;
    }

    public String getIban_source() {
        return iban_source;
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
