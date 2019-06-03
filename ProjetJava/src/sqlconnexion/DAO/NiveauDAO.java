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
public class NiveauDAO extends DAO<Niveau> {
    
public NiveauDAO(Connection conn) {
    
    super(conn);
  }

  @Override
    public boolean create(Niveau obj) {
         try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO niveau VALUES(?,?)");
            statement.setObject(1,null,Types.INTEGER); 
            statement.setObject(2,obj.getNom(),Types.VARCHAR); 
            
            statement.executeUpdate(); 
             System.out.println("Niveau créée");
        } catch (SQLException ex) {
            System.out.println("pas create Niveau");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
    }

  public boolean delete(Niveau obj) {
     
  
   try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM niveau WHERE id = " + obj.getId()+"" );
           
            statement.executeUpdate(); 
             System.out.println("Niveau supp");
        } catch (SQLException ex) {
            System.out.println("pas supp Niveau");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public boolean update(Niveau obj) {
      
  try{
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE niveau SET nom= '"+ obj.getNom() +"' WHERE id = " + obj.getId()+"");
           
            statement.executeUpdate(); 
             System.out.println("Niveau update");
        } catch (SQLException ex) {
            System.out.println("pas udpade Niveau");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public Niveau find(int id) {
    Niveau d = new Niveau();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM niveau WHERE id = " + id);
      if(result.first())
        d = new Niveau(id,result.getString("nom"));         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return d;
  }
  
   public ArrayList<Personne> retour()
  {
       ArrayList<Personne> table = new ArrayList();
       
       try {
        ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM personne");
        //+nomTable+
        if(result.first())
        {
           while(result.next()) {

               int id = result.getInt(1);
               String nom = result.getString(2);
               String prenom = result.getString(3);
               String type = result.getString(4);

               Personne obj = new Personne(id,nom,prenom,type);
               table.add(obj);

          }
        }

        } catch (SQLException e) {
         System.out.println("pas arraylist");
        }
       return table;
  }
}
