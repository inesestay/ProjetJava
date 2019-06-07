/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlconnexion.DAO;

import static java.lang.Float.parseFloat;
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
public class EvaluationDAO extends DAO<Evaluation> {
    
public EvaluationDAO(Connection conn) {
    
    
    super(conn);
  }

  @Override
    public boolean create(Evaluation obj) {
         try {
             if(("".equals(obj.getAppreciation())) || ("".equals(obj.getNote())) || ("".equals(obj.getDetailBulletinID()))){
        
                throw new SQLException("il manque un ou plusieurs champs");
         }
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO evaluation(appreciation,note,detailBulletinID ) VALUES(?,?,?)");
            statement.setObject(1,obj.getAppreciation(),Types.VARCHAR); 
            statement.setObject(2,Integer.parseInt(obj.getNote()) ,Types.INTEGER); 
            statement.setObject(3,Integer.parseInt(obj.getDetailBulletinID()),Types.INTEGER); 
            
            statement.executeUpdate(); 
             System.out.println("Evaluation créée");
        } catch (SQLException ex) {
            System.out.println("pas create Evaluation");
            System.out.println(ex.getMessage());
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
    }

  public boolean delete(Evaluation obj) {
     
       String requete = "DELETE FROM evaluation WHERE";
      boolean virgule = false;
      
      if(!("".equals(obj.getAppreciation()))){
          requete += " `appreciation`= "+"'" +obj.getAppreciation()+"'" ;
          virgule=true;
          
           if(virgule==true ){
              if (!("".equals(obj.getNote())) || !("".equals(obj.getDetailBulletinID())) || !("".equals(obj.getId())) ) {
                  requete =requete + " AND" ;
              }
            }       
      }
            
      if(!("".equals(obj.getNote()))){
          requete += " `note` = "+ "'"+ obj.getNote()+ "'";
          virgule=true;
          
           if(virgule==true){
               if(!("".equals(obj.getDetailBulletinID())) || !("".equals(obj.getId()))){
          requete =requete + " AND" ;
               }
            } 
      }
      
      if(!("".equals(obj.getDetailBulletinID()))){
          requete += " `detailBulletin` = "+"'"+obj.getDetailBulletinID()+ "'";
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
             System.out.println("Evaluation supp");
        } catch (SQLException ex) {
            System.out.println("pas supp Evaluation");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public boolean update(Evaluation obj) {
      
      String requete = "UPDATE evaluation SET ";
      boolean virgule = false;
      
      if(!("".equals(obj.getAppreciation()))){
          requete += " appreciation= "+"'" +obj.getAppreciation()+"'" ;
          virgule=true;
          
           if(virgule==true ){
              if (!("".equals(obj.getNote())) || !("".equals(obj.getDetailBulletinID()))) {
                  requete =requete + "," ;
              }
            }       
      }
            
      if(!("".equals(obj.getNote()))){
          requete += " note = "+ "' "+ obj.getNote()+ "' ";
          virgule=true;
          
           if(virgule==true){
               if(!("".equals(obj.getDetailBulletinID()))){
          requete =requete + "," ;
               }
            } 
      }
      
      if(!("".equals(obj.getDetailBulletinID()))){
          requete += " detailBulletin = "+"'"+obj.getDetailBulletinID()+ "' " + " ";
       
      }     
      
      
      requete += "WHERE id = " + obj.getId()+"" ;
      
      System.out.println(requete);
      
  try{
            PreparedStatement statement = this.connect.prepareStatement(requete);
           
            statement.executeUpdate(); 
             System.out.println("Evaluation update");
        } catch (SQLException ex) {
            System.out.println("pas udpade Evaluation");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public Evaluation find(String id) {
    Evaluation d = new Evaluation();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM evaluation WHERE id = " + id);
      if(result.first())
         
        d = new Evaluation(result.getString("id"),result.getString("appreciation"), result.getString("note"), result.getString("detailBulletinID"));         
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
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM evaluation");
        //+nomTable+
           while(result.next()) {

               String id = result.getString(1);
               String nom = result.getString(2);
               String a = result.getString(3);
               String z = result.getString(4);

               Evaluation obj = new Evaluation(id,nom,a,z);
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

