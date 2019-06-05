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
public class AnneeScolaireDAO extends DAO<AnneeScolaire>{
    
    public AnneeScolaireDAO(Connection conn) {
    
    
    super(conn);
  }

  @Override
    public boolean create(AnneeScolaire obj) {
         try {
             
              if(("".equals(obj.getAnneeScolaireID()))){
        
                throw new SQLException("il manque un ou plusieurs champs");
         }
             
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO anneescolaire(anneescolaireID) VALUES(?)"
                    ); 
            statement.setObject(1,obj.getAnneeScolaireID(),Types.INTEGER); 
           
             System.out.println("annee créée");
        } catch (SQLException ex) {
            System.out.println("pas create : " + ex.getMessage());
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
    }

  public boolean delete(AnneeScolaire obj) {
      
     String requete = "DELETE FROM anneeScolaire WHERE";
      boolean virgule = false;
      
      if(!("".equals(obj.getAnneeScolaireID()))){
          requete += " `type` = "+"'"+obj.getAnneeScolaireID()+ "'";
          virgule=true;
                 
      }     
      
  
   try {
            PreparedStatement statement = this.connect.prepareStatement(requete);
           
            statement.executeUpdate(); 
             System.out.println("annee supp");
        } catch (SQLException ex) {
            System.out.println("pas supp annee");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public boolean update(AnneeScolaire obj) {
      
      String requete = "UPDATE personne SET ";
      boolean virgule = false;
      
      if(!("".equals(obj.getAnneeScolaireID()))){
          requete += " nom= "+"'" +obj.getAnneeScolaireID()+"'" ;
              
      }
      
      requete += "WHERE id = " + obj.getAnneeScolaireID()+"" ;
      
      System.out.println(requete);
     
     try {
            PreparedStatement statement = this.connect.prepareStatement(requete);
           
            statement.executeUpdate(); 
             System.out.println("annee update");
        } catch (SQLException ex) {
            System.out.println("annee pas udpade");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public AnneeScolaire find(String id) {
    AnneeScolaire a = new AnneeScolaire();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM anneescolaire WHERE id = " + id);
      if(result.first())
        a = new AnneeScolaire(id);         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return a;
  }
  
   public ArrayList<Object> retour()
  {
       ArrayList<Object> table = new ArrayList();
       
       try {
        ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM anneescolaire");
        //+nomTable+
  
           while(result.next()) {

               String id = result.getString(1);
               AnneeScolaire obj = new AnneeScolaire(id);
               table.add(obj);

          }
       } catch (SQLException e) {
         System.out.println("pas arraylist");
        }
       return table;
  }
}
