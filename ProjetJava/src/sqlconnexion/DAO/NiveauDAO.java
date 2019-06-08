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
public class NiveauDAO extends DAO<Niveau> {
    
public NiveauDAO(Connection conn) {
    
    super(conn);
  }

/**
 * creer un objet dans la bdd
 * @param obj
 * @return true si l'objet est créé dans la bdd
 * blindage : si un champ est vide, l'objet ne peut etre créé
 * si aucun champ vide, on ajoute a la bdd
 * sinon exception créée
 */
  @Override
    public boolean create(Niveau obj) {
         try {
             if(("".equals(obj.getNom()))){
        
                throw new SQLException("il manque un ou plusieurs champs");
         }
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO niveau(nom) VALUES(?)");
            statement.setObject(1,obj.getNom(),Types.VARCHAR);
            statement.executeUpdate(); 
             System.out.println("Niveau créée");
        } catch (SQLException ex) {
            System.out.println("pas create Niveau");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
    }

  public boolean delete(Niveau obj) {
     
       String requete = "DELETE FROM niveau WHERE";
      boolean virgule = false;
      
      if(!("".equals(obj.getNom()))){
          requete += " `nom`= "+"'" +obj.getNom()+"'" ;
          virgule=true;
            System.out.println(obj.getNom());
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
             System.out.println("Niveau supp");
        } catch (SQLException ex) {
            System.out.println("pas supp Niveau");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
  
  /**
   * modifier un élélement de la bdd
   * possibilité de modifier n'importe quel champs
   *
   * @param obj
   * @return true si l'objet est modifié, sinon false
   */
   
  public boolean update(Niveau obj) {
      
       String requete = "UPDATE niveau SET ";
      boolean virgule = false;
      
      if(!("".equals(obj.getNom()))){
          requete += " nom = "+"'"+obj.getNom()+ "' " + " ";
       
      }     
            
      requete += "WHERE id = " + obj.getId()+"" ;
      
      System.out.println(requete);
      
  try{
            PreparedStatement statement = this.connect.prepareStatement(requete);
           
            statement.executeUpdate(); 
             System.out.println("Niveau update");
        } catch (SQLException ex) {
            System.out.println("pas udpade Niveau");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  
  /**
   * recherche d'un element dans la bdd
   * @param id
   * @return le niveau recherché
   * sinon lance exception
   */
  public Niveau find(String id) {
    Niveau d = new Niveau();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM niveau WHERE id = " + id);
      if(result.first())
        d = new Niveau(result.getString("id"),result.getString("nom"));         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return d;
  }
  
  
  /**
   * récupérer tous les niveaux
   * @return ArrayList<Object> de niveaux
   * 
   */
   public ArrayList<Object> retour()
  {
       ArrayList<Object> table = new ArrayList();
       
       try {
        ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM niveau");
        //+nomTable+
           while(result.next()) {

               String id = result.getString(1);
               String nom = result.getString(2);
              
               Niveau obj = new Niveau(id,nom);
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
