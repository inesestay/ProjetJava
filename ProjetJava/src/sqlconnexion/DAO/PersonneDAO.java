/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlconnexion.DAO;
import static java.lang.Float.parseFloat;
import sqlconnexion.Model.Personne;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import sqlconnexion.Model.Discipline;
import sqlconnexion.Model.Enseignement;
import sqlconnexion.Model.Inscription;
import sqlconnexion.factory.DAOFactory;


/**
 *
 * @author nelly
 */
public class PersonneDAO extends DAO<Personne> {
    
public PersonneDAO(Connection conn) {
    
    
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
    public boolean create(Personne obj){
    try{
        
        if(("".equals(obj.getNom())) || ("".equals(obj.getPrenom())) || ("".equals(obj.getType()))){
        
                throw new SQLException("il manque un ou plusieurs champs");
         }
        PreparedStatement statement = this.connect.prepareStatement("INSERT INTO personne(nom,prenom,type) VALUES(?,?,?)");
        statement.setObject(1,obj.getNom(),Types.VARCHAR);
        statement.setObject(2,obj.getPrenom(),Types.VARCHAR); 
        statement.setObject(3,obj.getType(),Types.VARCHAR); 
        statement.executeUpdate();
        System.out.println("personne créée");      
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
  public boolean delete(Personne obj) {
           
      try {
            ArrayList<Integer> ines = find(obj);
        for(int nelly : ines){
            //Suppression suplémentaire
            DAO<Enseignement>adrien = DAOFactory.getEnseignementDAO();
            adrien.delete(new Enseignement("", "", "" ,Integer.toString(nelly)));
            
            DAO<Inscription>adrienn = DAOFactory.getInscriptionDAO();
            adrienn.delete(new Inscription("", "", Integer.toString(nelly)));
            //Suppression dans la table
            String requete = "DELETE FROM personne WHERE  `id` =" + nelly;
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
  public boolean update(Personne obj) {
      
      String requete = "UPDATE personne SET ";
      boolean virgule = false;
      
      if(!("".equals(obj.getNom()))){
          requete += " nom= "+"'" +obj.getNom()+"'" ;
          virgule=true;
          
           if(virgule==true ){
              if (!("".equals(obj.getPrenom())) || !("".equals(obj.getType()))) {
                  requete =requete + "," ;
              }
            }       
      }
            
      if(!("".equals(obj.getPrenom()))){
          requete += " prenom = "+ "' "+ obj.getPrenom()+ "' ";
          virgule=true;
          
           if(virgule==true){
               if(!("".equals(obj.getType()))){
          requete =requete + "," ;
               }
            } 
      }
      
      if(!("".equals(obj.getType()))){
          requete += " type = "+"'"+obj.getType()+ "' " + " ";
       
      }     
      
      
      requete += "WHERE id = " + obj.getId()+"" ;
      
      System.out.println(requete);
                 
     try {
            PreparedStatement statement = this.connect.prepareStatement(requete);
          
            statement.executeUpdate(); 
             System.out.println("personne update");
        } catch (SQLException ex) {
            System.out.println("pas udpade : "+ex.getMessage());
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  
  /**
   * recherche d'un element dans la bdd
   * @param id
   * @return la personne recherchée
   * sinon lance exception
   * 
   * si la personne est un eleve, on calcule sa moyenne générale
   * si la personne est un prof, on recherche les discilpines qu'il enseigne
   */
@Override
  public Personne find(String id) {
    Personne personne = new Personne();      
    String discipline = "physique";
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM personne WHERE id = " + id);
      if(result.first())
      {
         
        personne = new Personne(result.getString("id"),result.getString("nom"),result.getString("prenom"),result.getString("type"));  
        if(personne.getType().equals("Etudiant"))
        {
           
           personne.setMoyenne(moyenne(personne.getId()));
            personne.setAppreciation(appreciation(personne.getId()));
          
        }
        else if(personne.getType().equals("Prof"))
        {
            
            personne.setDd(retourDiscipline(personne.getId()));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return personne;
  }
  
  /**
   * récupérer toutes les personnes
   * @return ArrayList<Object> de personnes
   * 
   */
  
   public ArrayList<Object> retour()
  {
       ArrayList<Object> table = new ArrayList();
       
       try {
        ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM personne");
        //+nomTable+
           while(result.next()) {

               String id = result.getString(1);
               String nom = result.getString(2);
               String prenom = result.getString(3);
               String type = result.getString(4);

               Personne obj = new Personne(id,nom,prenom,type);
               table.add(obj);

          }
        
        } catch (SQLException e) {
         System.out.println("pas arraylist");
        }
       return table;
  }
   
   
   /**
    * donne la moyenne générale d'un eleve
    * @param id
    * @return moyenne
    */
    public float moyenne(String id)
    {
       
        float somme=0;
        ArrayList<Float> notes = new ArrayList<>();
      //  String id_eleve = eleve.getId();
      
        try {
       //  System.out.println("SELECT DISTINCT note FROM `personne`,`inscription`,`bulletin`,`detailbulletin`,`evaluation` WHERE `inscription`.`personneID` LIKE `"+id+"` AND `inscription`.`id` LIKE `bulletin`.`inscriptionID` AND `detailbulletin`.`bulletinID` LIKE `bulletin`.`id` AND `detailbulletin`.`id` LIKE `evaluation`.`detailBulletinID`   ");
   
        ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT DISTINCT note FROM personne,inscription,bulletin,detailbulletin,evaluation WHERE inscription.personneID LIKE "+id+" AND inscription.id LIKE bulletin.inscriptionID AND detailbulletin.bulletinID LIKE bulletin.id AND detailbulletin.id LIKE evaluation.detailBulletinID ");
        
        System.out.println("SELECT DISTINCT note FROM personne,inscription,bulletin,detailbulletin,evaluation WHERE inscription.personneID LIKE "+id+" AND inscription.id LIKE bulletin.inscriptionID AND detailbulletin.bulletinID LIKE bulletin.id AND detailbulletin.id LIKE evaluation.detailBulletinID ");
        while(result.next()) {

               String note = result.getString(1);
               float note2 = parseFloat(note);
               System.out.println("Note : " + note2);
               notes.add(note2);
               
          }
        
        } catch (SQLException e) {
         System.out.println("pas moyenne");
        }
        
        for(int i=0; i<notes.size(); i++)
        {
            somme += notes.get(i);
             System.out.println("array : "+somme);
             System.out.println("taille : "+notes.size());
        }
        
        if(notes.size() == 0){
            return 0;
        }
        return somme/notes.size();
    }
    
       /**
    * appreciation du bulletin
    * @param id
    * @return l'appreciation
    */
    public String appreciation(String id)
    {
        
        String app="";
        //System.out.println("SELECT DISTINCT appreciation FROM inscription,bulletin WHERE inscription.personneID LIKE "+id+" AND inscription.id LIKE bulletin.inscriptionID " );
         try {
        ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery( "SELECT DISTINCT appreciation FROM inscription,bulletin WHERE inscription.personneID LIKE "+id+" AND inscription.id LIKE bulletin.inscriptionID ");
        
       result.next();
        
         app = result.getString(1);
        //System.out.println(" appreciation : "+app );
        } catch (SQLException e) {
         System.out.println("pas appreciation");
         System.out.println(e.getMessage());
        }
        
       
        return app;
    }

   /**
    * 
    * @param id
    * @param discipline
    * @return la moyenne d'une discipline choisie
    */ 
   public float moyenneMatiere(String id, String discipline)
   {
         float somme=0;
        ArrayList<Float> notes = new ArrayList<>();
      
        try {
        ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT DISTINCT note FROM personne,inscription,bulletin,detailbulletin,evaluation,discipline,enseignement WHERE discipline.nom LIKE '"+discipline+"' AND inscription.personneID LIKE "+id+" AND inscription.id LIKE bulletin.inscriptionID AND detailbulletin.bulletinID LIKE bulletin.id AND detailbulletin.id LIKE evaluation.detailBulletinID AND detailbulletin.enseignementID LIKE enseignement.id AND enseignement.disciplineId LIKE discipline.id AND evaluation.detailBulletinID LIKE detailbulletin.id AND detailbulletin.enseignementID LIKE enseignement.id AND enseignement.disciplineId LIKE discipline.id");
        
        while(result.next()){
       
               String note = result.getString(1);
               float note2 = parseFloat(note);
               notes.add(note2);
          }
        
        } catch (SQLException e){
         System.out.println("pas moyenne");
         System.out.println(e.getMessage());
        }
            
        if(notes.size() == 0){
            return 0;
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
         //System.out.println("tab siez"+ tab_eval.size());
         //System.out.println("eval size"+ eval.size());
       /*
         for(int i =0; i<tab_eval.size();i++)
        {
            System.out.println( "evaluation n°"+i + ": (note et appréciation :) ");
            for(int j=0; j<tab_eval.get(i).size(); j++)
            {
             System.out.println( tab_eval.get(i).get(j));
            }
        }
        */
       
        return tab_eval;
   }
   
   
   
   /**
    * recherche les disciplines enseignées par un prof
    * @param id
    * @return arrayList de disciplines
    */
    public ArrayList<String> retourDiscipline(String id)
    {
      
        ArrayList<String> dd = new ArrayList<>();
     
        try {
        
        ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT DISTINCT nom FROM discipline,enseignement WHERE enseignement.personneId LIKE "+id+" AND enseignement.disciplineId LIKE discipline.id ");
        
        
        while(result.next()) {

               String d = result.getString(1);
               dd.add(d);
               
          }
        
        } catch (SQLException e) {
         System.out.println("pas discipline");
        }
        /*
        System.out.print("Les disciplines enseignées par ce prof sont : ");
        for(int i =0; i<dd.size(); i++)
        {
        System.out.print(dd.get(i) + " | ");
        }
        */
        return dd;
    }

    @Override
    public ArrayList<Integer> find(Personne inscriATrouver) {
        ArrayList<Integer> aRetourner = new ArrayList<Integer>();
      
        String requete = "SELECT * FROM personne WHERE";
        boolean virgule = false;

        if(!("".equals(inscriATrouver.getId()))){
            requete += " `id`= "+"'" +inscriATrouver.getId() +"'" ;
            virgule=true;      
        }

        if(!("".equals(inscriATrouver.getNom()))){         
            if(virgule){
                requete += " AND";
            }
            requete += " `nom` = '"+ inscriATrouver.getNom()+ "'";
            virgule=true;
        }


        if(!("".equals(inscriATrouver.getPrenom()))){
            if(virgule){
                requete += " AND";
            }
            requete += " `prenom` = '"+ inscriATrouver.getPrenom()+ "'";
            virgule=true;
        }

        if(!("".equals(inscriATrouver.getType()))){
            if(virgule){
                requete += " AND";
            }
            requete += " `type` = '"+ inscriATrouver.getType()+ "'";
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

   
}
