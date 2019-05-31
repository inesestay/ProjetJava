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

/**
 *
 * @author nelly
 */
public class BulletinDAO extends DAO<Bulletin> {
    public BulletinDAO(Connection conn) {
    
    
    super(conn);
  }

  @Override
    public boolean create(Bulletin obj) {
         try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO bulletin VALUES(?,?,?,?)"
                    );
            statement.setObject(1,null,Types.INTEGER); 
            statement.setObject(2,obj.getAppreciation(),Types.VARCHAR);
            statement.setObject(3,obj.getTrimestreID(),Types.INTEGER);
            statement.setObject(4,obj.getInscriptionID(),Types.INTEGER);
            
            statement.executeUpdate(); 
             System.out.println(" bulletin créée");
        } catch (SQLException ex) {
            System.out.println("pas create bulletin");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
    }

  public boolean delete(Bulletin obj) {
     
  
   try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM bulletin WHERE id = " + obj.getId()+"");
           
            statement.executeUpdate(); 
             System.out.println("bulletin supp");
        } catch (SQLException ex) {
            System.out.println("pas supp bulletin");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public boolean update(Bulletin obj) {
      
  try{
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE bulletin SET appreciation= '"+ obj.getAppreciation() +"' WHERE id = " + obj.getId()+"");
           
            statement.executeUpdate(); 
             System.out.println("bulle update");
        } catch (SQLException ex) {
            System.out.println("pas udpade bulle");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public Bulletin find(int id) {
    Bulletin d = new Bulletin();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM bulletin WHERE id = " + id);
      if(result.first())
        d = new Bulletin(id,result.getString("appreciation"),result.getInt("trimestreID"),result.getInt("inscriptionID") );         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return d;
  }
}
