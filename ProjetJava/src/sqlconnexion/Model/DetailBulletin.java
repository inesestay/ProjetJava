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
        private int id;
        private String appreciation;
        private int bulletinID;
        private int enseignementID;
        
    ///CONSTRUCTORS
        public DetailBulletin(){
            id=0;
            appreciation="";
            bulletinID=0;
            enseignementID=0;
        }
        
        public DetailBulletin(int id, String appreciation, int bulletinID, int enseignementID){
            this.id=id;
            this.appreciation=appreciation;
            this.bulletinID=bulletinID;
            this.enseignementID=enseignementID;
          
        }
        
        public DetailBulletin( String appreciation, int bulletinID, int enseignementID){
        
            this.appreciation=appreciation;
            this.bulletinID=bulletinID;
            this.enseignementID=enseignementID;
          
        }
        
    ///GETTERS AND SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public int getBulletinID() {
        return bulletinID;
    }

    public void setBulletinID(int bulletinID) {
        this.bulletinID = bulletinID;
    }

    public int getEnseignementID() {
        return enseignementID;
    }

    public void setEnseignementID(int enseignementID) {
        this.enseignementID = enseignementID;
    }
        
    
}
