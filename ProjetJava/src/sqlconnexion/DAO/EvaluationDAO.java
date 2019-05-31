/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlconnexion.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import sqlconnexion.Model.*;

/**
 *
 * @author nelly
 */
public class EvaluationDAO extends DAO<Evaluation> {
    
public EvaluationDAO(Connection conn) {
    
    
    super(conn);
  }

  @Override
    public boolean create(Evaluation obj) {
         try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO evaluation VALUES(?,?)");
            statement.setObject(1,null,Types.INTEGER); 
            statement.setObject(2,obj.getAppreciation(),Types.VARCHAR); 
            statement.setObject(3,obj.getNote() ,Types.INTEGER); 
            statement.setObject(4,obj.getDetailBulletinID(),Types.INTEGER); 
            
            statement.executeUpdate(); 
             System.out.println("Evaluation créée");
        } catch (SQLException ex) {
            System.out.println("pas create Evaluation");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
    }

  public boolean delete(Evaluation obj) {
     
  
   try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM evaluation WHERE id = " + obj.getId()+"" );
           
            statement.executeUpdate(); 
             System.out.println("Evaluation supp");
        } catch (SQLException ex) {
            System.out.println("pas supp Evaluation");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public boolean update(Evaluation obj) {
      
  try{
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE evaluation SET appreciation= '"+ obj.getAppreciation() +"',note= '"+ obj.getNote() +"', detailBulletinID= '"+ obj.getDetailBulletinID() +"' WHERE id = " + obj.getId()+"");
           
            statement.executeUpdate(); 
             System.out.println("Evaluation update");
        } catch (SQLException ex) {
            System.out.println("pas udpade Evaluation");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public Evaluation find(int id) {
    Evaluation d = new Evaluation();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM evaluation WHERE id = " + id);
      if(result.first())
         
        d = new Evaluation(id,result.getString("appreciation"), result.getInt("note"), result.getInt("detailBulletinID"));         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return d;
  }
}

