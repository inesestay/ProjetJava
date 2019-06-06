/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlconnexion.Model;

/**
 *
 * @author inese
 */
public class AnneeScolaire {
      private String AnneeScolaireID;
      
      public AnneeScolaire(){
          
      }
      
      public AnneeScolaire(String AnneeScolaireID){
            this.AnneeScolaireID = AnneeScolaireID;
      }
      
      public String getAnneeScolaireID(){
          return AnneeScolaireID;
      }
}
