package com.dastex.dasangebotword.model;

import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aladhari
 */
public class Kunden {
    
    private List<Angebot> angebots;
    private String land;
    private String name1;
    private String name2;
    private String name3;
    private String nr;
    private String ort;
    private String plz;
    private String strasse;
    private String sprachzeichen;
    private String zahlBed;
    private String liefBed;
    private String mMZ = "";
    private String mWSt = "";
    private String tk = "";
    private String rab1 = "";
 

    
    public List<Angebot> getAngebots() {
        return angebots;
    }
    public String getLand() {
        return land;
    }

    public String getName1() {
        return name1;
    }


    public String getName2() {
        return name2;
    }

    public String getTk() {
        return tk;
    }

    public void setTk(String tk) {
        this.tk = tk;
    }

    public String getRab1() {
        return rab1;
    }

    public void setRab1(String rab1) {
        this.rab1 = rab1;
    }


    public String getName3() {
        return name3;
    }
    public String getNr() {
        return nr;
    }
    public String getOrt() {
        return ort;
    }
    public String getPlz() {
        return plz;
    }

    public String getStrasse() {
        return strasse;
    }
    public void setAngebots(List<Angebot> angebots) {
        this.angebots = angebots;
    }
    public void setLand(String land) {
        this.land = land;
    }
    public void setName1(String name1) {
        this.name1 = name1;
    }
    public void setName2(String name2) {
        this.name2 = name2;
    }
    public void setName3(String name3) {
        this.name3 = name3;
    }
    public void setNr(String nr) {
        this.nr = nr;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }
    public void setPlz(String plz) {
        this.plz = plz;
    }
    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getSprachzeichen() {
        return sprachzeichen;
    }

    public void setSprachzeichen(String sprachzeichen) {
        this.sprachzeichen = sprachzeichen;
    }

    public String getZahlBed() {
        return zahlBed;
    }

    public void setZahlBed(String zahlBed) {
        this.zahlBed = zahlBed;
    }

    public String getLiefBed() {
        return liefBed;
    }

    public void setLiefBed(String liefBed) {
        this.liefBed = liefBed;
    }

    public String getmMZ() {
        return mMZ;
    }

    public void setmMZ(String mMZ) {
        this.mMZ = mMZ;
    }

    public String getmWSt() {
        return mWSt;
    }

    public void setmWSt(String mWSt) {
        this.mWSt = mWSt;
    }


    
    
    
}
