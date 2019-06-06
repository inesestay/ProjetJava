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
    
    private String id;
    private String classeID;
    private String enseignantID;
    private String disciplineId;
    
    ///CONSTRUCTORS
    public Enseignement(String id, String classeID, String enseignantID, String disciplineId ){
        this.id=id;
        this.classeID=classeID;
        this.enseignantID=enseignantID;
        this.disciplineId =disciplineId ;       
     
    }
    public Enseignement( String classeID, String enseignantID, String disciplineId ){
      
        this.classeID=classeID;
        this.enseignantID=enseignantID;
        this.disciplineId =disciplineId ;       
     
    }
    
    public Enseignement(){
        id = "";
        classeID = "";
        enseignantID = "";
        disciplineId  = "";
    }
    
    ///GETTERS AND SETTERS

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getClasseID() {
        return classeID;
    }

    public void setClasseID(String classeID) {
        this.classeID = classeID;
    }

    public String getEnseignantID() {
        return enseignantID;
    }

    public void setEnseignantID(String enseignantID) {
        this.enseignantID = enseignantID;
    }
    public String getDisciplineID() {
        return disciplineId ;
    }

    public void setDisciplineID(String disciplineId ) {
        this.disciplineId  = disciplineId ;
    }
    
    
}
