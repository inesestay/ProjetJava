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
public class Eleve extends Personne{
    
     public Eleve(int id, String nom, String prenom, String type){
        super(id, nom, prenom,type);
        
    }
    
    public Eleve(){
        super();
    }
    
}