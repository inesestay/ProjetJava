/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlconnexion.DAO;

/**
 *
 * @author inese
 */

import static java.lang.Float.parseFloat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import sqlconnexion.Model.Inscription;
import sqlconnexion.Model.Personne;

public class InscriDAO extends DAO<Inscription> {
    
public InscriDAO(Connection conn) {
    
    
    super(conn);
  }

  @Override
    public boolean create(Inscription obj){

   
    try{
        
        if(("".equals(obj.getClassID())) || ("".equals(obj.getPersonneID()))){
        
                throw new SQLException("il manque un ou plusieurs champs");
         }
        
        PreparedStatement statement = this.connect.prepareStatement("INSERT INTO inscription(classID,personneID) VALUES(?,?)");

        statement.setObject(1,obj.getClassID(),Types.INTEGER); 
        statement.setObject(2,obj.getPersonneID(),Types.INTEGER); 
        statement.executeUpdate();
        System.out.println("inscription créée");      
    }catch (SQLException ex) {
        System.out.println("pas create : " + ex.getMessage());
        return false;
    }

    return true;
}

@Override
  public boolean delete(Inscription obj) {

      String requete = "DELETE FROM inscription WHERE";
      boolean virgule = false;
      
      if(!("".equals(obj.getClassID()))){
          requete += " `classeID`= "+"'" +obj.getClassID()+"'" ;
          virgule=true;
          
           if(virgule==true ){
              if (!("".equals(obj.getPersonneID())) || !("".equals(obj.getId()))) {
                  requete =requete + " AND" ;
              }
            }       
      }
            
      if(!("".equals(obj.getPersonneID()))){
          requete += " `personneID` = "+ "'"+ obj.getPersonneID()+ "'";
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
             System.out.println("inscription supp");
        } catch (SQLException ex) {
            System.out.println("pas supp");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
@Override
  public boolean update(Inscription obj) {
      
     String requete = "UPDATE inscription SET ";
      boolean virgule = false;
      
      if(!("".equals(obj.getClassID()))){
          requete += " classID= "+"'" +obj.getClassID()+"'" ;
          virgule=true;
          
           if(virgule==true ){
              if (!("".equals(obj.getPersonneID()))) {
                  requete =requete + "," ;
              }
            }       
      }

      if(!("".equals(obj.getPersonneID()))){
          requete += " personneID = "+"'"+obj.getPersonneID()+ "' " + " ";
       
      }     
      
      requete += "WHERE id = " + obj.getId()+"" ;
      System.out.println(requete);
     
     try {

            PreparedStatement statement = this.connect.prepareStatement(requete);

            statement.executeUpdate(); 
             System.out.println("inscription update");
        } catch (SQLException ex) {
            System.out.println("pas udpade");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
@Override
  public Inscription find(String id) {
    Inscription inscription = new Inscription();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM personne WHERE id = " + id);
      if(result.first())
        inscription = new Inscription(result.getString("id"),result.getString("classID"),result.getString("personneID"));         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return inscription;
  }
  
   public ArrayList<Object> retour()
  {
       ArrayList<Object> table = new ArrayList();
       
       try {
        ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM inscription");
        //+nomTable+
           while(result.next()) {

               String id = result.getString(1);
               String a = result.getString(2);
               String z = result.getString(3);
               
               Inscription obj = new Inscription(id,a,z);
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


