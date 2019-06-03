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
import java.util.ArrayList;
import sqlconnexion.Model.*;

/**
 *
 * @author nelly
 */
public class TrimestreDAO extends DAO<Trimestre> {
    
public TrimestreDAO(Connection conn) {
    
    
    super(conn);
  }

  @Override
    public boolean create( Trimestre obj) {
         try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO  trimestre VALUES(?,?,?,?,?)");
            statement.setObject(1,null,Types.INTEGER); 
            statement.setObject(2,obj.getNum(),Types.INTEGER); 
            statement.setObject(3,obj.getDebut(),Types.INTEGER); 
            statement.setObject(4,obj.getFin(),Types.INTEGER); 
            statement.setObject(5,obj.getAnneescolaireID(),Types.INTEGER);   
            statement.executeUpdate(); 
             System.out.println(" Trimestre créée");
        } catch (SQLException ex) {
            System.out.println("pas create  Trimestre");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
    }

  public boolean delete( Trimestre obj) {
     
  
   try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM  trimestre WHERE id = " + obj.getId()+"" );
           
            statement.executeUpdate(); 
             System.out.println(" Trimestre supp");
        } catch (SQLException ex) {
            System.out.println("pas supp  Trimestre");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public boolean update( Trimestre obj) {
      
  try{
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE  trimestre SET numero= '"+ obj.getNum() +"', debut= '"+ obj.getDebut() +"',fin= '"+ obj.getFin() +"', AnneeScolaireId= '"+ obj.getAnneescolaireID() +"' WHERE id = " + obj.getId()+"");
           
            statement.executeUpdate(); 
             System.out.println(" Trimestre update");
        } catch (SQLException ex) {
            System.out.println("pas udpade  Trimestre");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public  Trimestre find(int id) {
     Trimestre d = new  Trimestre();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM  trimestre WHERE id = " + id);
      if(result.first())
        d = new  Trimestre(id,result.getInt("num"), result.getInt("debut"), result.getInt("fin"), result.getInt("anneescolaireID"));         
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
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM trimestre");
        //+nomTable+
           while(result.next()) {

               int id = result.getInt(1);
               int a = result.getInt(2);
               int z = result.getInt(3);
               int e = result.getInt(4);
               int r = result.getInt(5);

               Trimestre obj = new Trimestre(id,a,z,e,r);
               table.add(obj);

          }
        

        } catch (SQLException e) {
         System.out.println("pas arraylist");
        }
       return table;
  }
}

