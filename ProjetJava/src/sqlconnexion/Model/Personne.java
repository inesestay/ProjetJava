/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlconnexion.Model;

import java.util.ArrayList;

/**
 *
 * @author inese
 */
public class Personne {
    
    private String id;
    private String nom;
    private String prenom;
    private String type;
    private float moyenne;
    public ArrayList<String> dd;
    
    public Personne()
    {
        this.id="";
        this.nom="";
        this.prenom="";
        this.type="";
        this.moyenne=-1;
        dd = new ArrayList<>();
        
    }
    
    public Personne (String nom, String prenom, String type, float m)
    {
        this.id= id;
        this.nom=nom;
        this.prenom=prenom;
        this.type=type;
        this.moyenne=m;
        dd = new ArrayList<>();
    }
    
    public Personne (String id, String nom, String prenom, String type)
    {
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.type=type;
        dd = new ArrayList<>();
    }
    
    public String getId(){return this.id;}
    public String getNom(){return this.nom;}
    public String getPrenom(){return this.prenom;}
    public String getType(){return this.type;}
    public float getMoyenne(){return this.moyenne;}
    
    public void setId(String id){this.id = id;}
    public void setNom(String nom){this.nom = nom;}
    public void setPrenom(String prenom){this.prenom = prenom;}
    public void setType(String type){this.type = type;}
    public void setMoyenne(Float m){this.moyenne = m;}
    public void setDiscipline(ArrayList<String> dd)
    {
        this.dd.clear();
        for(int i=0; i<dd.size(); i++){ this.dd.add(dd.get(i));}
       
    }
}
