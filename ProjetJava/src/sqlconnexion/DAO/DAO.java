/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlconnexion.DAO;
import java.sql.Connection;



import java.sql.*;
import java.util.ArrayList;
import sqlconnexion.Model.*;

/**
 * abstract class template for the connection with the database
 * @param <T> model use by the DAO
 * @author samrhein
 */
public abstract class DAO<T> {
    
    /**
     * the connection link of the element will be use to vreate the preparedStatement 
     */
    protected Connection connect = null;
   
    /**
     * constructor to set the connection need to be called by daughter class
     * @param conn
     */
    public DAO(Connection conn){
        this.connect = conn;
    }
   
  
  /**
   * will insert an element in the DB
   * @param obj the model to insert
   * @return yes or false if update done
   */
  public abstract boolean create(T obj);

  
  /**
   * will remove an element in the DB
   * @param obj delete this object
   * @return true or false if delete
   */
  public abstract boolean delete(T obj);


  /**
   * will update an element 
   * @param obj update this element
   * @return true or false if update
   */
  public abstract boolean update(T obj);

 
  /**
   * will find an element with the ID
   * @param id
   * @return the model with the correct key ID
   */
  public abstract T find(String id);
    
    
  /**
   * will search a table
   * @param 
   * @return the table
   */
  //public abstract ArrayList<Object> table(String name);
    public abstract ArrayList<Object> retour();
    
   
}
