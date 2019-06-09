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
import sqlconnexion.factory.DAOFactory;
/**
 *
 * @author nelly
 */
public class DetailBulletinDAO extends DAO<DetailBulletin> {
    
public DetailBulletinDAO(Connection conn) {
    
    
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

    /**
     * supprime un element
     * possibilité de supprimer avec un juste un certain nombre de champs remplis
     * si pas supprimé exception
     * @param obj
     * @return true si objet supprimé, sinon false
     */
  public boolean delete(DetailBulletin obj) {
     
      try {
            ArrayList<Integer> ines = find(obj);
        for(int nelly : ines){
            //Suppression suplémentaire
            DAO<Evaluation>adrien = DAOFactory.getEvaluationDAO();
            adrien.delete(new Evaluation("", "", "" ,Integer.toString(nelly)));
            //Suppression dans la table
            String requete = "DELETE FROM detailBulletin WHERE  `id` =" + nelly;
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
  
  /**
   * recherche d'un element dans la bdd
   * @param id
   * @return detail bulletin recherché
   * sinon lance exception
   */
   
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
  
  
  /**
   * récupérer tous les details bulletins
   * @return ArrayList<Object> de details bulletins
   * 
   */
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

    @Override
    public ArrayList<Integer> find(DetailBulletin inscriATrouver) {
        ArrayList<Integer> aRetourner = new ArrayList<Integer>();
      
        String requete = "SELECT * FROM detailbulletin WHERE";
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


        if(!("".equals(inscriATrouver.getBulletinID()))){
            if(virgule){
                requete += " AND";
            }
            requete += " `bulletinID` = '"+ inscriATrouver.getBulletinID()+ "'";
            virgule=true;
        }

        if(!("".equals(inscriATrouver.getEnseignementID()))){
            if(virgule){
                requete += " AND";
            }
            requete += " `enseignementID` = '"+ inscriATrouver.getEnseignementID()+ "'";
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
              System.out.println("Requette echouer");
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
    
        public  ArrayList<ArrayList<String>> evaluation(String id, String discipline)
   {
        
        ArrayList<String> eval = new ArrayList<>();
        ArrayList<ArrayList<String>> tab_eval = new ArrayList<>();
      
      
        try {
        ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT DISTINCT evaluation.note, evaluation.appreciation FROM personne,inscription,bulletin,detailbulletin,evaluation,discipline,enseignement WHERE discipline.nom LIKE '"+discipline+"' AND inscription.personneID LIKE "+id+" AND inscription.id LIKE bulletin.inscriptionID AND detailbulletin.bulletinID LIKE bulletin.id AND detailbulletin.id LIKE evaluation.detailBulletinID AND detailbulletin.enseignementID LIKE enseignement.id AND enseignement.disciplineId LIKE discipline.id AND evaluation.detailBulletinID LIKE detailbulletin.id AND detailbulletin.enseignementID LIKE enseignement.id AND enseignement.disciplineId LIKE discipline.id");
        
        while(result.next()) {
       
            eval.clear();
               String note = result.getString(1);
               String appreciation = result.getString(2);
               eval.add(note);
               eval.add(appreciation);
               tab_eval.add(eval);
                System.out.println("apres ajout tab");
               
                System.out.println("apres clear");
          }
        
        } catch (SQLException ex) {
         System.out.println("pas evaluation");
         System.out.println(ex.getMessage());
        }
        
        //affichage
         System.out.println("tab siez"+ tab_eval.size());
         System.out.println("eval size"+ eval.size());
        for(int i =0; i<tab_eval.size();i++)
        {
            System.out.println( "evaluation n°"+i + ": (note et appréciation :) ");
            for(int j=0; j<tab_eval.get(i).size(); j++)
            {
             System.out.println( tab_eval.get(i).get(j));
            }
        }
        
       
        return tab_eval;
   }
}
