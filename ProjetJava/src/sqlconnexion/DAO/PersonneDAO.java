/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlconnexion.DAO;
import sqlconnexion.Model.Personne;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;


/**
 *
 * @author nelly
 */
public class PersonneDAO extends DAO<Personne> {
    
public PersonneDAO(Connection conn) {
    
    
    super(conn);
  }

  @Override
    public boolean create(Personne obj) {
         try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO personne VALUES(?,?,?,?)"
                    ); 
            statement.setObject(1,null,Types.INTEGER); 
            statement.setObject(2,obj.getNom(),Types.VARCHAR); 
            statement.setObject(3,obj.getPrenom(),Types.VARCHAR); 
            statement.setObject(4,obj.getType(),Types.VARCHAR); 
            statement.executeUpdate(); 
             System.out.println("personne créée");
        } catch (SQLException ex) {
            System.out.println("pas create : " + ex.getMessage());
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
    }

  public boolean delete(Personne obj) {
      
    // try {
    //  ResultSet result = this.connect.createStatement(
     //   ResultSet.TYPE_SCROLL_INSENSITIVE,
     //   ResultSet.CONCUR_UPDATABLE).executeQuery("DELETE FROM 'personne' WHERE id = " + obj.getId());
        
   // } catch (SQLException e) {
    //    System.out.println("pas delete");
     //   return false;
        
   // }
    
  //  return true;    
  
   try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM personne WHERE id = " + obj.getId()+""
                    );
           
            statement.executeUpdate(); 
             System.out.println("personne supp");
        } catch (SQLException ex) {
            System.out.println("pas supp");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public boolean update(Personne obj) {
      
     
   // try {
    //  ResultSet result = this.connect.createStatement(
              
     // ResultSet.TYPE_SCROLL_INSENSITIVE,
      //  ResultSet.CONCUR_UPDATABLE).executeQuery("UPDATE personne SET nom= '"+ obj.getNom() +"', prenom= '"+ obj.getPrenom()+"', type= '"+obj.getType()+"' WHERE id = " + obj.getId()+"");
      //System.out.println("personne update");  
      

   // } catch (SQLException e) {
     //   System.out.println("pas update");
      //  return false;
        
   // }
     // return true;   
    
     
     try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE personne SET nom= '"+ obj.getNom() +"', prenom= '"+ obj.getPrenom()+"', type= '"+obj.getType()+"' WHERE id = " + obj.getId()+"");
           
            statement.executeUpdate(); 
             System.out.println("personne update");
        } catch (SQLException ex) {
            System.out.println("pas udpade");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
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
