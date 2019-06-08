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
/**
 *
 * @author nelly
 */
public class ClasseDAO extends DAO<Classe> {
    
public ClasseDAO(Connection conn) {
    
    
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
    public boolean create(Classe obj) {
         try {
             
             if(("".equals(obj.getNom())) || ("".equals(obj.getNiveauID())) || ("".equals(obj.getAnneescolaireID()))){
        
                throw new SQLException("il manque un ou plusieurs champs");
         }
             
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO classe(nom,niveauID,anneescolaireID) VALUES(?,?,?)"
                    );
            statement.setObject(1,obj.getNom(),Types.VARCHAR); 
            statement.setObject(2,Integer.parseInt(obj.getNiveauID()),Types.INTEGER);
            statement.setObject(3,Integer.parseInt(obj.getAnneescolaireID()),Types.INTEGER);
            
            statement.executeUpdate(); 
             System.out.println("classe créée");
        } catch (SQLException ex) {
            System.out.println("pas create classe");
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
  public boolean delete(Classe obj) {
     
      String requete = "DELETE FROM classe WHERE";
      boolean virgule = false;
      
      if(!("".equals(obj.getNom()))){
          requete += " `nom`= "+"'" +obj.getNom()+"'" ;
          virgule=true;
          
           if(virgule==true ){
              if (!("".equals(obj.getNiveauID())) || !("".equals(obj.getAnneescolaireID())) || !("".equals(obj.getId())) ) {
                  requete =requete + " AND" ;
              }
            }       
      }
            
      if(!("".equals(obj.getNiveauID()))){
          requete += " `niveauID` = "+ "'"+ obj.getNiveauID()+ "'";
          virgule=true;
          
           if(virgule==true){
               if(!("".equals(obj.getAnneescolaireID())) || !("".equals(obj.getId()))){
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
             System.out.println("classe supp");
        } catch (SQLException ex) {
            System.out.println("pas supp classe");
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
  public boolean update(Classe obj) {
      
       String requete = "UPDATE classe SET ";
      boolean virgule = false;
      
      if(!("".equals(obj.getNom()))){
          requete += " nom= "+"'" +obj.getNom()+"'" ;
          virgule=true;
          
           if(virgule==true ){
              if (!("".equals(obj.getNiveauID())) || !("".equals(obj.getAnneescolaireID()))) {
                  requete =requete + "," ;
              }
            }       
      }
            
      if(!("".equals(obj.getNiveauID()))){
          requete += " niveauID = "+ "' "+ obj.getNiveauID()+ "' ";
          virgule=true;
          
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
             System.out.println("classe update");
        } catch (SQLException ex) {
            System.out.println("pas udpade classe");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  /**
   * recherche d'un element dans la bdd
   * @param id
   * @return la classe recherchée
   * sinon lance exception
   */
  public Classe find(String id) {
    Classe d = new Classe();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM classe WHERE id = " + id);
      if(result.first())
          
        d = new Classe(result.getString("id"),result.getString("nom"), result.getString("NiveauID") , result.getString("AnneeScolaireId"));         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return d;
  }
  
  
  /**
   * récupérer tous les classes
   * @return ArrayList<Object> de classes
   * 
   */
   public ArrayList<Object> retour()
  {
       ArrayList<Object> table = new ArrayList();
       
       try {
        ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM classe");
        //+nomTable+
           while(result.next()) {

               String id = result.getString(1);
               String nom = result.getString(2);
               String tri = result.getString(3);
               String insc = result.getString(4);

               Classe obj = new Classe(id,nom,tri,insc);
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
    public ArrayList<Integer> find(Classe inscriATrouver) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

