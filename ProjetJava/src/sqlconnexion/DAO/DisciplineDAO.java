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
public class DisciplineDAO extends DAO<Discipline> {
    
public DisciplineDAO(Connection conn) {
    
    
    super(conn);
  }

  @Override
    public boolean create(Discipline obj) {
         try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO discipline VALUES(?,?)");
            statement.setObject(1,null,Types.INTEGER); 
            statement.setObject(2,obj.getNom(),Types.VARCHAR); 
            
            statement.executeUpdate(); 
             System.out.println("discipline créée");
        } catch (SQLException ex) {
            System.out.println("pas create discipline");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
    }

  public boolean delete(Discipline obj) {
     
  
   try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM discipline WHERE id = " + obj.getId()+"" );
           
            statement.executeUpdate(); 
             System.out.println("discipline supp");
        } catch (SQLException ex) {
            System.out.println("pas supp discipline");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public boolean update(Discipline obj) {
      
  try{
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE discipline SET nom= '"+ obj.getNom() +"' WHERE id = " + obj.getId()+"");
           
            statement.executeUpdate(); 
             System.out.println("discipline update");
        } catch (SQLException ex) {
            System.out.println("pas udpade discipline");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public Discipline find(int id) {
    Discipline d = new Discipline();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM discipline WHERE id = " + id);
      if(result.first())
        d = new Discipline(id,result.getString("nom"));         
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
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM discipline");
        //+nomTable+
           while(result.next()) {

               int id = result.getInt(1);
               String nom = result.getString(2);

               Discipline obj = new Discipline(id,nom);
               table.add(obj);

          }
        

        } catch (SQLException e) {
         System.out.println("pas arraylist");
        }
       return table;
  }
}
