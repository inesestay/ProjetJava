
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


/**
 * creer un objet dans la bdd
 * @param obj
 * @return true si l'objet est créé dans la bdd
 * blindage : si un champ est vide, l'objet ne peut etre créé
 * si aucun champ vide, on ajoute a la bdd
 * sinon exception créée
 */
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

    /**
     * supprime un element
     * possibilité de supprimer avec un juste un certain nombre de champs remplis
     * si pas supprimé exception
     * @param obj
     * @return true si objet supprimé, sinon false
     */
  public boolean delete(Evaluation obj) {
     
      try {
            ArrayList<Integer> ines = find(obj);
        for(int nelly : ines){
            //Suppression suplémentaire
            
            //Suppression dans la table
            String requete = "DELETE FROM evaluation WHERE  `id` =" + nelly;
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
   
  /**
   * recherche d'un element dans la bdd
   * @param id
   * @return l'evaluation  recherchée
   * sinon lance exception
   */
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
  
  
  /**
   * récupérer tous les evaluations
   * @return ArrayList<Object> d'evaluations
   * 
   */
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

    @Override
    public ArrayList<Integer> find(Evaluation inscriATrouver) {
        ArrayList<Integer> aRetourner = new ArrayList<Integer>();
      
        String requete = "SELECT * FROM evaluation WHERE";
        boolean virgule = false;

        if(!("".equals(inscriATrouver.getId()))){
            requete += " `id`= "+"'" +inscriATrouver.getId() +"'" ;
            virgule=true;      
        }

        if(!("".equals(inscriATrouver.getAppreciation()))){         
            if(virgule){
                requete += " AND";
            }
            requete += " `appreciation` = '"+ inscriATrouver.getAppreciation()+ "'";
            virgule=true;
        }


        if(!("".equals(inscriATrouver.getDetailBulletinID()))){
            if(virgule){
                requete += " AND";
            }
            requete += " `detailBulletinfID` = '"+ inscriATrouver.getDetailBulletinID()+ "'";
            virgule=true;
        }

        if(!("".equals(inscriATrouver.getNote()))){
            if(virgule){
                requete += " AND";
            }
            requete += " `note` = '"+ inscriATrouver.getNote()+ "'";
            virgule=true;
        }

        try {
            ResultSet result = this.connect.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY).executeQuery(requete);

            while(result.next()) {
                aRetourner.add(result.getInt(1));
            }

          } catch (SQLException ex) {
              System.out.println("Requette echouée");
          }

        return aRetourner;
    }
    
    public float moyenneMatiere(String id, String discipline)
   {
         float somme=0;
        ArrayList<Float> notes = new ArrayList<>();
      
        try {
        ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT DISTINCT note FROM personne,inscription,bulletin,detailbulletin,evaluation,discipline,enseignement WHERE discipline.nom LIKE '"+discipline+"' AND inscription.personneID LIKE "+id+" AND inscription.id LIKE bulletin.inscriptionID AND detailbulletin.bulletinID LIKE bulletin.id AND detailbulletin.id LIKE evaluation.detailBulletinID AND detailbulletin.enseignementID LIKE enseignement.id AND enseignement.disciplineId LIKE discipline.id AND evaluation.detailBulletinID LIKE detailbulletin.id AND detailbulletin.enseignementID LIKE enseignement.id AND enseignement.disciplineId LIKE discipline.id");
        
        while(result.next()) {
       
               String note = result.getString(1);
               float note2 = parseFloat(note);
               notes.add(note2);
          }
        
        } catch (SQLException e) {
         System.out.println("pas moyenne");
         System.out.println(e.getMessage());
        }
            
       
        return somme/notes.size();
   }
}

