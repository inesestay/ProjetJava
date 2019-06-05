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
public class ClasseDAO extends DAO<Classe> {
    
public ClasseDAO(Connection conn) {
    
    
    super(conn);
  }

  @Override
    public boolean create(Classe obj) {
         try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO classe VALUES(?,?,?,?)"
                    );
            statement.setObject(1,null,Types.INTEGER); 
            statement.setObject(2,obj.getNom(),Types.VARCHAR); 
            statement.setObject(3,obj.getNiveauID(),Types.INTEGER);
            statement.setObject(4,obj.getAnneescolaireID(),Types.INTEGER);
            
            statement.executeUpdate(); 
             System.out.println("classe créée");
        } catch (SQLException ex) {
            System.out.println("pas create classe");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
    }

  public boolean delete(Classe obj) {
     
  
   try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM classe WHERE id = " + obj.getId()+"");
           
            statement.executeUpdate(); 
             System.out.println("classe supp");
        } catch (SQLException ex) {
            System.out.println("pas supp classe");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public boolean update(Classe obj) {
      
  try{
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE classe SET nom= '"+ obj.getNom() +"',NiveauId= '"+ obj.getNiveauID() +"', AnneeScolaireId= '"+ obj.getAnneescolaireID() +"' WHERE id = " + obj.getId()+"");
           
            statement.executeUpdate(); 
             System.out.println("classe update");
        } catch (SQLException ex) {
            System.out.println("pas udpade classe");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public Classe find(String id) {
    Classe d = new Classe();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM classe WHERE id = " + id);
      if(result.first())
          
        d = new Classe(result.getString("id"),result.getString("nom"), result.getString("NiveauID") , result.getString("AnneeScolaireId"));         
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
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM classe");
        //+nomTable+
           while(result.next()) {

               String id = result.getString(1);
               String nom = result.getString(2);
               String tri = result.getString(3);
               String insc = result.getString(4);

               Classe obj = new Classe(id,nom,tri,insc);
               table.add(obj);

          }

        } catch (SQLException e) {
         System.out.println("pas arraylist");
        }
       return table;
  }
}

