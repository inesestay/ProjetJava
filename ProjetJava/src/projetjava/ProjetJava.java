/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import sqlconnexion.Model.*;
import sqlconnexion.DAO.DAO;
import sqlconnexion.DAO.PersonneDAO;
import sqlconnexion.factory.DAOFactory;
import projetjava.Graphique.MainGraphique;

/**
 *
 * @author inese
 */
public class ProjetJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        
          /********************************************************
         * Connection with factory and DAO 
         * can be everywhere in the code cause DAOFactory is a static method
         ********************************************************/
        
          try{
            Connexion c= new Connexion("ecole","root","");
            DAO<Personne> pers = DAOFactory.getPersonneDAO();
            DAO<AnneeScolaire> annee = DAOFactory.getAnneeScolaireDAO();
     //  pers.create(new Personne(5,"sébastien","ek","fr"));
        //Personne p;
        ArrayList<Object> table ;
      table =  annee.retour();
      
      for (int i= 0; i<table.size(); i++)
      {
          AnneeScolaire a= (AnneeScolaire) table.get(i);
          System.out.println("indice : "+a.getAnneeScolaireID());
      }
       // p =  pers.find(5);
       // pers.delete(p);
     //  p.setPrenom( "nel");
      // pers.update(p);
        
       // System.out.println(p.getNom());
          }
          catch(SQLException e){
              System.out.println("sql");
          }
           catch(ClassNotFoundException e){
               System.out.println("not found");
           }
           
          }
        
        // TODO code application logic here
      //  MainGraphique.launch();
    }
    

