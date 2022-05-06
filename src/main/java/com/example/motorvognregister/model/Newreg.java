package com.example.motorvognregister.model;

public class Newreg {
    private int id;
    private String skiltNr;
    private String bilmerke;
    private String biltype;
    private String personnr;
    private String navn;
    private String adresse;

    public Newreg(int id, String skiltNr, String bilmerke, String biltype, String personnr, String navn, String adresse) {
        this.id = id;
        this.skiltNr = skiltNr;
        this.bilmerke = bilmerke;
        this.biltype = biltype;
        this.personnr = personnr;
        this.navn = navn;
        this.adresse = adresse;
    }

    public Newreg() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkiltNr() {
        return skiltNr;
    }

    public void setSkiltNr(String skiltNr) {
        this.skiltNr = skiltNr;
    }

    public String getBilmerke() {
        return bilmerke;
    }

    public void setBilmerke(String bilmerke) {
        this.bilmerke = bilmerke;
    }

    public String getBiltype() {
        return biltype;
    }

    public void setBiltype(String biltype) {
        this.biltype = biltype;
    }

    public String getPersonnr() {
        return personnr;
    }

    public void setPersonnr(String personnr) {
        this.personnr = personnr;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
