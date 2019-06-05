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
             if(("".equals(obj.getNom()))){
        
                throw new SQLException("il manque un ou plusieurs champs");
         }
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO niveau(nom) VALUES(?)");
            statement.setObject(1,obj.getNom(),Types.VARCHAR);
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
     
       String requete = "DELETE FROM niveau WHERE";
      boolean virgule = false;
      
      if(!("".equals(obj.getNom()))){
          requete += " `nom`= "+"'" +obj.getNom()+"'" ;
          virgule=true;
            System.out.println(obj.getNom());
           if(virgule==true ){
              if (!("".equals(obj.getId())) ) {
                  requete =requete + " AND" ;
              }
            }       
      }
            
      
      if(!("".equals(obj.getId()))){
          requete += " `id` = "+"'"+obj.getId()+ "' ";
      }
      
      System.out.println(requete);
                  
  
   try {
            PreparedStatement statement = this.connect.prepareStatement(requete);
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
      
       String requete = "UPDATE niveau SET ";
      boolean virgule = false;
      
      if(!("".equals(obj.getNom()))){
          requete += " nom = "+"'"+obj.getNom()+ "' " + " ";
       
      }     
            
      requete += "WHERE id = " + obj.getId()+"" ;
      
      System.out.println(requete);
      
  try{
            PreparedStatement statement = this.connect.prepareStatement(requete);
           
            statement.executeUpdate(); 
             System.out.println("Niveau update");
        } catch (SQLException ex) {
            System.out.println("pas udpade Niveau");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public Niveau find(String id) {
    Niveau d = new Niveau();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM niveau WHERE id = " + id);
      if(result.first())
        d = new Niveau(result.getString("id"),result.getString("nom"));         
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
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM niveau");
        //+nomTable+
           while(result.next()) {

               String id = result.getString(1);
               String nom = result.getString(2);
              
               Niveau obj = new Niveau(id,nom);
               table.add(obj);

          }
        

        } catch (SQLException e) {
         System.out.println("pas arraylist");
        }
       return table;
  }
}
