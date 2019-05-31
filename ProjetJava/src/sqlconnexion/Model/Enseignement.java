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
    private int disciplineId;
    
    ///CONSTRUCTORS
    public Enseignement(int id, int classeID, int enseignantID, int disciplineId ){
        this.id=id;
        this.classeID=classeID;
        this.enseignantID=enseignantID;
        this.disciplineId =disciplineId ;       
     
    }
    
    public Enseignement(){
        id = 0;
        classeID = 0;
        enseignantID = 0;
        disciplineId  = 0;
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
    public int getDisciplineID() {
        return disciplineId ;
    }

    public void setDisciplineID(int disciplineId ) {
        this.disciplineId  = disciplineId ;
    }
    
    
}
