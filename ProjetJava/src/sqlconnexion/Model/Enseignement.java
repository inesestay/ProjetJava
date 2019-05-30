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
public class Enseignement {
    ///CHARACTERISTICS
    
    private int id;
    private int classeID;
    private int enseignantID;
    private int discipID;
    
    ///CONSTRUCTORS
    public Enseignement(int id, int classeID, int enseignantID, int discipID){
        this.id=id;
        this.classeID=classeID;
        this.enseignantID=enseignantID;
        this.discipID=discipID;       
     
    }
    
    public Enseignement(){
        id = 0;
        classeID = 0;
        enseignantID = 0;
        discipID = 0;
    }
    
    ///GETTERS AND SETTERS

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getClasseID() {
        return classeID;
    }

    public void setClasseID(int classeID) {
        this.classeID = classeID;
    }

    public int getEnseignantID() {
        return enseignantID;
    }

    public void setEnseignantID(int enseignantID) {
        this.enseignantID = enseignantID;
    }
    public int getDiscipID() {
        return discipID;
    }

    public void setDiscipID(int discipID) {
        this.discipID = discipID;
    }
    
    
}
