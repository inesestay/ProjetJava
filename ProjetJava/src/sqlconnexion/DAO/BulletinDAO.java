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
public class BulletinDAO extends DAO<Bulletin> {
    public BulletinDAO(Connection conn) {
    
    
    super(conn);
  }

  @Override
    public boolean create(Bulletin obj) {
         try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO bulletin VALUES(?,?,?,?)"
                    );
            statement.setObject(1,null,Types.INTEGER); 
            statement.setObject(2,obj.getAppreciation(),Types.VARCHAR);
            statement.setObject(3,obj.getTrimestreID(),Types.INTEGER);
            statement.setObject(4,obj.getInscriptionID(),Types.INTEGER);
            
            statement.executeUpdate(); 
             System.out.println(" bulletin créée");
        } catch (SQLException ex) {
            System.out.println("pas create bulletin");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
    }

  public boolean delete(Bulletin obj) {
     
  
   try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM bulletin WHERE id = " + obj.getId()+"");
           
            statement.executeUpdate(); 
             System.out.println("bulletin supp");
        } catch (SQLException ex) {
            System.out.println("pas supp bulletin");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public boolean update(Bulletin obj) {
      
  try{
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE bulletin SET appreciation= '"+ obj.getAppreciation() +"' WHERE id = " + obj.getId()+"");
           
            statement.executeUpdate(); 
             System.out.println("bulle update");
        } catch (SQLException ex) {
            System.out.println("pas udpade bulle");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
  public Bulletin find(String id) {
    Bulletin d = new Bulletin();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM bulletin WHERE id = " + id);
      if(result.first())
        d = new Bulletin(id,result.getString("appreciation"),result.getString("trimestreID"),result.getString("inscriptionID") );         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return d;
  }
  
   public ArrayList<Object> retour()
  {
       ArrayList<Object> table = new ArrayList();
       
       try {
        ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM bulletin");
        //+nomTable+
           while(result.next()) {

               String id = result.getString(1);
               String nom = result.getString(2);
               String tri = result.getString(3);
               String insc = result.getString(4);
           
               Bulletin obj = new Bulletin(id,nom,tri,insc);
               table.add(obj);

          }
        

        } catch (SQLException e) {
         System.out.println("pas arraylist");
        }
       return table;
  }
   
   /**
     *calcul la moyenne d'un eleve
     * @param eleve
     * @return
     */
    /*public float moyenne(Personne eleve)
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
    }*/
}
