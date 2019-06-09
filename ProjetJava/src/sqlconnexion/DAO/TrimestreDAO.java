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
import sqlconnexion.factory.DAOFactory;

/**
 *
 * @author nelly
 */
public class TrimestreDAO extends DAO<Trimestre> {
    
public TrimestreDAO(Connection conn) {
    
    
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

    /**
     * supprime un element
     * possibilité de supprimer avec un juste un certain nombre de champs remplis
     * si pas supprimé exception
     * @param obj
     * @return true si objet supprimé, sinon false
     */
  public boolean delete( Trimestre obj) {
      try {
            ArrayList<Integer> ines = find(obj);
        for(int nelly : ines){
            //Suppression suplémentaire
            //Suppresion dans bulletin
            DAO<Bulletin>adrien = DAOFactory.getBulletinDAO();
            adrien.delete(new Bulletin("", "", Integer.toString(nelly), ""));
            
            //Suppression dans la table
            String requete = "DELETE FROM trimestre WHERE  `id` =" + nelly;
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
   
  /**
   * recherche d'un element dans la bdd
   * @param id
   * @return le trimestre recherché
   * sinon lance exception
   */
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
  
  
  /**
   * récupérer tous lestrimestres
   * @return ArrayList<Object> de trimestres
   * 
   */
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

    @Override
    public ArrayList<Integer> find(Trimestre inscriATrouver) {
        ArrayList<Integer> aRetourner = new ArrayList<Integer>();
      
        String requete = "SELECT * FROM trimestre WHERE";
        boolean virgule = false;

        if(!("".equals(inscriATrouver.getId()))){
            requete += " `id`= "+"'" +inscriATrouver.getId() +"'" ;
            virgule=true;      
        }

        if(!("".equals(inscriATrouver.getNum()))){         
            if(virgule){
                requete += " AND";
            }
            requete += " `numero` = '"+ inscriATrouver.getNum()+ "'";
            virgule=true;
        }


        if(!("".equals(inscriATrouver.getDebut()))){
            if(virgule){
                requete += " AND";
            }
            requete += " `debut` = '"+ inscriATrouver.getDebut()+ "'";
            virgule=true;
        }

        if(!("".equals(inscriATrouver.getFin()))){
            if(virgule){
                requete += " AND";
            }
            requete += " `fin` = '"+ inscriATrouver.getFin()+ "'";
            virgule=true;
        }
        
        if(!("".equals(inscriATrouver.getAnneescolaireID()))){
            if(virgule){
                requete += " AND";
            }
            requete += " `anneescolaireID` = '"+ inscriATrouver.getAnneescolaireID()+ "'";
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

