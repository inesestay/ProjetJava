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

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the appreciation
     */
    public String getAppreciation() {
        return appreciation;
    }

    /**
     * @param appreciation the appreciation to set
     */
    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    /**
     * @return the bulletinID
     */
    public String getBulletinID() {
        return bulletinID;
    }

    /**
     * @param bulletinID the bulletinID to set
     */
    public void setBulletinID(String bulletinID) {
        this.bulletinID = bulletinID;
    }

    /**
     * @return the enseignementID
     */
    public String getEnseignementID() {
        return enseignementID;
    }

    /**
     * @param enseignementID the enseignementID to set
     */
    public void setEnseignementID(String enseignementID) {
        this.enseignementID = enseignementID;
    }

        
    
}
