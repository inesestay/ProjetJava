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
import sqlconnexion.Model.Discipline;


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
      
      String requete = "DELETE FROM personne WHERE";
      boolean virgule = false;
      
      if(!("".equals(obj.getNom()))){
          requete += " `nom`= "+"'" +obj.getNom()+"'" ;
          virgule=true;
          
           if(virgule==true ){
              if (!("".equals(obj.getPrenom())) || !("".equals(obj.getType())) || !("".equals(obj.getId())) ) {
                  requete =requete + " AND" ;
              }
            }       
      }
            
      if(!("".equals(obj.getPrenom()))){
          requete += " `prenom` = "+ "'"+ obj.getPrenom()+ "'";
          virgule=true;
          
           if(virgule==true){
               if(!("".equals(obj.getType())) || !("".equals(obj.getId()))){
          requete =requete + " AND" ;
               }
            } 
      }
      
      if(!("".equals(obj.getType()))){
          requete += " `type` = "+"'"+obj.getType()+ "'";
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
             System.out.println("personne supp");
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
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM personne WHERE id = " + id);
      if(result.first())
      {
         
        personne = new Personne(result.getString("id"),result.getString("nom"),result.getString("prenom"),result.getString("type"));  
        if(personne.getType().equals("Etudiant"))
        {
            System.out.println("moy"+moyenne(personne.getId()));
            personne.setMoyenne(moyenne(personne.getId()));
        }
        else if(personne.getType().equals("Prof"))
        {
            
            personne.setDiscipline(retourDiscipline(personne.getId()));
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
         System.out.println("SELECT DISTINCT note FROM `personne`,`inscription`,`bulletin`,`detailbulletin`,`evaluation` WHERE `inscription`.`personneID` LIKE `"+id+"` AND `inscription`.`id` LIKE `bulletin`.`inscriptionID` AND `detailbulletin`.`bulletinID` LIKE `bulletin`.`id` AND `detailbulletin`.`id` LIKE `evaluation`.`detailBulletinID`   ");
   
        ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT DISTINCT note FROM personne,inscription,bulletin,detailbulletin,evaluation WHERE inscription.personneID LIKE "+id+" AND inscription.id LIKE bulletin.inscriptionID AND detailbulletin.bulletinID LIKE bulletin.id AND detailbulletin.id LIKE evaluation.detailBulletinID ");
        
        
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
            somme += notes.get(i);
             System.out.println("array : "+somme);
             System.out.println("taille : "+notes.size());
        }
        return somme/notes.size();
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
        
        System.out.print("Les disciplines enseignées par ce prof sont : ");
        for(int i =0; i<dd.size(); i++)
        {
        System.out.print(dd.get(i) + " | ");
        }
        
        return dd;
    }

   
}
