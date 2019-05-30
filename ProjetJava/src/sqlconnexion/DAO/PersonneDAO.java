/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlconnexion.DAO;
import sqlconnexion.Model.Personne;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author nelly
 */
public class PersonneDAO extends DAO<Personne> {
    
public PersonneDAO(Connection conn) {
    
    
    super(conn);
  }

  public boolean create(Personne obj) {
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_UPDATABLE).executeQuery("INSERT INTO personne VALUES("+obj.getId()+","+obj.getNom()+","+obj.getPrenom()+")" );
        
    } catch (SQLException e) {
        System.out.println("pas create");
         return false;
    }
   return true;    
  }

  public boolean delete(Personne obj) {
      
     try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_UPDATABLE).executeQuery("DELETE FROM 'personne' WHERE id = " + obj.getId());
        
    } catch (SQLException e) {
        System.out.println("pas delete");
        return false;
        
    }
    
    return true;    
  }
   
  public boolean update(Personne obj) {
      
      String nomModif = obj.getNom();
      String prenomModif = obj.getPrenom();
      String typeModif = obj.getType();
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_UPDATABLE).executeQuery("UPDATE personne SET nom= 'nomModif', prenom= 'prenomModif', type= 'typeModif' WHERE id = " + obj.getId());
        return false;
    } catch (SQLException e) {
        System.out.println("pas update");
    }
      return true;   
    
  }
   
  public Personne find(int id) {
    Personne personne = new Personne();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM personne WHERE id = " + id);
      if(result.first())
        personne = new Personne(id,result.getString("nom"),result.getString("prenom"),result.getString("type"));         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return personne;
  }
}
