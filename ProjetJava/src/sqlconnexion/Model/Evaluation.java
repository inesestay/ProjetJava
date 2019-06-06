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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDetailBulletinID() {
        return detailBulletinID;
    }

    public void setDetailBulletinID(String detailBulletinID) {
        this.detailBulletinID = detailBulletinID;
    }
    
}
