/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlconnexion.Model;

/**
 *
 * @author inese
 */
public class Bulletin {
    ///CHARACTERISTICS
    private int id;
    private String appreciation;
    private int trimID;
    private int inscriptionID;
    
    ///METHODS
    public Bulletin(){
        id=0;
        appreciation="";
        trimID=0;
        inscriptionID=0;
    }
    
    public Bulletin(int id,String appreciation,int trimID ,int inscriptionID ){
        this.id=id;
        this.appreciation=appreciation;
        this.trimID=trimID;
        this.inscriptionID=inscriptionID;
    }
    
    ///GETTERS AND SETTERS
    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public int getTrimID() {
        return trimID;
    }

    public void setTrimID(int trimID) {
        this.trimID = trimID;
    }

    public int getInscriptionID() {
        return inscriptionID;
    }

    public void setInscriptionID(int inscriptionID) {
        this.inscriptionID = inscriptionID;
    }
    
}
