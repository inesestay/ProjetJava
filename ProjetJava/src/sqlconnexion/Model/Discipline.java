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
public class Discipline {
    ///CHARACTERISTICS
    private String id;
    private String nom;
    
    ///CONSTRUCTORS
    public Discipline(String id, String nom){
    this.id = id;
    this.nom=nom;
    }
     public Discipline( String nom){

    this.nom=nom;
    }
    
    public Discipline(){
        id="";
        nom="";
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
    
}
