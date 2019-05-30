/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

import sqlconnexion.Model.Personne;
import sqlconnexion.DAO.DAO;
import sqlconnexion.DAO.PersonneDAO;

/**
 *
 * @author inese
 */
public class ProjetJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         DAO<Personne> personneDao = new PersonneDAO(SdzConnection.getInstance());
    for(int i = 1; i < 5; i++){
      Personne personne = personneDao.find(i);
      System.out.println("Personne  N°" + personne.getId() + "  - " + personne.getNom() + " " + personne.getPrenom() + "  - " + personne.getType());
    }
    }
    
}
