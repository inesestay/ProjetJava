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
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO anneescolaire VALUES(?)"
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
      
    
  
   try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM annescolaire WHERE id = " + obj.getAnneeScolaireID()+""
                    );
           
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
      
     
     
     try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE anneescolaire SET nom= '"+ obj.getAnneeScolaireID() +"'WHERE id = " + obj.getAnneeScolaireID()+"");
           
            statement.executeUpdate(); 
             System.out.println("annee update");
        } catch (SQLException ex) {
            System.out.println("annee pas udpade");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public AnneeScolaire find(int id) {
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
