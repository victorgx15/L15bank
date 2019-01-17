package com.L15operation.L15operation.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    
    public Operation(String ibanSrc, String ibanDest, double value, String label, String type) {
        assert (type == "VIREMENT" || type == "CB" || type == "CHEQUE");
        this.ibanSrc = ibanSrc;
        this.ibanDest = ibanDest;
        this.value = value;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.date = dateFormat.format(new Date());
        this.label = label;
        this.type = type;
    }
    
    public Operation(String ibanSrc, String type) {
        assert (type == "VIREMENT" || type == "CB" || type == "CHEQUE");
        this.ibanSrc = ibanSrc;
        this.ibanDest = "";
        this.value = 0;
        this.date = "";
        this.label = "";
        this.type = type;
    }
    
    public Operation() {
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        assert (type == "VIREMENT" || type == "CB" || type == "CHEQUE");
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
    
    @Override
    public boolean equals(Object o) {
    	if (o.getClass() != Operation.class) {
    		return false;
    	} else {
    		Operation o1 = (Operation) o;
    		return o1.getId() == this.id;
    	}
    	
    }

}
