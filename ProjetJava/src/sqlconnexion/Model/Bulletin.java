/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlconnexion.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author inese
 */
public class Bulletin {
    ///CHARACTERISTICS
    private String id;
    private String appreciation;
    private String trimestreID;
    private String inscriptionID;
    
    ///METHODS
    public Bulletin(){
        id="";
        appreciation="";
        trimestreID="";
        inscriptionID="";
    }
    
    public Bulletin(String id,String appreciation,String trimestreID ,String inscriptionID ){
        this.id=id;
        this.appreciation=appreciation;
        this.trimestreID=trimestreID;
        this.inscriptionID=inscriptionID;
    }
     public Bulletin(String appreciation,String trimestreID ,String inscriptionID ){
       
        this.appreciation=appreciation;
        this.trimestreID=trimestreID;
        this.inscriptionID=inscriptionID;
    }
    
    ///GETTERS AND SETTERS
    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public String getTrimestreID() {
        return trimestreID;
    }

    public void setTrimestreID(String trimID) {
        this.trimestreID = trimID;
    }

    public String getInscriptionID() {
        return inscriptionID;
    }

    public void setInscriptionID(String inscriptionID) {
        this.inscriptionID = inscriptionID;
    }
    
    
}
