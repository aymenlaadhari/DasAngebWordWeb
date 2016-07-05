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
public class Prises {
    private String farben, groessen, me, pmng, vk1, vpMng, wz, art, ab, preis;

    public Prises() {
    }

    public Prises(Prises other) {
        this.farben = other.farben;
        this.groessen = other.groessen;
        this.me = other.me;
        this.pmng = other.pmng;
        this.vk1 = other.vk1;
        this.vpMng = other.vpMng;
        this.wz = other.wz;
        this.art = other.art;
        this.ab = other.ab;
        this.preis = other.preis;
    }

    
    public String getPreis() {
        return preis;
    }

    public void setPreis(String preis) {
        this.preis = preis;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public String getAb() {
        return ab;
    }

    public void setAb(String ab) {
        this.ab = ab;
    }

    public String getFarben() {
        return farben;
    }


    public String getGroessen() {
        return groessen;
    }
    public String getMe() {
        return me;
    }
    public String getPmng() {
        return pmng;
    }

    public String getVk1() {
        return vk1;
    }
    public String getVpMng() {
        return vpMng;
    }

    public String getWz() {
        return wz;
    }
    public void setFarben(String farben) {
        this.farben = farben;
    }
    public void setGroessen(String groessen) {
        this.groessen = groessen;
    }


    public void setMe(String me) {
        this.me = me;
    }
    public void setPmng(String pmng) {
        this.pmng = pmng;
    }
    public void setVk1(String vk1) {
        this.vk1 = vk1;
    }

    public void setVpMng(String vpMng) {
        this.vpMng = vpMng;
    }
    public void setWz(String wz) {
        this.wz = wz;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Prises)) {
          
            return false;
        }
        Prises other = (Prises) object;
        if ((this.ab == null && other.ab != null) || (this.ab != null && !this.ab.equals(other.ab))) {
          
            return false;
        }
        if ((this.art == null && other.art != null) || (this.art != null && !this.art.equals(other.art))) {
         
            return false;
        }
        if ((this.farben == null && other.farben != null) || (this.farben != null && !this.farben.equals(other.farben))) {
            
            return false;
        }
        if ((this.groessen == null && other.groessen != null) || (this.groessen != null && !this.groessen.equals(other.groessen))) {
            
            return false;
        }
        if ((this.me == null && other.me != null) || (this.me != null && !this.me.equals(other.me))) {
            
            return false;
        }
        if ((this.pmng == null && other.pmng != null) || (this.pmng != null && !this.pmng.equals(other.pmng))) {
            
            return false;
        }
        if ((this.preis == null && other.preis != null) || (this.preis != null && !this.preis.equals(other.preis))) {
           
            return false;
        }
        if ((this.vk1 == null && other.vk1 != null) || (this.vk1 != null && !this.vk1.equals(other.vk1))) {
            
            return false;
        }
        if ((this.vpMng == null && other.vpMng != null) || (this.vpMng != null && !this.vpMng.equals(other.vpMng))) {
         
            return false;
        }
        if ((this.wz == null && other.wz != null) || (this.wz != null && !this.wz.equals(other.wz))) {
           
            return false;
        }
        
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.farben);
        hash = 71 * hash + Objects.hashCode(this.groessen);
        hash = 71 * hash + Objects.hashCode(this.pmng);
        hash = 71 * hash + Objects.hashCode(this.vk1);
        hash = 71 * hash + Objects.hashCode(this.vpMng);
        hash = 71 * hash + Objects.hashCode(this.wz);
        hash = 71 * hash + Objects.hashCode(this.art);
        hash = 71 * hash + Objects.hashCode(this.ab);
        hash = 71 * hash + Objects.hashCode(this.preis);
        return hash;
    }
    
    
}
