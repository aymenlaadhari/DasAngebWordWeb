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
import com.dastex.dasangebotword.model.Option;
import com.dastex.dasangebotword.model.Prises;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author aladhari
 */
@Stateless
@LocalBean
public class DocDao implements DocDaoInterface {

    private final String dburlProdukt = "jdbc:sqlanywhere:uid=dba;pwd=sql;eng=DBSRV5;database=Produkt5;links=tcpip(host = 10.152.1.203)";
    private Connection connection;

    @Override
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DocDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

    @Override
    public Bearbeiter getBearbeiterByCriteria(String login) {
         Bearbeiter bearbeiter = new Bearbeiter();
        String procName = "{call Angebot_Bearbeiterdaten('"+login+"')}";
        Connection conProdukt;
        try {
            conProdukt = DriverManager.getConnection(dburlProdukt);
            CallableStatement cs = conProdukt.prepareCall(procName);
             try (ResultSet rs = cs.executeQuery()) {
                 while (rs.next()) {
                     bearbeiter.setName(rs.getString("Name"));
                     bearbeiter.setEmail(rs.getString("E_Mail"));
                     bearbeiter.setTel(rs.getString("Tel"));
                     bearbeiter.setLogin(rs.getString("Login"));
                     bearbeiter.setZn(rs.getString("Zn"));
                     bearbeiter.setNummer(rs.getString("MANr"));
                     bearbeiter.setAbt(rs.getString("Abt"));
                 }
                 rs.close();
                 cs.close();
                 conProdukt.close();
                 
             }
        } catch (SQLException ex) {
            Logger.getLogger(DocDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bearbeiter;
    }

    @Override
    public String getFavfarben(String artNummer,String sprache,String fav) {
        String ret = null;
        try {
            String proc = "SELECT hf_artikel_farben_3('"+artNummer+"','"+sprache+"','"+fav+"')";  
            Connection conProdukt = DriverManager.getConnection(dburlProdukt);
            Statement s = conProdukt.createStatement();
            try (ResultSet rs = s.executeQuery(proc)) {
                while (rs.next()) {
                    ret = rs.getString(1);          
                }
                rs.close();
                s.close();
                conProdukt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DocDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
        
    }

    @Override
    public List<String> getLieferZeit(String artNum, String sprache) {
       List<String> liefer = new ArrayList<>();
        String procName = "{CALL Angebot_Lieferzeit( '"+artNum+"','"+sprache+"')}";
        Connection conProdukt;
        try {
            conProdukt = DriverManager.getConnection(dburlProdukt);
            CallableStatement cs = conProdukt.prepareCall(procName);
           try (ResultSet rs = cs.executeQuery()) {
               while (rs.next()) {
                   liefer.add(rs.getString(1));
                   liefer.add(rs.getString(2));
               }
               rs.close();
               cs.close();
               conProdukt.close();
               
           }
            
        } catch (SQLException ex) {
            Logger.getLogger(DocDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liefer;
    }

   

    @Override
    public List<Bearbeiter> getListBearbeiters() {
        List<Bearbeiter> bearbeiters = new ArrayList<>();
        String procName = "{call Angebot_Bearbeiterdaten('%')}";
        Connection conProdukt;
        try {
            conProdukt = DriverManager.getConnection(dburlProdukt);
            CallableStatement cs = conProdukt.prepareCall(procName);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Bearbeiter bearbeiter = new Bearbeiter();
                    bearbeiter.setName(rs.getString("Name"));
                    bearbeiter.setEmail(rs.getString("E_Mail"));
                    bearbeiter.setTel(rs.getString("Tel"));
                    bearbeiter.setLogin(rs.getString("Login"));
                    bearbeiter.setZn(rs.getString("Zn"));
                    bearbeiter.setNummer(rs.getString("MANr"));
                    bearbeiter.setAbt(rs.getString("Abt"));
                    bearbeiters.add(bearbeiter);
                }
                rs.close();
                cs.close();
                conProdukt.close();
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DocDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bearbeiters;
    }

    @Override
    public List<Gultichkeit> getListGultichkeit() {
        List<Gultichkeit> gultichkeits = new ArrayList<>();
        String procName = "{CALL Angebot_Gueltig_Bis()}";
        Connection conProdukt;
        try {
            conProdukt = DriverManager.getConnection(dburlProdukt);
            CallableStatement cs = conProdukt.prepareCall(procName);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Gultichkeit gultichkeit =  new Gultichkeit();
                    gultichkeit.setNr(rs.getString("Nr"));
                    gultichkeit.setText(rs.getString("Text"));
                    gultichkeit.setDate(rs.getString("Datum"));
                    gultichkeits.add(gultichkeit);
                    
                }
                rs.close();
                cs.close();
                conProdukt.close();
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DocDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return gultichkeits;
    }

    @Override
    public List<Kunden> getListKundenByCriteria(String akundNummer, String name, String ort, String land) {
        List<Kunden> kundens = new ArrayList<>();
        String procName = "{call angebot_kundendaten('"+akundNummer+"%','"+name+"%','"+ort+"%','"+land+"%')}";
        Connection conProdukt;
        try {
            conProdukt = DriverManager.getConnection(dburlProdukt);
            CallableStatement cs = conProdukt.prepareCall(procName);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Kunden localkunden = new Kunden();
                    localkunden.setNr(rs.getString("Nr"));
                    localkunden.setName1(rs.getString("Name_1"));
                    localkunden.setName2(rs.getString("NAme_2"));
                    localkunden.setName3(rs.getString("Name_3"));
                    localkunden.setStrasse(rs.getString("Strasse"));
                    localkunden.setPlz(rs.getString("PLZ"));
                    localkunden.setOrt(rs.getString("Ort"));
                    localkunden.setLand(rs.getString("Land"));
                    localkunden.setSprachzeichen(rs.getString("Sprach_Zeichen"));
                    localkunden.setZahlBed(rs.getString("Zahl_Bed"));
                    localkunden.setLiefBed(rs.getString("Lief_Bed"));
                    if (null!=rs.getString("MMZ")) {
                        localkunden.setmMZ(rs.getString("MMZ"));
                    }
                    if (null != rs.getString("MwSt")) {
                        localkunden.setmWSt(rs.getString("MwSt"));
                    }
                    localkunden.setTk(rs.getString("TK"));
                    localkunden.setRab1(rs.getString("Rab1"));
                    kundens.add(localkunden);                    
                }
                rs.close();
                cs.close();
                conProdukt.close();
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DocDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return kundens;

    }

    @Override
    public List<Kunden> getListKunden() {
        List<Kunden> kundens = new ArrayList<>();
        String procName = "{call angebot_kundendaten('%','%','%','%')}";
        Connection conProdukt;
        try {
            conProdukt = DriverManager.getConnection(dburlProdukt);
            CallableStatement cs = conProdukt.prepareCall(procName);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Kunden localkunden = new Kunden();
                    localkunden.setNr(rs.getString("Nr"));
                    localkunden.setName1(rs.getString("Name_1"));
                    localkunden.setName2(rs.getString("NAme_2"));
                    localkunden.setName3(rs.getString("Name_3"));
                    localkunden.setStrasse(rs.getString("Strasse"));
                    localkunden.setPlz(rs.getString("PLZ"));
                    localkunden.setOrt(rs.getString("Ort"));
                    localkunden.setLand(rs.getString("Land"));
                    localkunden.setSprachzeichen(rs.getString("Sprach_Zeichen"));
                    localkunden.setZahlBed(rs.getString("Zahl_Bed"));
                    localkunden.setLiefBed(rs.getString("Lief_Bed"));
                    localkunden.setmMZ(rs.getString("MMZ"));
                    localkunden.setmWSt(rs.getString("MwSt"));
                    localkunden.setTk(rs.getString("TK"));
                    localkunden.setRab1(rs.getString("Rab1"));
                    kundens.add(localkunden);     
                }
                rs.close();
                cs.close();
                conProdukt.close();   
            }
        } catch (SQLException ex) {
            Logger.getLogger(DocDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return kundens;
    }
    
    
    @Override
    public Kunden getKundenByCriteria(String kundNummer) {
        Kunden kunden = new Kunden();

        try (
                // Connect to Sybase Database
                Connection conProdukt = DriverManager.getConnection(dburlProdukt);
                Statement statementPro = conProdukt.createStatement();
                ResultSet rs = statementPro.executeQuery("SELECT * FROM Kunde where Kunde.Nr = '" + kundNummer + "'");) {
            kunden = new Kunden();
            kunden.setName1(rs.getString("Name_1"));
            kunden.setName2(rs.getString("Name_2"));
            kunden.setName3(rs.getString("Name_3"));
            kunden.setNr(rs.getNString("Nr"));
            kunden.setOrt(rs.getString("Ort"));
            kunden.setPlz(rs.getString("PLZ"));
            kunden.setStrasse(rs.getString("Strasse"));
            rs.close();
            statementPro.close();
            conProdukt.close();
            
        } catch (Exception e) {
        }

        return kunden;
    }

    @Override
    public Artikel getArtikle(String artNummer, String sprache, String varNr, String beschreibung, String nJ, String kundNumm, String liste) {
        Artikel artikel = null;
        try (
                // Connect to Sybase Database
                Connection conProdukt = DriverManager.getConnection(dburlProdukt);
                Statement statementPro = conProdukt.createStatement();
                ResultSet rsArtikel = statementPro.executeQuery("SELECT * FROM Artikel LEFT OUTER JOIN Artikelzusatztext ON Artikelzusatztext.At_ID = Artikel.ID AND Artikelzusatztext.Sprache = 'XAD' WHERE Artikel.Nr = '" + artNummer + "'");) {

            if (rsArtikel.next()) {
                artikel = new Artikel();
                artikel.setNr(rsArtikel.getString("Nr"));
                artikel.setBezeichnung(rsArtikel.getString("Bezeichnung"));
                artikel.setText(rsArtikel.getString("Text"));
                artikel.setFarben(getFarben(artNummer, sprache));
                artikel.setGroessen(getGroessen(artNummer,liste));
               // artikel.setCombinations(getCombinations(artNummer));
                //artikel.setOptions(getListOptionen(varNr, beschreibung, artNummer, nJ, kundNumm, sprache));
            } else {
                artikel = new Artikel();
                artikel.setCombinations(new ArrayList<Prises>());

            }

            rsArtikel.close();
            statementPro.close();
            conProdukt.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DocDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return artikel;
    }

     @Override
    public Artikel getArtikleWithCriteria(String artNumm, String beschreibung, String sprache, String liste) {
        Artikel artikel = null;
        String procName = "{CALL Angebot_Artikel( '"+artNumm+"%', '"+beschreibung+"%', '"+sprache+"')}";
        Connection conProdukt;
        try {
            conProdukt = DriverManager.getConnection(dburlProdukt);
            CallableStatement cs = conProdukt.prepareCall(procName);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    artikel = new Artikel();
                    artikel.setNr(rs.getString("Nr"));
                    artikel.setBezeichnung(rs.getString("Bezeichnung"));
                    artikel.setText(rs.getString("Text"));
                    artikel.setFarben(getFarben(artNumm, sprache));
                    artikel.setGroessen(getGroessen(artNumm,liste));  
                }
                rs.close();
                cs.close();
                conProdukt.close();
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DocDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return artikel;
    }
    
    @Override
    public Artikel getSingelArtikle(String nummer, String besch, String sprache, String liste) {
        Artikel artikel = null;
        String procName = "{CALL Angebot_Artikel( '"+nummer+"', '"+besch+"%', '"+sprache+"')}";
        Connection conProdukt;
        try {
            conProdukt = DriverManager.getConnection(dburlProdukt);
            CallableStatement cs = conProdukt.prepareCall(procName);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    artikel = new Artikel();
                    artikel.setNr(rs.getString("Nr"));
                    artikel.setBezeichnung(rs.getString("Bezeichnung"));
                    artikel.setText(rs.getString("Text"));
                    artikel.setFarben(getFarben(nummer, sprache));
                    artikel.setGroessen(getGroessen(nummer,liste));
                    artikel.setImageUrl(getImage(nummer));
                }
                rs.close();
                cs.close();
                conProdukt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DocDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return artikel;
    }

    @Override
    public List<Artikel> getListArtikel() {
        Artikel artikel;

        List<Artikel> artikels = new ArrayList<>();

        try (
                // Connect to Sybase Database
                Connection conProdukt = DriverManager.getConnection(dburlProdukt);
                Statement statementPro = conProdukt.createStatement(); ResultSet rs = statementPro.executeQuery("select * from Artikel");) {

            while (rs.next()) {

                artikel = new Artikel();
                artikel.setNr(rs.getString("Nr"));
                artikel.setId(rs.getString("ID"));
                artikel.setBezeichnung(rs.getString("Bezeichnung"));
                artikel.setPosition(rs.getRow());
                artikels.add(artikel);
            }
            rs.close();
            statementPro.close();
            conProdukt.close();

        } catch (SQLException ex) {
            Logger.getLogger(DocDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return artikels;
    }
   
    
     @Override
    public List<Artikel> getListArtikelWIthCriteria(String artNumm, String beschreibung,String kuzbeschreibung,String liefArtNumm,int lan,String sprache) {
       List<Artikel> artikels = new ArrayList<>();
        String procName = "{CALL Angebot_Artikel_2( '"+artNumm+"%', '"+beschreibung+"%', '"+kuzbeschreibung+"%','"+liefArtNumm+"%','"+lan+"', '"+sprache+"')}";
        
        try {
            connection = DriverManager.getConnection(dburlProdukt);
            CallableStatement cs = connection.prepareCall(procName);
           try (ResultSet rs = cs.executeQuery()) {
               while (rs.next()) {
                   Artikel artikel = new Artikel();
                   artikel.setNr(rs.getString("Nr"));
                   artikel.setBezeichnung(rs.getString("Bezeichnung"));
                   artikel.setText(rs.getString("Text"));
                   artikel.setKurzzeichen(rs.getString("Kurzbezeichnung"));
                   artikel.setLiefArtNummer(rs.getString("Lief_Art_Nr"));
                   artikel.setPosition(rs.getRow());
                   artikels.add(artikel);
                   
               }
               rs.close();
               cs.close();
           }
        } catch (SQLException ex) {
            Logger.getLogger(DocDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return artikels;
    }
    
    public List<Option> getListOptionen(String varNr, String beschreibung,String artNumm, String nJ, String kundNumm, String sprache) {
        List<Option> options = new ArrayList<>();
        String procName = "{CALL Angebot_Optionen( '"+varNr+"%','"+beschreibung+"%', '"+artNumm+"', '"+nJ+"', '"+kundNumm+"', '"+sprache+"')}";
        Connection conProdukt;
        try {
            conProdukt = DriverManager.getConnection(dburlProdukt);
            CallableStatement cs = conProdukt.prepareCall(procName);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Option option = new Option();
                    option.setNr(rs.getString("Nr"));
                    option.setStd(rs.getString("Std"));
                    option.setBeszeichnung(rs.getString("Bezeichnung"));
                    option.setText(rs.getString("Text"));
                    option.setVkpr(rs.getString("VK-Pr"));
                    option.setKdpr(rs.getString("Kd-Pr"));
                    options.add(option);
                }
                rs.close();
            cs.close();
            conProdukt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DocDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return options;
    }
   
    @Override
    public String getText(String feld, String sprache, String mult) {
        String text = "";
        String procName = "{CALL Angebot_Sprachtext( '"+feld+"','"+sprache+"', '"+mult+"' )}";
        Connection conProdukt;
        try {
            conProdukt = DriverManager.getConnection(dburlProdukt);
            CallableStatement cs = conProdukt.prepareCall(procName);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    text = (rs.getString("Text"));
                }
            rs.close();
            cs.close();
            conProdukt.close();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DocDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return text;
    }

    @Override
    public String getTextExtra(String feld, String sprache) {
        String ret = null;
        try {
            String proc = "SELECT Angebot_Sprachtext_Wert('"+feld+"','"+sprache+"')";  
            Connection conProdukt = DriverManager.getConnection(dburlProdukt);
            Statement s = conProdukt.createStatement();
            try (ResultSet rs = s.executeQuery(proc)) {
                while (rs.next()) {
                    ret = rs.getString(1);          
                }
                rs.close();
                s.close();
                conProdukt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DocDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    @Override
    public List<String> getTexts(String feld, String sprache, String mult) {
         List<String> texts = new ArrayList<>();
        String procName = "{CALL Angebot_Sprachtext( '"+feld+"','"+sprache+"', '"+mult+"' )}";
        Connection conProdukt;
        try {
            conProdukt = DriverManager.getConnection(dburlProdukt);
            CallableStatement cs = conProdukt.prepareCall(procName);
             try (ResultSet rs = cs.executeQuery()) {
                 while (rs.next()) {
                     texts.add(rs.getString("Text"));
                 }
                 rs.close();
                cs.close();
                conProdukt.close();
             }
        } catch (SQLException ex) {
            Logger.getLogger(DocDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return texts;
    }

    private String getFarben(String artNummer, String sprache) throws SQLException {
        String proc = "SELECT hf_artikel_farben_3('"+artNummer+"','"+sprache+"','1')";
        String ret = null;
        Connection conProdukt = DriverManager.getConnection(dburlProdukt);
        Statement s = conProdukt.createStatement();
        try (ResultSet rs = s.executeQuery(proc)) {
            while (rs.next()) {
                ret = rs.getString(1);
            }
            rs.close();
            s.close();
            conProdukt.close();
        }

        return ret;
    }

    public String getGroessen(String artNummer,String liste) throws SQLException {
        String ret = null;
        String sql = "SELECT hf_artikel_groessen_2(Artikel.ID,'" + liste + "') FROM Artikel WHERE Artikel.Nr = '" + artNummer + "'";
        Connection conProdukt = DriverManager.getConnection(dburlProdukt);
        Statement statementPro = conProdukt.createStatement();

        try (ResultSet rsGroessen = statementPro.executeQuery(sql)) {
            while (rsGroessen.next()) {
                
                ret = rsGroessen.getString(1);
            }
            rsGroessen.close();
            statementPro.close();
            conProdukt.close();
        }
        return ret;
    }

    public List<Prises> getCombinations(String artNummer) throws SQLException {
        List<Prises> combinations = new ArrayList<>();
        String sql = "SELECT hf_artikel_farben_2_gleicher_Preis( Preisstaffel.At_ID, Preisstaffel.VK_1, Preisstaffel.Preismenge, 1 ) AS 'Farben', hf_artikel_groessen_2_gleicher_Preis( Preisstaffel.At_ID, Preisstaffel.VK_1, Preisstaffel.Preismenge, 1 ) AS 'Groessen', Preisstaffel.VK_1 AS 'VK1', Preisstaffel.Waehrungszeichen AS 'WZ', Preisstaffel.Preismenge AS 'P_Mng', Preisstaffel.Mengeneinheit AS 'ME', Preisstaffel.Verpackungsmenge AS 'VP_Mng' FROM Preisstaffel, Groessenpreisstaffel, Artikel, Groessenstaffel WHERE Preisstaffel.Groessen_ID = Groessenpreisstaffel.ID AND Preisstaffel.At_ID = Groessenpreisstaffel.At_ID AND Preisstaffel.At_ID = Artikel.ID AND Artikel.Groessenstaffel_ID = Groessenstaffel.ID AND Preisstaffel.Nr = '02' AND Artikel.Nr = '" + artNummer + "' AND Groessenpreisstaffel.Groesse <> '<?>' GROUP BY Farben, Groessen, Preisstaffel.VK_1, Preisstaffel.Waehrungszeichen, Preisstaffel.Preismenge, Preisstaffel.Mengeneinheit, Preisstaffel.Verpackungsmenge ORDER BY 3";
        Connection conProdukt = DriverManager.getConnection(dburlProdukt);
        Statement statementPro = conProdukt.createStatement();

        try (ResultSet rsPrises = statementPro.executeQuery(sql)) {
            while (rsPrises.next()) {
                Prises ret = new Prises();
                
                ret.setFarben(rsPrises.getString("Farben"));
                ret.setGroessen(rsPrises.getString("Groessen"));
                ret.setVk1(rsPrises.getString("VK1"));
                ret.setWz(rsPrises.getString("WZ"));
                ret.setPmng(rsPrises.getString("P_Mng"));
                ret.setMe(rsPrises.getString("ME"));
                ret.setVpMng(rsPrises.getString("VP_Mng"));
                combinations.add(ret);
                
            }
            rsPrises.close();
            statementPro.close();
            conProdukt.close();
        }
        return combinations;
    }

    public List<Prises> getListCombProc(String kundNum, String artNum, String sprache) throws SQLException {
        List<Prises> combinations = new ArrayList<>();
        String procName = "{call hf_artikel_alle_preise_3(?, ?, ?)}";
        Connection conProdukt = DriverManager.getConnection(dburlProdukt);
        CallableStatement cs = conProdukt.prepareCall(procName);
        cs.setString(1, kundNum);				// (1)
        cs.setString(2, artNum);
        cs.setString(3, sprache);
        try (ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                Prises ret = new Prises();
                ret.setFarben(rs.getString("Farbe"));
                ret.setGroessen(rs.getString("Groesse"));
                ret.setArt(rs.getString("Art"));
                ret.setAb(rs.getString("ab"));
                ret.setPreis(rs.getString("Preis"));
                ret.setWz(rs.getString("WZ"));
                ret.setPmng(rs.getString("per"));
                ret.setMe(rs.getString("ME"));
                ret.setVpMng(rs.getString("VE"));
                combinations.add(ret);
                
            }
            rs.close();
            cs.close();
            conProdukt.close();
        }
        return combinations;
    }
  
    private String getImage(String artNummer) throws SQLException
    {
       String proc = "SELECT Angebot_Artikel_Bild('"+artNummer+"')";
        String ret = "";
        Connection conProdukt = DriverManager.getConnection(dburlProdukt);
        Statement s = conProdukt.createStatement();
        try (ResultSet rs = s.executeQuery(proc)) {
            while (rs.next()) {
                if (rs.getString(1) == null) {
                   ret = ""; 
                } else
                {
                  ret = rs.getString(1);  
                }  
            }
            rs.close();
            s.close();
            conProdukt.close();
        }
          
        return ret; 
    }

}
