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
public class TrimestreDAO extends DAO<Trimestre> {
    
public TrimestreDAO(Connection conn) {
    
    
    super(conn);
  }

  @Override
    public boolean create( Trimestre obj) {
         try {
             if(("".equals(obj.getNum())) || ("".equals(obj.getDebut())) || ("".equals(obj.getFin())) || ("".equals(obj.getAnneescolaireID()))){
        
                throw new SQLException("il manque un ou plusieurs champs");
         }
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO  trimestre(numero,debut,fin,anneescolaireID) VALUES(?,?,?,?)");
           
            statement.setObject(1,Integer.parseInt(obj.getNum()),Types.INTEGER); 
            statement.setObject(2,Integer.parseInt(obj.getDebut()),Types.INTEGER); 
            statement.setObject(3,Integer.parseInt(obj.getFin()),Types.INTEGER); 
            statement.setObject(4,Integer.parseInt(obj.getAnneescolaireID()),Types.INTEGER);   
            statement.executeUpdate(); 
             System.out.println(" Trimestre créée");
        } catch (SQLException ex) {
            System.out.println("pas create  Trimestre");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
    }

  public boolean delete( Trimestre obj) {
     String requete = "DELETE FROM trimestre WHERE";
      boolean virgule = false;
      
      if(!("".equals(obj.getNum()))){
          requete += " `numero`= "+"'" +obj.getNum()+"'" ;
          virgule=true;
          
           if(virgule==true ){
              if (!("".equals(obj.getDebut())) || !("".equals(obj.getFin())) || !("".equals(obj.getAnneescolaireID())) ||  !("".equals(obj.getId())) ) {
                  requete =requete + " AND" ;
              }
            }       
      }
            
      if(!("".equals(obj.getDebut()))){
          requete += " `debut` = "+ "'"+ obj.getDebut()+ "'";
          virgule=true;
          
           if(virgule==true){
               if(!("".equals(obj.getFin())) || !("".equals(obj.getAnneescolaireID())) ||  !("".equals(obj.getId()))){
          requete =requete + " AND" ;
               }
            } 
      }
      
      if(!("".equals(obj.getFin()))){
          requete += " `fin` = "+"'"+obj.getFin()+ "'";
          virgule=true;
          
           if(virgule==true){
               if(!("".equals(obj.getAnneescolaireID())) ||  !("".equals(obj.getId()))){
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
             System.out.println(" Trimestre supp");
        } catch (SQLException ex) {
            System.out.println("pas supp  Trimestre");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public boolean update( Trimestre obj) {
      
      String requete = "UPDATE trimestre SET ";
      boolean virgule = false;
      
      if(!("".equals(obj.getNum()))){
          requete += " numero= "+"'" +obj.getNum()+"'" ;
          virgule=true;
          
           if(virgule==true ){
              if (!("".equals(obj.getDebut())) || !("".equals(obj.getFin())) || !("".equals(obj.getAnneescolaireID()))) {
                  requete =requete + "," ;
              }
            }       
      }
            
      if(!("".equals(obj.getDebut()))){
          requete += " debut = "+ "' "+ obj.getDebut()+ "' ";
          virgule=true;
          
           if(virgule==true){
               if(!("".equals(obj.getFin())) || !("".equals(obj.getAnneescolaireID()))){
          requete =requete + "," ;
               }
            } 
      }
      
      if(!("".equals(obj.getFin()))){
          requete += " fin = "+"'"+obj.getFin()+ "' " + " ";
          
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
             System.out.println(" Trimestre update");
        } catch (SQLException ex) {
            System.out.println("pas udpade  Trimestre");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public  Trimestre find(String id) {
     Trimestre d = new  Trimestre();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM  trimestre WHERE id = " + id);
      if(result.first())
        d = new  Trimestre(result.getString("id"),result.getString("num"), result.getString("debut"), result.getString("fin"), result.getString("anneescolaireID"));         
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
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM trimestre");
        //+nomTable+
           while(result.next()) {

               String id = result.getString(1);
               String a = result.getString(2);
               String z = result.getString(3);
               String e = result.getString(4);
               String r = result.getString(5);

               Trimestre obj = new Trimestre(id,a,z,e,r);
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

