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
     * @return the classeID
     */
    public String getClasseID() {
        return classeID;
    }

    /**
     * @param classeID the classeID to set
     */
    public void setClasseID(String classeID) {
        this.classeID = classeID;
    }

    /**
     * @return the enseignantID
     */
    public String getEnseignantID() {
        return enseignantID;
    }

    /**
     * @param enseignantID the enseignantID to set
     */
    public void setEnseignantID(String enseignantID) {
        this.enseignantID = enseignantID;
    }

    /**
     * @return the disciplineId
     */
    public String getDisciplineID() {
        return disciplineId;
    }

    /**
     * @param disciplineId the disciplineId to set
     */
    public void setDisciplineId(String disciplineId) {
        this.disciplineId = disciplineId;
    }

    
    
}
