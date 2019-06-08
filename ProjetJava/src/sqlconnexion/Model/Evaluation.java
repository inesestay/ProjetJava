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
public class Evaluation {
    private String id;
    private String appreciation;
    private String note;
    private String detailBulletinID;
    
    ///CONSTRUCTORS
    public Evaluation(){
        id="";
        appreciation="";
        note= "";
        detailBulletinID="";
    }
    
    public Evaluation(String id, String appreciation, String note, String detailBulletinID){
        this.id=id;
        this.appreciation=appreciation;
        this.note=note;
        this.detailBulletinID=detailBulletinID;
    }
    
    public Evaluation(String appreciation, String note, String detailBulletinID){
      
        this.appreciation=appreciation;
        this.note=note;
        this.detailBulletinID=detailBulletinID;
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
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return the detailBulletinID
     */
    public String getDetailBulletinID() {
        return detailBulletinID;
    }

    /**
     * @param detailBulletinID the detailBulletinID to set
     */
    public void setDetailBulletinID(String detailBulletinID) {
        this.detailBulletinID = detailBulletinID;
    }

    
    
}
