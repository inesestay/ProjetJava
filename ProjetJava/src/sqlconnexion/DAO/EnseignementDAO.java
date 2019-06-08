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
public class EnseignementDAO extends DAO<Enseignement> {
    
public EnseignementDAO(Connection conn) {
    
    
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
    public boolean create(Enseignement obj) {
         try {
             if(("".equals(obj.getClasseID())) || ("".equals(obj.getDisciplineID())) || ("".equals(obj.getEnseignantID()))){
        
                throw new SQLException("il manque un ou plusieurs champs");
         }
             
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO enseignement(classeID,disciplineId,personneId) VALUES(?,?,?)");
            statement.setObject(1,Integer.parseInt(obj.getClasseID()),Types.INTEGER); 
            statement.setObject(2,Integer.parseInt(obj.getDisciplineID()),Types.INTEGER); 
            statement.setObject(3,Integer.parseInt(obj.getEnseignantID()),Types.INTEGER); 
            
            statement.executeUpdate(); 
             System.out.println("Enseignement créé");
        } catch (SQLException ex) {
            System.out.println("pas create Enseignement");
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
  public boolean delete(Enseignement obj) {
     
       String requete = "DELETE FROM enseignement WHERE";
      boolean virgule = false;
      
      if(!("".equals(obj.getClasseID()))){
          requete += " `classeID`= "+"'" +obj.getClasseID()+"'" ;
          virgule=true;
          
           if(virgule==true ){
              if (!("".equals(obj.getDisciplineID())) || !("".equals(obj.getEnseignantID())) || !("".equals(obj.getId())) ) {
                  requete =requete + " AND" ;
              }
            }       
      }
            
      if(!("".equals(obj.getDisciplineID()))){
          requete += " `disciplineId` = "+ "'"+ obj.getDisciplineID()+ "'";
          virgule=true;
          
           if(virgule==true){
               if(!("".equals(obj.getEnseignantID())) || !("".equals(obj.getId()))){
          requete =requete + " AND" ;
               }
            } 
      }
      
      if(!("".equals(obj.getEnseignantID()))){
          requete += " `personneId` = "+"'"+obj.getEnseignantID()+ "'";
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
             System.out.println("Enseignement supp");
        } catch (SQLException ex) {
            System.out.println("pas supp Enseignement");
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
   
  public boolean update(Enseignement obj) {
      
      String requete = "UPDATE enseignement SET ";
      boolean virgule = false;
      
      if(!("".equals(obj.getClasseID()))){
          requete += " classeID= "+"'" +obj.getClasseID()+"'" ;
          virgule=true;
          
           if(virgule==true ){
              if (!("".equals(obj.getDisciplineID())) || !("".equals(obj.getEnseignantID()))) {
                  requete =requete + "," ;
              }
            }       
      }
            
      if(!("".equals(obj.getDisciplineID()))){
          requete += " disciplineId = "+ "' "+ obj.getDisciplineID()+ "' ";
          virgule=true;
          
           if(virgule==true){
               if(!("".equals(obj.getEnseignantID()))){
          requete =requete + "," ;
               }
            } 
      }
      
      if(!("".equals(obj.getEnseignantID()))){
          requete += " personneId = "+"'"+obj.getEnseignantID()+ "' " + " ";
       
      }     
      
      requete += " WHERE id = " + obj.getId()+"" ;
      
      System.out.println(requete);
      
  try{
            PreparedStatement statement = this.connect.prepareStatement(requete);
           
            statement.executeUpdate(); 
             System.out.println("Enseignement update");
        } catch (SQLException ex) {
            System.out.println("pas udpade Enseignement");
            System.out.println(ex.getMessage());
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  
  /**
   * recherche d'un element dans la bdd
   * @param id
   * @return l'enseignement recherché
   * sinon lance exception
   */
  public Enseignement find(String id) {
    Enseignement d = new Enseignement();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM enseignement WHERE id = " + id);
      if(result.first())
          
        d = new Enseignement(result.getString("id"),result.getString("classeID"), result.getString("enseignantID"), result.getString("discipID"));         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return d;
  }
  
  
  /**
   * récupérer tous enseignements
   * @return ArrayList<Object> d'enseignements
   * 
   */
   public ArrayList<Object> retour()
  {
       ArrayList<Object> table = new ArrayList();
       
       try {
        ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM enseignement");
        //+nomTable+
           while(result.next()) {

               String id = result.getString(1);
               String a = result.getString(2);
               String z = result.getString(3);
               String e = result.getString(4);

               Enseignement obj = new Enseignement(id,a,z,e);
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
    public ArrayList<Integer> find(Enseignement inscriATrouver) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
