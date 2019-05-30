/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlconnexion.Model;

/**
 *
 * @author nelly
 */
public class Personne {
    
    private int id;
    private String nom;
    private String prenom;
    private String type;
    
    public Personne()
    {
        this.id=0;
        this.nom="";
        this.prenom="";
        this.type="";
        
    }
    
    
    public Personne (int id, String nom, String prenom, String type)
    {
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.type=type;
    }
    
    public int getId(){return this.id;}
    public String getNom(){return this.nom;}
    public String getPrenom(){return this.prenom;}
    public String getType(){return this.type;}
    
    public void setId(int id){this.id = id;}
    public void setNom(String nom){this.nom = nom;}
    public void setPrenom(String prenom){this.prenom = prenom;}
    public void setType(String type){this.type = type;}
}
