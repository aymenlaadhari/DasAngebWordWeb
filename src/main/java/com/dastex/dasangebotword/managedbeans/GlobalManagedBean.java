/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dastex.dasangebotword.managedbeans;

import com.dastex.dasangebotword.dao.DocDaoInterface;
import com.dastex.dasangebotword.model.Artikel;
import com.dastex.dasangebotword.model.Gultichkeit;
import com.dastex.dasangebotword.model.Kunden;
import com.dastex.dasangebotword.model.Option;
import com.dastex.dasangebotword.model.Prises;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author aladhari
 */
@Named(value = "globalManagedBean")
@Dependent
public class GlobalManagedBean {

    private ArrayList<String> words, names1, names2, names3, orts, mitarbeitersNames, artNummers;
    private List<Kunden> kundens;
    private List<Gultichkeit> gultichkeits;
    private List<Artikel> selectedArtikels, allArtikels;
    private List<String> schlussSatz, transpKostns;
    private List<Prises> innerPrises = new ArrayList<>();
    private List<Option> innerOptions = new ArrayList<>();

    @EJB
    DocDaoInterface daoInterface;

    /**
     * Creates a new instance of GlobalManagedBean
     */
    public GlobalManagedBean() {
        kundens = new ArrayList<>();

    }

    @PostConstruct
    public void init() {
        kundens = daoInterface.getListKunden();
        gultichkeits = daoInterface.getListGultichkeit();
        allArtikels = daoInterface.getListArtikel();
    }

    public List<Kunden> getKundens() {
        return kundens;
    }

    public void setKundens(List<Kunden> kundens) {
        this.kundens = kundens;
    }

}
