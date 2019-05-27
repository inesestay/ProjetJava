/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlconnexion.factory;
///REPRIS DE http://trois-litres-de-vie.fr/
/**
 *
 * @author inese
 */


import java.sql.*;
import java.util.logging.*;
import sqlconnexion.DAO.*;

/**
 * this is a static class able to be call everywhere in the code and construct the good DAO.
 * surcharge connection on SQL so that is done only one time
 * @author samrhein
 */
public class DAOFactory {
    
    /**
     * constante static containing the connection variable of the class
     */
    protected static final Connection conn;   
   
    
    /**
     * this is a specific way to init final variable throwing exceptions
     */
    static{
        Connection tmp = null;
        
        try {
            // subscribe to your DriverManager as we use mysql-connector add this :
            Class.forName("com.mysql.jdbc.Driver"); 
            // get the connection variable
            // jdbc:mysl:// is the protocol URI (like http:// is for http)
            // localhost cause we are from a wamp server
            // ecole is the name of database
            // second parameter the id for connecting to the mysql db ( on phpmyadmin)
            // thirs parameter is the password
            tmp = DriverManager.getConnection("jdbc:mysql://localhost/gestionecole","root","");
         } catch (ClassNotFoundException ex) {
                Logger.getLogger(DAOFactory.class.getName()).log(Level.SEVERE, null, ex);
         }
         catch (SQLException ex) {
            Logger.getLogger(DAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn = tmp;
    }
    
 
  /**
  * Retourne un objet Professeur interagissant avec la BDD
  * @return DAO
  */
  public static DAO getAnneeScolaireDAO(){
    return new DAOAnneeScolaire(conn);
  }

  
    
}
