package com.L15account.L15account.bean;

public class OperationBean {

    private int id;
    private String ibanSrc, ibanDest;
    private double value;
    private String date, label, type;
    
    public OperationBean(String ibanSrc, String ibanDest, double value, String label, String type) {
        assert (type == "VIREMENT" || type == "CB" || type == "CHEQUE");
        this.ibanSrc = ibanSrc;
        this.ibanDest = ibanDest;
        this.value = value;
        this.label = label;
        this.type = type;
    }
    
    public OperationBean() {
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


}
