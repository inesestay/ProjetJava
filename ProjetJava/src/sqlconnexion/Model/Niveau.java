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
public class Niveau {
    ///CHARACTERISTICS
    private int id;
    private int classeID;
    private int etudiantID;
    
    ///CONSTRUCTORS
    public Niveau(){
        id=0;
        classeID=0;
        etudiantID=0;
    }
    
    public Niveau(int id, int classeID,int etudiantID){
        this.id=id;
        this.classeID=classeID;
        this.etudiantID=etudiantID;
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

    public int getEtudiantID() {
        return etudiantID;
    }

    public void setEtudiantID(int etudiantID) {
        this.etudiantID = etudiantID;
    }

   
}
