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
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNiveauID() {
        return niveauID;
    }

    public void setNiveauID(String niveauID) {
        this.niveauID = niveauID;
    }

    public String getAnneescolaireID() {
        return anneescolaireID;
    }

    public void setAnneescolaireID(String anneescolaireID) {
        this.anneescolaireID = anneescolaireID;
    }
    
    
}
