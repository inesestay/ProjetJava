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
             
             if(("".equals(obj.getNom())) || ("".equals(obj.getNiveauID())) || ("".equals(obj.getAnneescolaireID()))){
        
                throw new SQLException("il manque un ou plusieurs champs");
         }
             
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO classe(nom,niveauID,anneescolaireID) VALUES(?,?,?)"
                    );
            statement.setObject(1,obj.getNom(),Types.VARCHAR); 
            statement.setObject(2,Integer.parseInt(obj.getNiveauID()),Types.INTEGER);
            statement.setObject(3,Integer.parseInt(obj.getAnneescolaireID()),Types.INTEGER);
            
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
     
      String requete = "DELETE FROM classe WHERE";
      boolean virgule = false;
      
      if(!("".equals(obj.getNom()))){
          requete += " `nom`= "+"'" +obj.getNom()+"'" ;
          virgule=true;
          
           if(virgule==true ){
              if (!("".equals(obj.getNiveauID())) || !("".equals(obj.getAnneescolaireID())) || !("".equals(obj.getId())) ) {
                  requete =requete + " AND" ;
              }
            }       
      }
            
      if(!("".equals(obj.getNiveauID()))){
          requete += " `niveauID` = "+ "'"+ obj.getNiveauID()+ "'";
          virgule=true;
          
           if(virgule==true){
               if(!("".equals(obj.getAnneescolaireID())) || !("".equals(obj.getId()))){
          requete =requete + " AND" ;
               }
            } 
      }
      
      if(!("".equals(obj.getAnneescolaireID()))){
          requete += " `anneescolaireID` = "+"'"+obj.getAnneescolaireID()+ "'";
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
      
       System.out.println(requete);
  
   try {
            PreparedStatement statement = this.connect.prepareStatement(requete);
           
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
      
       String requete = "UPDATE classe SET ";
      boolean virgule = false;
      
      if(!("".equals(obj.getNom()))){
          requete += " nom= "+"'" +obj.getNom()+"'" ;
          virgule=true;
          
           if(virgule==true ){
              if (!("".equals(obj.getNiveauID())) || !("".equals(obj.getAnneescolaireID()))) {
                  requete =requete + "," ;
              }
            }       
      }
            
      if(!("".equals(obj.getNiveauID()))){
          requete += " niveauID = "+ "' "+ obj.getNiveauID()+ "' ";
          virgule=true;
          
           if(virgule==true){
               if(!("".equals(obj.getAnneescolaireID()))){
          requete =requete + "," ;
               }
            } 
      }
      
      if(!("".equals(obj.getAnneescolaireID()))){
          requete += " anneescolaireID = "+"'"+obj.getAnneescolaireID()+ "' " + " ";
       
      }     
      
      
      requete += "WHERE id = " + obj.getId()+"" ;
      
      System.out.println(requete);
                 
      
  try{
            PreparedStatement statement = this.connect.prepareStatement(requete);
           
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

