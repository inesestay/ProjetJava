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
             
             if(("".equals(obj.getNom()))){
        
                throw new SQLException("il manque un ou plusieurs champs");
         }
             
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO discipline(nom) VALUES(?)");
            statement.setObject(1,obj.getNom(),Types.VARCHAR); 
            
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
     
       String requete = "DELETE FROM discipline WHERE";
      boolean virgule = false;
      
      if(!("".equals(obj.getNom()))){
          requete += " `nom`= "+"'" +obj.getNom()+"'" ;
          virgule=true;
          
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
             System.out.println("discipline supp");
        } catch (SQLException ex) {
            System.out.println("pas supp discipline");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public boolean update(Discipline obj) {
      
       String requete = "UPDATE discipline SET ";
      boolean virgule = false;
      
      if(!("".equals(obj.getNom()))){
          requete += " nom = "+"'"+obj.getNom()+ "' " + " ";
       
      }     
      
      
      requete += "WHERE id = " + obj.getId()+"" ;
      
      System.out.println(requete);
                 
            
  try{
            PreparedStatement statement = this.connect.prepareStatement(requete);
           
            statement.executeUpdate(); 
             System.out.println("discipline update");
        } catch (SQLException ex) {
            System.out.println("pas udpade discipline");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public Discipline find(String id) {
    Discipline d = new Discipline();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM discipline WHERE id = " + id);
      if(result.first())
        d = new Discipline(result.getString("id"),result.getString("nom"));         
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

               String id = result.getString(1);
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
