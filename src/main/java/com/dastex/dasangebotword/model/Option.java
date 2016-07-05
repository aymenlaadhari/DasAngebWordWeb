/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dastex.dasangebotword.model;

import java.util.Objects;

/**
 *
 * @author aladhari
 */
public class Option {
    private String nr, beszeichnung, text, vkpr,kdpr,std, aufpreis;

    public Option() {
    }

    public Option(Option other) {
        this.nr = other.nr;
        this.beszeichnung = other.beszeichnung;
        this.text = other.text;
        this.vkpr = other.vkpr;
        this.kdpr = other.kdpr;
        this.std = other.std;
        this.aufpreis = other.aufpreis;
    }

    
    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getBeszeichnung() {
        return beszeichnung;
    }

    public void setBeszeichnung(String beszeichnung) {
        this.beszeichnung = beszeichnung;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getVkpr() {
        return vkpr;
    }

    public void setVkpr(String vkpr) {
        this.vkpr = vkpr;
    }

    public String getKdpr() {
        return kdpr;
    }

    public void setKdpr(String kdpr) {
        this.kdpr = kdpr;
    }

    public String getStd() {
        return std;
    }

    public void setStd(String std) {
        this.std = std;
    }

    public String getAufpreis() {
        return aufpreis;
    }

    public void setAufpreis(String aufpreis) {
        this.aufpreis = aufpreis;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Option)) {
            return false;
        }
        Option other = (Option) object;
        if ((this.aufpreis == null && other.aufpreis != null) || (this.aufpreis != null && !this.aufpreis.equals(other.aufpreis))) {
            return false;
        }
        if ((this.beszeichnung == null && other.beszeichnung != null) || (this.beszeichnung != null && !this.beszeichnung.equals(other.beszeichnung))) {
            return false;
        }
        if ((this.kdpr == null && other.kdpr != null) || (this.kdpr != null && !this.kdpr.equals(other.kdpr))) {
            return false;
        }
        if ((this.nr == null && other.nr != null) || (this.nr != null && !this.nr.equals(other.nr))) {
            return false;
        }
         if ((this.std == null && other.std != null) || (this.std != null && !this.std.equals(other.std))) {
            return false;
        }
          if ((this.text == null && other.text != null) || (this.text != null && !this.text.equals(other.text))) {
            return false;
        }
          if ((this.vkpr == null && other.vkpr != null) || (this.vkpr != null && !this.vkpr.equals(other.vkpr))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.nr);
        hash = 19 * hash + Objects.hashCode(this.beszeichnung);
        hash = 19 * hash + Objects.hashCode(this.text);
        hash = 19 * hash + Objects.hashCode(this.vkpr);
        hash = 19 * hash + Objects.hashCode(this.kdpr);
        hash = 19 * hash + Objects.hashCode(this.std);
        hash = 19 * hash + Objects.hashCode(this.aufpreis);
        return hash;
    }
    
    
}
