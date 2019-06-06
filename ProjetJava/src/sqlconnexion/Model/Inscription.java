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
public class Inscription {
    ///CHARACTERISTICS
        private String id;
        private String classID;
        private String personneID;
        
    ///CONSTRUCTORS
        public Inscription(){
            id="";
            classID="";
            personneID="";
        }
        
        public Inscription(String id, String classID, String personneID){
            this.id=id;
            this.classID=classID;
            this.personneID=personneID;
        }
        
        public Inscription(String classID, String personneID){
            this.id=id;
            this.classID=classID;
            this.personneID=personneID;
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
     * @return the classID
     */
    public String getClassID() {
        return classID;
    }

    /**
     * @param classID the classID to set
     */
    public void setClassID(String classID) {
        this.classID = classID;
    }

    /**
     * @return the personneID
     */
    public String getPersonneID() {
        return personneID;
    }

    /**
     * @param personneID the personneID to set
     */
    public void setPersonneID(String personneID) {
        this.personneID = personneID;
    }
        
}
