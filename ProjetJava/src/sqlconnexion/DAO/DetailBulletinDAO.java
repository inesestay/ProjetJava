/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlconnexion.DAO;

import static java.lang.Float.parseFloat;
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
public class DetailBulletinDAO extends DAO<DetailBulletin> {
    
public DetailBulletinDAO(Connection conn) {
    
    
    super(conn);
  }

  @Override
    public boolean create(DetailBulletin obj) {
        
         try {
             
             if(("".equals(obj.getAppreciation())) || ("".equals(obj.getBulletinID())) || ("".equals(obj.getEnseignementID()))){
        
                throw new SQLException("il manque un ou plusieurs champs");
         }
             
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO detailbulletin(appreciation,bulletinID,enseignementID) VALUES(?,?,?)");
            statement.setObject(1,obj.getAppreciation(),Types.VARCHAR);
            statement.setObject(2,Integer.parseInt(obj.getBulletinID()),Types.INTEGER);
            statement.setObject(3,Integer.parseInt(obj.getEnseignementID()),Types.INTEGER);
            
            statement.executeUpdate(); 
             System.out.println("bulletin créée");
        } catch (SQLException ex) {
            System.out.println("pas create bulletin");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
    }

  public boolean delete(DetailBulletin obj) {
     
        String requete = "DELETE FROM detailBulletin WHERE";
      boolean virgule = false;
      
      if(!("".equals(obj.getAppreciation()))){
          requete += " `appreciation`= "+"'" +obj.getAppreciation()+"'" ;
          virgule=true;
          
           if(virgule==true ){
              if (!("".equals(obj.getBulletinID())) || !("".equals(obj.getEnseignementID())) || !("".equals(obj.getId())) ) {
                  requete =requete + " AND" ;
              }
            }       
      }
            
      if(!("".equals(obj.getBulletinID()))){
          requete += " `bulletinID` = "+ "'"+ obj.getBulletinID()+ "'";
          virgule=true;
          
           if(virgule==true){
               if(!("".equals(obj.getEnseignementID())) || !("".equals(obj.getId()))){
          requete =requete + " AND" ;
               }
            } 
      }
      
      if(!("".equals(obj.getEnseignementID()))){
          requete += " `enseignementID` = "+"'"+obj.getEnseignementID()+ "'";
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
   
  public boolean update(DetailBulletin obj) {
      
       String requete = "UPDATE detailBulletin SET ";
      boolean virgule = false;
      
      if(!("".equals(obj.getAppreciation()))){
          requete += " appreciation= "+"'" +obj.getAppreciation()+"'" ;
          virgule=true;
          
           if(virgule==true ){
              if (!("".equals(obj.getBulletinID())) || !("".equals(obj.getEnseignementID()))) {
                  requete =requete + "," ;
              }
            }       
      }
            
      if(!("".equals(obj.getBulletinID()))){
          requete += " bulletinID = "+ "' "+ obj.getBulletinID()+ "' ";
          virgule=true;
          
           if(virgule==true){
               if(!("".equals(obj.getEnseignementID()))){
          requete =requete + "," ;
               }
            } 
      }
      
      if(!("".equals(obj.getEnseignementID()))){
          requete += " enseignementID = "+"'"+obj.getEnseignementID()+ "' " + " ";
       
      }     
      
      
      requete += "WHERE id = " + obj.getId()+"" ;
      
      System.out.println(requete);
      
  try{
            PreparedStatement statement = this.connect.prepareStatement(requete);
           
            statement.executeUpdate(); 
             System.out.println("detail update");
        } catch (SQLException ex) {
            System.out.println("pas udpade detail");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public DetailBulletin find(String id) {
    DetailBulletin d = new DetailBulletin();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM discipline WHERE id = " + id);
      if(result.first())
        d = new DetailBulletin(result.getString("id"),result.getString("nom"), result.getString("bulletinID"), result.getString("enseignementID"));         
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
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM detailbulletin");
        //+nomTable+
           while(result.next()) {

               String id = result.getString(1);
               String nom = result.getString(2);
               String tri = result.getString(3);
               String insc = result.getString(4);

               DetailBulletin obj = new DetailBulletin(id,nom,tri,insc);
               table.add(obj);

          }
        
        } catch (SQLException e) {
         System.out.println("pas arraylist");
        }
       return table;
  }
    public float moyenne(Personne eleve)
    {
        float moyenne=0;
        float somme=0;
        ArrayList<Float> notes = new ArrayList<>();
        String id_eleve = eleve.getId();
      
        try {
        ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT note FROM `personne`,`inscription`,`bulletin`,`detailbulletin`,`evaluation` WHERE `"+id_eleve+"`LIKE `inscription`.`personneID` AND `inscription`.`id` LIKE `bulletin`.`inscriptionID` AND `detailbulletin`.`bulletinID` LIKE `bulletin`.`id` AND `detailbulletin`.`id` LIKE `evaluation`.`detailBulletinID`   ");
           while(result.next()) {

               String note = result.getString(1);
               float note2 = parseFloat(note);
               notes.add(note2);
          }
        
        } catch (SQLException e) {
         System.out.println("pas moyenne");
        }
        for(int i=0; i<notes.size(); i++)
        {
            somme = somme+notes.get(i);
        }
        return moyenne/notes.size();
    }
}
