/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlconnexion.DAO;

/**
 *
 * @author inese
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import sqlconnexion.Model.Inscription;
import sqlconnexion.Model.Personne;

public class InscriptionDAO extends DAO<Inscription> {
    
public InscriptionDAO(Connection conn) {
    
    
    super(conn);
  }

  @Override
    public boolean create(Inscription obj){

    System.out.println("salut");
    if((obj.getClassID()==0) || (obj.getPersonneID()==0)){
        System.out.println("il manque un champ");
        return false;
    }

    try{
        PreparedStatement statement = this.connect.prepareStatement("INSERT INTO inscription(classID,personneID) VALUES(?,?)");

        statement.setObject(1,obj.getClassID(),Types.INTEGER); 
        statement.setObject(2,obj.getPersonneID(),Types.INTEGER); 
        statement.executeUpdate();
        System.out.println("inscription créée");      
    }catch (SQLException ex) {
        System.out.println("pas create : " + ex.getMessage());
        return false;
    }

    return true;
}

@Override
  public boolean delete(Inscription obj) {

   try {
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM inscription WHERE id = " + obj.getId()+""
                    );
           
            statement.executeUpdate(); 
             System.out.println("inscription supp");
        } catch (SQLException ex) {
            System.out.println("pas supp");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
@Override
  public boolean update(Inscription obj) {
      
     String requete = "UPDATE personne SET ";
      boolean virgule = false;
      
      if(!("".equals(obj.getClassID()))){
          requete += " nom= "+"'" +obj.getClassID()+"'" ;
          virgule=true;
          
           if(virgule==true ){
              if (!("".equals(obj.getPersonneID()))) {
                  requete =requete + "," ;
              }
            }       
      }

      if(!("".equals(obj.getPersonneID()))){
          requete += " type = "+"'"+obj.getPersonneID()+ "' " + " ";
       
      }     
      
      requete += "WHERE id = " + obj.getId()+"" ;
     
     try {

            PreparedStatement statement = this.connect.prepareStatement(requete);

            statement.executeUpdate(); 
             System.out.println("inscription update");
        } catch (SQLException ex) {
            System.out.println("pas udpade");
            return false;
        }
        //en spécifiant bien les types SQL cibles 
        
        return true;
  }
   
@Override
  public Inscription find(int id) {
    Inscription inscription = new Inscription();      
      
    try {
      ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM personne WHERE id = " + id);
      if(result.first())
        inscription = new Inscription(id,result.getInt("classID"),result.getInt("personneID"));         
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return inscription;
  }
  
   public ArrayList<Object> retour()
  {
       ArrayList<Object> table = new ArrayList();
       
       try {
        ResultSet result = this.connect.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM inscription");
        //+nomTable+
           while(result.next()) {

               int id = result.getInt(1);
               int a = result.getInt(1);
               int z = result.getInt(1);
               
               Inscription obj = new Inscription(id,a,z);
               table.add(obj);

          }
        

        } catch (SQLException e) {
         System.out.println("pas arraylist");
        }
       return table;
  }
}

