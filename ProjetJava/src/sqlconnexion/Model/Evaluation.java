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
    private int id;
    private String appreciation;
    private int note;
    private int detailBulletinID;
    
    ///CONSTRUCTORS
    public Evaluation(){
        id=0;
        appreciation="";
        note= 0;
        detailBulletinID=0;
    }
    
    public Evaluation(int id, String appreciation, int note, int detailBulletinID){
        this.id=id;
        this.appreciation=appreciation;
        this.note=note;
        this.detailBulletinID=detailBulletinID;
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

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getDetailBulletinID() {
        return detailBulletinID;
    }

    public void setDetailBulletinID(int detailBulletinID) {
        this.detailBulletinID = detailBulletinID;
    }
    
}
