/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlconnexion.DAO;
import sqlconnexion.Model.Personne;
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
public class PersonneDAO extends DAO<Personne> {
    
public PersonneDAO(Connection conn) {
    
    
    super(conn);
  }

  @Override
    public boolean create(Personne obj){
            
    if(("".equals(obj.getNom())) || (obj.getPrenom()=="") || (obj.getType()=="")){
        System.out.println("il manque un champ");
        return false;
    }

    try{
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

@Override
  public boolean delete(Personne obj) {

   try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM personne WHERE id = " + obj.getId()+""
                    );
           
            statement.executeUpdate(); 
             System.out.println("personne supp");
        } catch (SQLException ex) {
            System.out.println("pas supp");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
@Override
  public boolean update(Personne obj) {
      
     
     
     try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE personne SET nom= '"+ obj.getNom() +"', prenom= '"+ obj.getPrenom()+"', type= '"+obj.getType()+"' WHERE id = " + obj.getId()+"");
           
            statement.executeUpdate(); 
             System.out.println("personne update");
        } catch (SQLException ex) {
            System.out.println("pas udpade");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
@Override
  public Personne find(int id) {
    Personne personne = new Personne();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM personne WHERE id = " + id);
      if(result.first())
        personne = new Personne(id,result.getString("nom"),result.getString("prenom"),result.getString("type"));         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return personne;
  }
  
   public ArrayList<Object> retour()
  {
       ArrayList<Object> table = new ArrayList();
       
       try {
        ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM personne");
        //+nomTable+
           while(result.next()) {

               int id = result.getInt(1);
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
  
   
}
