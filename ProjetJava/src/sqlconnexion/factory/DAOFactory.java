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
    
 
  protected static final Connection conn = SdzConnection.getInstance();   
   
  /**
  * Retourne un objet Classe interagissant avec la BDD
  * @return DAO
  */
  public static DAO getClasseDAO(){
    return new ClasseDAO(conn);
  }

  /**
  * Retourne un objet Professeur interagissant avec la BDD
  * @return DAO
  */
  public static DAO getPersonneDAO(){
    return new PersonneDAO(conn);
  }

     
    
}
