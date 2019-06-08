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
public class AnneeScolaireDAO extends DAO<AnneeScolaire>{
    
    public AnneeScolaireDAO(Connection conn) {
    
    
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
    public boolean create(AnneeScolaire obj) {
         try {
             
              if(("".equals(obj.getAnneeScolaireID()))){
        
                throw new SQLException("il manque un ou plusieurs champs");
         }
             System.out.println(obj.getAnneeScolaireID());
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO anneescolaire(AnneeScolaireID) VALUES(?)");
            statement.setObject(1,Integer.parseInt(obj.getAnneeScolaireID()),Types.INTEGER); 
             statement.executeUpdate(); 
            System.out.println("annee créée");
        } catch (SQLException ex) {
            System.out.println("pas create : " + ex.getMessage());
            System.out.println(ex.getMessage());
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
    }

    /**
     * supprime un element
     * si pas supprimé exception
     * @param obj
     * @return true si objet supprimé, sinon false
     */
  public boolean delete(AnneeScolaire obj) {
      
    try {
            ArrayList<Integer> ines = find(obj);
        for(int nelly : ines){
            //Suppression suplémentaire
            
            //Suppression dans la table
            String requete = "DELETE FROM anneescolaire WHERE  `AnneeScolaireID` =" + nelly;
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
  public boolean update(AnneeScolaire obj) {
      
      String requete = "UPDATE personne SET ";
      boolean virgule = false;
      
      if(!("".equals(obj.getAnneeScolaireID()))){
          requete += " AnneeScolaireID= "+"'" +obj.getAnneeScolaireID()+"'" ;
              
      }
      
      requete += "WHERE AnneeScolaireID = " + obj.getAnneeScolaireID()+"" ;
      
      System.out.println(requete);
     
     try {
            PreparedStatement statement = this.connect.prepareStatement(requete);
           
            statement.executeUpdate(); 
             System.out.println("annee update");
        } catch (SQLException ex) {
            System.out.println("annee pas udpade");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  
  /**
   * recherche d'un element dans la bdd
   * @param id
   * @return l'année soclaire recherchée
   * sinon lance exception
   */
  public AnneeScolaire find(String id) {
    AnneeScolaire a = new AnneeScolaire();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM anneescolaire WHERE id = " + id);
      if(result.first())
        a = new AnneeScolaire(id);         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return a;
  }
  
  
  
  
  /**
   * récupérer tous les années scolaires
   * @return ArrayList<Object> d'annnées scolaires
   * 
   */
   public ArrayList<Object> retour()
  {
       ArrayList<Object> table = new ArrayList();
       
       try {
        ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM anneescolaire");
        //+nomTable+
  
           while(result.next()) {

               String id = result.getString(1);
               AnneeScolaire obj = new AnneeScolaire(id);
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
    public ArrayList<Integer> find(AnneeScolaire inscriATrouver) {
        ArrayList<Integer> aRetourner = new ArrayList<Integer>();
      
      String requete = "SELECT * FROM anneescolaire WHERE";
      
      if(!("".equals(inscriATrouver.getAnneeScolaireID()))){
          requete += " `AnneeScolaireID`= "+"'" +inscriATrouver.getAnneeScolaireID() +"'" ;    
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
}
