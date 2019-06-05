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
public class EnseignementDAO extends DAO<Enseignement> {
    
public EnseignementDAO(Connection conn) {
    
    
    super(conn);
  }

  @Override
    public boolean create(Enseignement obj) {
         try {
             if(("".equals(obj.getClasseID())) || ("".equals(obj.getDisciplineID())) || ("".equals(obj.getEnseignantID()))){
        
                throw new SQLException("il manque un ou plusieurs champs");
         }
             
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO enseignement(classeID,disciplineId,personneId) VALUES(?,?,?)");
            statement.setObject(1,Integer.parseInt(obj.getClasseID()),Types.INTEGER); 
            statement.setObject(2,Integer.parseInt(obj.getDisciplineID()),Types.INTEGER); 
            statement.setObject(3,Integer.parseInt(obj.getEnseignantID()),Types.INTEGER); 
            
            statement.executeUpdate(); 
             System.out.println("Enseignement créé");
        } catch (SQLException ex) {
            System.out.println("pas create Enseignement");
            System.out.println(ex.getMessage());
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
    }

  public boolean delete(Enseignement obj) {
     
       String requete = "DELETE FROM enseignement WHERE";
      boolean virgule = false;
      
      if(!("".equals(obj.getClasseID()))){
          requete += " `classeID`= "+"'" +obj.getClasseID()+"'" ;
          virgule=true;
          
           if(virgule==true ){
              if (!("".equals(obj.getDisciplineID())) || !("".equals(obj.getEnseignantID())) || !("".equals(obj.getId())) ) {
                  requete =requete + " AND" ;
              }
            }       
      }
            
      if(!("".equals(obj.getDisciplineID()))){
          requete += " `disciplineId` = "+ "'"+ obj.getDisciplineID()+ "'";
          virgule=true;
          
           if(virgule==true){
               if(!("".equals(obj.getEnseignantID())) || !("".equals(obj.getId()))){
          requete =requete + " AND" ;
               }
            } 
      }
      
      if(!("".equals(obj.getEnseignantID()))){
          requete += " `personneId` = "+"'"+obj.getEnseignantID()+ "'";
          virgule=true;
          
           if(virgule==true){
               if(!("".equals(obj.getId()))){
          requete =requete + " AND" ;
               }
            }
       
      }     
      
      if(!("".equals(obj.getId()))){
          requete += " `id` = "+"'"+obj.getId()+ "' ";
      }
      
   try {
            PreparedStatement statement = this.connect.prepareStatement(requete);
           
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
      
      String requete = "UPDATE enseignement SET ";
      boolean virgule = false;
      
      if(!("".equals(obj.getClasseID()))){
          requete += " classeID= "+"'" +obj.getClasseID()+"'" ;
          virgule=true;
          
           if(virgule==true ){
              if (!("".equals(obj.getDisciplineID())) || !("".equals(obj.getEnseignantID()))) {
                  requete =requete + "," ;
              }
            }       
      }
            
      if(!("".equals(obj.getDisciplineID()))){
          requete += " disciplineId = "+ "' "+ obj.getDisciplineID()+ "' ";
          virgule=true;
          
           if(virgule==true){
               if(!("".equals(obj.getEnseignantID()))){
          requete =requete + "," ;
               }
            } 
      }
      
      if(!("".equals(obj.getEnseignantID()))){
          requete += " personneId = "+"'"+obj.getEnseignantID()+ "' " + " ";
       
      }     
      
      requete += " WHERE id = " + obj.getId()+"" ;
      
      System.out.println(requete);
      
  try{
            PreparedStatement statement = this.connect.prepareStatement(requete);
           
            statement.executeUpdate(); 
             System.out.println("Enseignement update");
        } catch (SQLException ex) {
            System.out.println("pas udpade Enseignement");
            System.out.println(ex.getMessage());
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public Enseignement find(String id) {
    Enseignement d = new Enseignement();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM enseignement WHERE id = " + id);
      if(result.first())
          
        d = new Enseignement(result.getString("id"),result.getString("classeID"), result.getString("enseignantID"), result.getString("discipID"));         
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
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM enseignement");
        //+nomTable+
           while(result.next()) {

               String id = result.getString(1);
               String a = result.getString(2);
               String z = result.getString(3);
               String e = result.getString(4);

               Enseignement obj = new Enseignement(id,a,z,e);
               table.add(obj);

          }
        

        } catch (SQLException e) {
         System.out.println("pas arraylist");
        }
       return table;
  }
}
