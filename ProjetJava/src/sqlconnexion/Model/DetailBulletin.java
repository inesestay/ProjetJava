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
public class DetailBulletin {
    ///CHARACTERISTICS
        private String id;
        private String appreciation;
        private String bulletinID;
        private String enseignementID;
        
    ///CONSTRUCTORS
        public DetailBulletin(){
            id="";
            appreciation="";
            bulletinID="";
            enseignementID="";
        }
        
        public DetailBulletin(String id, String appreciation, String bulletinID, String enseignementID){
            this.id=id;
            this.appreciation=appreciation;
            this.bulletinID=bulletinID;
            this.enseignementID=enseignementID;
          
        }
        
        public DetailBulletin( String appreciation, String bulletinID, String enseignementID){
        
            this.appreciation=appreciation;
            this.bulletinID=bulletinID;
            this.enseignementID=enseignementID;
          
        }
        
    ///GETTERS AND SETTERS
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public String getBulletinID() {
        return bulletinID;
    }

    public void setBulletinID(String bulletinID) {
        this.bulletinID = bulletinID;
    }

    public String getEnseignementID() {
        return enseignementID;
    }

    public void setEnseignementID(String enseignementID) {
        this.enseignementID = enseignementID;
    }
        
    
}
