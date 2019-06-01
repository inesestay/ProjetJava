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
        private int id;
        private int classID;
        private int personneID;
        
    ///CONSTRUCTORS
        public Inscription(){
            id=0;
            classID=0;
            personneID=0;
        }
        
        public Inscription(int id, int classID, int personneID){
            this.id=id;
            this.classID=classID;
            this.personneID=personneID;
        }
        
        public Inscription(int classID, int personneID){
            this.id=-1;
            this.classID=classID;
            this.personneID=personneID;
        }
        
        
    
    ///GETTERS AND SETTERS

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the classID
     */
    public int getClassID() {
        return classID;
    }

    /**
     * @param classID the classID to set
     */
    public void setClassID(int classID) {
        this.classID = classID;
    }

    /**
     * @return the personneID
     */
    public int getPersonneID() {
        return personneID;
    }

    /**
     * @param personneID the personneID to set
     */
    public void setPersonneID(int personneID) {
        this.personneID = personneID;
    }
        
}
