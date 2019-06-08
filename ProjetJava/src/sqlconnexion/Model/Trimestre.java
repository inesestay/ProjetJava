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
    
    /**
 *les attributes
 */
    private String id;
    private String num;
    private String debut;
    private String fin;
    private String anneescolaireID;
    
    /**
 *les constructeurs
 */
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
    
    /**
     * getters et setters
     */


    /**
     * return  id
     */
    public String getId() {
        return id;
    }

    /**
     * param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * return  num
     */
    public String getNum() {
        return num;
    }

    /**
     * param num the num to set
     */
    public void setNum(String num) {
        this.num = num;
    }

    /**
     * return the debut
     */
    public String getDebut() {
        return debut;
    }

    /**
     * param debut the debut to set
     */
    public void setDebut(String debut) {
        this.debut = debut;
    }

    /**
     * return the fin
     */
    public String getFin() {
        return fin;
    }

    /**
     *param fin the fin to set
     */
    public void setFin(String fin) {
        this.fin = fin;
    }

    /**
     * return the anneescolaireID
     */
    public String getAnneescolaireID() {
        return anneescolaireID;
    }

    /**
     * param anneescolaireID the anneescolaireID to set
     */
    public void setAnneescolaireID(String anneescolaireID) {
        this.anneescolaireID = anneescolaireID;
    }
    
}
