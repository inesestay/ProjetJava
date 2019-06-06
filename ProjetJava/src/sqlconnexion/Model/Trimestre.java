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
public class Trimestre {
    
    ///CHARACTERISTICS
    private String id;
    private String num;
    private String debut;
    private String fin;
    private String anneescolaireID;
    
    ///CONSTRUCTORS
    public Trimestre(){
        id = "";
        num="";
        debut="";
        fin="";
        anneescolaireID="";
    }
    
    public Trimestre(String id, String num, String debut, String fin, String anneescolaireID){
        this.id=id;
        this.num=num;
        this.debut=debut;
        this.fin=fin;
        this.anneescolaireID=anneescolaireID;      
    }
    
    public Trimestre( String num, String debut, String fin, String anneescolaireID){

        this.num=num;
        this.debut=debut;
        this.fin=fin;
        this.anneescolaireID=anneescolaireID;      
    }
    
    ///GETTERS AND SETTERS

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDebut() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getAnneescolaireID() {
        return anneescolaireID;
    }

    public void setAnneescolaireID(String anneescolaireID) {
        this.anneescolaireID = anneescolaireID;
    }
}
