/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlconnexion.factory;
///REPRIS DE http://trois-litres-de-vie.fr/
/**
 *
 * @author nelly
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
            tmp = DriverManager.getConnection("jdbc:mysql://localhost/ecole","root","");
         } catch (ClassNotFoundException ex) {
                Logger.getLogger(DAOFactory.class.getName()).log(Level.SEVERE, null, ex);
         }
         catch (SQLException ex) {
            Logger.getLogger(DAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn = tmp;
    }
    


  /**
  * Retourne un objet Personne interagissant avec la BDD
  * @return DAO
  */
  public static DAO getPersonneDAO(){
    return new PersonneDAO(conn);
  }
  
   /**
  * Retourne un objet annee interagissant avec la BDD
  * @return DAO
  */
  public static DAO getAnneeScolaireDAO(){
    return new AnneeScolaireDAO(conn);
  }
  
  
  /**
  * Retourne un objet Discipline interagissant avec la BDD
  * @return DAO
  */
  public static DAO getDisciplineDAO(){
    return new DisciplineDAO(conn);
  }
  
    /**
  * Retourne un objet Année Scolaire interagissant avec la BDD
  * @return DAO
  */
  public static DAO getAnneeScolaireDAO(){
    return new AnneeScolaireDAO(conn);
  }

    /**
  * Retourne un objet Bulletin interagissant avec la BDD
  * @return DAO
  */
  public static DAO getBulletinDAO(){
    return new BulletinDAO(conn);
  }
     
    /**
  * Retourne un objet Classe interagissant avec la BDD
  * @return DAO
  */
  public static DAO getClasseDAO(){
    return new ClasseDAO(conn);
  }
  
    /**
  * Retourne un objet Detail Bulletin interagissant avec la BDD
  * @return DAO
  */
  public static DAO getDetailBulletinDAO(){
    return new DetailBulletinDAO(conn);
  }
    
  /**
  * Retourne un objet Enseignement interagissant avec la BDD
  * @return DAO
  */
  public static DAO getEnseignementDAO(){
    return new EnseignementDAO(conn);
  }
  
  /**
  * Retourne un objet Evaluation interagissant avec la BDD
  * @return DAO
  */
  public static DAO getEvaluationDAO(){
    return new EvaluationDAO(conn);
  }
  
  /**
  * Retourne un objet Niveau interagissant avec la BDD
  * @return DAO
  */
  public static DAO getNiveauDAO(){
    return new NiveauDAO(conn);
  }
    
  
  /**
  * Retourne un objet Trimestre interagissant avec la BDD
  * @return DAO
  */
  public static DAO getTrimestreDAO(){
    return new TrimestreDAO(conn);
  }
  
  /**
  * Retourne un objet Inscription interagissant avec la BDD
  * @return DAO
  */
  public static DAO getInscriptionDAO(){
    return new inscriptionDAO(conn);
  }
}
