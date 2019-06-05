/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlconnexion.DAO;

import sqlconnexion.Model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
/**
 *
 * @author nelly
 */
public class DetailBulletinDAO extends DAO<DetailBulletin> {
    
public DetailBulletinDAO(Connection conn) {
    
    
    super(conn);
  }

  @Override
    public boolean create(DetailBulletin obj) {
         try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO detailbulletin VALUES(?,?,?,?)");
            statement.setObject(1,null,Types.INTEGER); 
            statement.setObject(2,obj.getAppreciation(),Types.VARCHAR);
            statement.setObject(3,obj.getBulletinID(),Types.INTEGER);
            statement.setObject(4,obj.getEnseignementID(),Types.INTEGER);
            
            statement.executeUpdate(); 
             System.out.println("bulletin créée");
        } catch (SQLException ex) {
            System.out.println("pas create bulletin");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
    }

  public boolean delete(DetailBulletin obj) {
     
  
   try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM bulletin WHERE id = " + obj.getId()+"" );
           
            statement.executeUpdate(); 
             System.out.println("bulletin supp");
        } catch (SQLException ex) {
            System.out.println("pas supp bulletin");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public boolean update(DetailBulletin obj) {
      
  try{
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE bulletin SET appreciation= '"+ obj.getAppreciation() +"', bulletinID = '"+ obj.getBulletinID() +"' , enseignementId = '"+ obj.getEnseignementID() +"'WHERE id = " + obj.getId()+"");
           
            statement.executeUpdate(); 
             System.out.println("detail update");
        } catch (SQLException ex) {
            System.out.println("pas udpade detail");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public DetailBulletin find(String id) {
    DetailBulletin d = new DetailBulletin();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM discipline WHERE id = " + id);
      if(result.first())
        d = new DetailBulletin(result.getString("id"),result.getString("nom"), result.getString("bulletinID"), result.getString("enseignementID"));         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return d;
  }
  
   public ArrayList<Object> retour()
  {
       ArrayList<Object> table = new ArrayList();
       
       try {
        ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM detailbulletin");
        //+nomTable+
           while(result.next()) {

               String id = result.getString(1);
               String nom = result.getString(2);
               String tri = result.getString(3);
               String insc = result.getString(4);

               DetailBulletin obj = new DetailBulletin(id,nom,tri,insc);
               table.add(obj);

          }
        
        } catch (SQLException e) {
         System.out.println("pas arraylist");
        }
       return table;
  }
}
