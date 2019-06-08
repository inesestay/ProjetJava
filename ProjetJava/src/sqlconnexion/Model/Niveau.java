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
public class Niveau {
    ///CHARACTERISTICS
    private String id;
    private String nom;
    
    ///CONSTRUCTORS
    public Niveau(){
        id="";
        nom="";
    }
    
    public Niveau(String id, String nom){
        this.id=id;
        this.nom=nom;
    }
    public Niveau( String nom){
       
        this.nom=nom;
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

  
   
}
