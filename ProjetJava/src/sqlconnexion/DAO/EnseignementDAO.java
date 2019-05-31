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
public class EnseignementDAO extends DAO<Enseignement> {
    
public EnseignementDAO(Connection conn) {
    
    
    super(conn);
  }

  @Override
    public boolean create(Enseignement obj) {
         try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO enseignement VALUES(?,?,?,?)");
            statement.setObject(1,null,Types.INTEGER); 
            statement.setObject(2,obj.getClasseID(),Types.INTEGER); 
            statement.setObject(3,obj.getDisciplineID(),Types.INTEGER); 
            statement.setObject(4,obj.getEnseignantID(),Types.INTEGER); 
            
            statement.executeUpdate(); 
             System.out.println("Enseignement créée");
        } catch (SQLException ex) {
            System.out.println("pas create Enseignement");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
    }

  public boolean delete(Enseignement obj) {
     
  
   try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM enseignement WHERE id = " + obj.getId()+"" );
           
            statement.executeUpdate(); 
             System.out.println("Enseignement supp");
        } catch (SQLException ex) {
            System.out.println("pas supp Enseignement");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public boolean update(Enseignement obj) {
      
  try{
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE enseignement SET classeID= '"+ obj.getClasseID() +"',disciplineId= '"+ obj.getDisciplineID() +"',personneId = '"+ obj.getEnseignantID() +"' WHERE id = " + obj.getId()+"");
           
            statement.executeUpdate(); 
             System.out.println("Enseignement update");
        } catch (SQLException ex) {
            System.out.println("pas udpade Enseignement");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public Enseignement find(int id) {
    Enseignement d = new Enseignement();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM enseignement WHERE id = " + id);
      if(result.first())
          
        d = new Enseignement(id,result.getInt("classeID"), result.getInt("enseignantID"), result.getInt("discipID"));         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return d;
  }
}
