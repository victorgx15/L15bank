package com.victor.antoine.L15.L15bank.model;

public class Operation {
    private int id;
    private int iban_source;
    private int type_dest;
    private double value;
    private String date;
    private String Label;

    public Operation(int id, int iban_source, int type_dest, double value, String date, String label) {
        this.id = id;
        this.iban_source = iban_source;
        this.type_dest = type_dest;
        this.value = value;
        this.date = date;
        Label = label;
    }

    public int getId() {
        return id;
    }

    public int getIban_source() {
        return iban_source;
    }

    public int getType_dest() {
        return type_dest;
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
