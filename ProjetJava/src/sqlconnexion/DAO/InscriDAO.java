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
import sqlconnexion.Model.Bulletin;
import sqlconnexion.Model.Inscription;
import sqlconnexion.Model.Personne;
import sqlconnexion.factory.DAOFactory;

public class InscriDAO extends DAO<Inscription> {
    
public InscriDAO(Connection conn) {
    
    
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

    /**
     * supprime un element
     * possibilité de supprimer avec un juste un certain nombre de champs remplis
     * si pas supprimé exception
     * @param obj
     * @return true si objet supprimé, sinon false
     */
@Override
  public boolean delete(Inscription obj) {
      
    try {
            ArrayList<Integer> ines = find(obj);
        for(int nelly : ines){
            //Suppression dans bulleltin
            DAO<Bulletin>adrien = DAOFactory.getBulletinDAO();
            adrien.delete(new Bulletin("", "", "", Integer.toString(nelly)));

            //Suppression dans la table
            String requete = "DELETE FROM inscription WHERE  `id` =" + nelly;
            PreparedStatement statement = this.connect.prepareStatement(requete);
            statement.executeUpdate(); 
            System.out.println("inscription supp");
        }
            
            
    } catch (SQLException ex) {
        System.out.println("pas supp");
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
   
  
  /**
   * recherche d'un element dans la bdd
   * @param id
   * @return l'insciption recherchée
   * sinon lance exception
   */
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
  
  /**
   * récupérer toutes les id des inscription avec des attribut qui correspond
   * @return ArrayList<Integer> des id d inscription 
   * 
   */
  public ArrayList<Integer> find(Inscription inscriATrouver){
      ArrayList<Integer> aRetourner = new ArrayList<Integer>();
      
      String requete = "SELECT * FROM inscription WHERE";
      boolean virgule = false;
      
      if(!("".equals(inscriATrouver.getId()))){
          requete += " `id`= "+"'" +inscriATrouver.getId() +"'" ;
          virgule=true;      
      }
            
      if(!("".equals(inscriATrouver.getClassID()))){         
          if(virgule){
              requete += " AND";
          }
          
          requete += " `classID` = '"+ inscriATrouver.getClassID()+ "'";
          
          virgule=true;
      }
      
      
      if(!("".equals(inscriATrouver.getPersonneID()))){
                    if(virgule){
              requete += " AND";
          }
          requete += " `personneID` = '"+ inscriATrouver.getPersonneID()+ "'";
      }
      
   try {
            ResultSet result = this.connect.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
            
            while(result.next()) {
               aRetourner.add(result.getInt(1));
          }
            
        } catch (SQLException ex) {
            System.out.println("Requette echouer");
        }
      
      return aRetourner;
  }
  
  /**
   * récupérer toutes les inscriptions
   * @return ArrayList<Object> d'inscriptions
   * 
   */
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


