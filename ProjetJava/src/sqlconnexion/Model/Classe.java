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
public class Classe {
    ///CHARACTERISTICS
    private String id;
    private String nom;
    private String niveauID;
    private String anneescolaireID;

    ///CONSTRUCTORS
    public Classe(String id, String nom, String niveauID, String anneescolaireID){
        this.id=id;
        this.nom=nom;
        this.niveauID=niveauID;
        this.anneescolaireID=anneescolaireID;
    }
    public Classe( String nom, String niveauID, String anneescolaireID){
  
        this.nom=nom;
        this.niveauID=niveauID;
        this.anneescolaireID=anneescolaireID;
    }
    
    public Classe(){
        id="";
        nom="";
        niveauID="";
        anneescolaireID="";
    }
    
    ///GETTERS AND SETTERS

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the niveauID
     */
    public String getNiveauID() {
        return niveauID;
    }

    /**
     * @param niveauID the niveauID to set
     */
    public void setNiveauID(String niveauID) {
        this.niveauID = niveauID;
    }

    /**
     * @return the anneescolaireID
     */
    public String getAnneescolaireID() {
        return anneescolaireID;
    }

    /**
     * @param anneescolaireID the anneescolaireID to set
     */
    public void setAnneescolaireID(String anneescolaireID) {
        this.anneescolaireID = anneescolaireID;
    }

    
    
}
