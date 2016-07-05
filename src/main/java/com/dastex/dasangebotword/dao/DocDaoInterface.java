/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dastex.dasangebotword.dao;



import com.dastex.dasangebotword.model.Artikel;
import com.dastex.dasangebotword.model.Bearbeiter;
import com.dastex.dasangebotword.model.Gultichkeit;
import com.dastex.dasangebotword.model.Kunden;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author aladhari
 */
@Local
public interface DocDaoInterface {
    
    
    public List<Kunden> getListKunden();
    public List<Kunden> getListKundenByCriteria(String artNummer, String name, String ort, String land );
    public Kunden getKundenByCriteria(String kundNummer);
    public List<Artikel> getListArtikel();
    public List<Artikel> getListArtikelWIthCriteria(String artNumm, String beschreibung,String kuzbeschreibung,String liefArtNumm,int lan,String sprache);
    public Artikel getArtikle(String artNummer, String sprache, String varNr, String beschreibung, String nJ, String kundNumm, String liste);
    public Artikel getArtikleWithCriteria(String nummer,String besch, String sprache, String liste);
    public Artikel getSingelArtikle(String nummer,String besch, String sprache, String liste);
    public Bearbeiter getBearbeiterByCriteria(String login);
    public List<Bearbeiter> getListBearbeiters();
    public String getText(String feld,String sprache,String mult );
    public String getTextExtra(String feld,String sprache);
    public List<String> getTexts(String feld,String sprache,String mult);
    public List<Gultichkeit> getListGultichkeit();
    public List<String> getLieferZeit(String artNum,String sprache);
    public String getFavfarben(String artNummer,String sprache,String fav);
    public void closeConnection();
    
 
    
}
