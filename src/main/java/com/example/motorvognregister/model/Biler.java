package com.example.motorvognregister.model;

public class Biler {
    private int id;
    String biltype;
    String bilmerke;

    public Biler() {
    }

    public Biler(int id, String biltype, String bilmerke) {
        this.id = id;
        this.biltype = biltype;
        this.bilmerke = bilmerke;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBiltype() {
        return biltype;
    }

    public void setBiltype(String biltype) {
        this.biltype = biltype;
    }

    public String getBilmerke() {
        return bilmerke;
    }

    public void setBilmerke(String bilmerke) {
        this.bilmerke = bilmerke;
    }

}
