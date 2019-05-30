/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;
import java.sql.Connection;
import java.sql.SQLException;
import sqlconnexion.Model.Personne;
import sqlconnexion.DAO.DAO;
import sqlconnexion.DAO.PersonneDAO;
import sqlconnexion.factory.DAOFactory;

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
        pers.create(new Personne(4,"sébastien","ek","fr"));
          }
          catch(SQLException e){
              System.out.println("sql");
          }
           catch(ClassNotFoundException e){
               System.out.println("not found");
           }
            catch (Exception e){
              System.out.println("oof");
          }
       
        
    }
    
}
