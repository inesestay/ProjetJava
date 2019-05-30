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
    private int id;
    private String nom;
    private int niveauID;
    private int anneescolaireID;

    ///CONSTRUCTORS
    public Classe(int id, String nom, int niveauID, int anneescolaireID){
        this.id=id;
        this.nom=nom;
        this.niveauID=niveauID;
        this.anneescolaireID=anneescolaireID;
    }
    
    public Classe(){
        id=0;
        nom="";
        niveauID=0;
        anneescolaireID=0;
    }
    
    ///GETTERS AND SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNiveauID() {
        return niveauID;
    }

    public void setNiveauID(int niveauID) {
        this.niveauID = niveauID;
    }

    public int getAnneescolaireID() {
        return anneescolaireID;
    }

    public void setAnneescolaireID(int anneescolaireID) {
        this.anneescolaireID = anneescolaireID;
    }
    
    
}
