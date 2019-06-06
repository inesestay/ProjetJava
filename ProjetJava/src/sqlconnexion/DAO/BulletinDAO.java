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
public class BulletinDAO extends DAO<Bulletin> {
    public BulletinDAO(Connection conn) {
    
    
    super(conn);
  }

  @Override
    public boolean create(Bulletin obj) {
         try {
             
             if(("".equals(obj.getAppreciation())) || ("".equals(obj.getTrimestreID())) || ("".equals(obj.getInscriptionID()))){
        
                throw new SQLException("il manque un ou plusieurs champs");
         }
             
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO bulletin(appreciation,trimestreID,inscriptionID) VALUES(?,?,?)"
                    );
            statement.setObject(1,obj.getAppreciation(),Types.VARCHAR);
            statement.setObject(2,Integer.parseInt(obj.getTrimestreID()),Types.INTEGER);
            statement.setObject(3,Integer.parseInt(obj.getInscriptionID()),Types.INTEGER);
            
            statement.executeUpdate(); 
             System.out.println(" bulletin créée");
        } catch (SQLException ex) {
            System.out.println("pas create bulletin");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
    }

  public boolean delete(Bulletin obj) {
     
       String requete = "DELETE FROM bulletin WHERE";
      boolean virgule = false;
      
      if(!("".equals(obj.getAppreciation()))){
          requete += " `appreciation`= "+"'" +obj.getAppreciation()+"'" ;
          virgule=true;
          
           if(virgule==true ){
              if (!("".equals(obj.getTrimestreID())) || !("".equals(obj.getInscriptionID())) || !("".equals(obj.getId())) ) {
                  requete =requete + " AND" ;
              }
            }       
      }
            
      if(!("".equals(obj.getTrimestreID()))){
          requete += " `trimestreID` = "+ "'"+ obj.getTrimestreID()+ "'";
          virgule=true;
          
           if(virgule==true){
               if(!("".equals(obj.getInscriptionID())) || !("".equals(obj.getId()))){
          requete =requete + " AND" ;
               }
            } 
      }
      
      if(!("".equals(obj.getInscriptionID()))){
          requete += " `inscriptionID` = "+"'"+obj.getInscriptionID()+ "'";
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
             System.out.println("bulletin supp");
        } catch (SQLException ex) {
            System.out.println("pas supp bulletin");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public boolean update(Bulletin obj) {
      
      String requete = "UPDATE bulletin SET ";
      boolean virgule = false;
      
      if(!("".equals(obj.getAppreciation()))){
          requete += " appreciation= "+"'" +obj.getAppreciation()+"'" ;
          virgule=true;
          
           if(virgule==true ){
              if (!("".equals(obj.getTrimestreID())) || !("".equals(obj.getInscriptionID()))) {
                  requete =requete + "," ;
              }
            }       
      }
            
      if(!("".equals(obj.getTrimestreID()))){
          requete += " trimestreID = "+ "' "+ obj.getTrimestreID()+ "' ";
          virgule=true;
          
           if(virgule==true){
               if(!("".equals(obj.getInscriptionID()))){
          requete =requete + "," ;
               }
            } 
      }
      
      if(!("".equals(obj.getInscriptionID()))){
          requete += " inscriptionID = "+"'"+obj.getInscriptionID()+ "' " + " ";
       
      }     
            
      requete += "WHERE id = " + obj.getId()+"" ;
      
      System.out.println(requete);
                 
      
        try{
            PreparedStatement statement = this.connect.prepareStatement(requete);
           
            statement.executeUpdate(); 
             System.out.println("bulle update");
        } catch (SQLException ex) {
            System.out.println("pas udpade bulle");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public Bulletin find(String id) {
    Bulletin d = new Bulletin();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM bulletin WHERE id = " + id);
      if(result.first())
        d = new Bulletin(id,result.getString("appreciation"),result.getString("trimestreID"),result.getString("inscriptionID") );         
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
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM bulletin");
        //+nomTable+
           while(result.next()) {

               String id = result.getString(1);
               String nom = result.getString(2);
               String tri = result.getString(3);
               String insc = result.getString(4);
           
               Bulletin obj = new Bulletin(id,nom,tri,insc);
               table.add(obj);

          }
        

        } catch (SQLException e) {
         System.out.println("pas arraylist");
        }
       return table;
  }
}
