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
public class Trimestre {
    
    ///CHARACTERISTICS
    private int id;
    private int num;
    private int debut;
    private int fin;
    private int anneescolaireID;
    
    ///CONSTRUCTORS
    public Trimestre(){
        id = 0;
        num=0;
        debut=0;
        fin=0;
        anneescolaireID=0;
    }
    
    public Trimestre(int id, int num, int debut, int fin, int anneescolaireID){
        this.id=id;
        this.num=num;
        this.debut=debut;
        this.fin=fin;
        this.anneescolaireID=anneescolaireID;      
    }
    
    ///GETTERS AND SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getDebut() {
        return debut;
    }

    public void setDebut(int debut) {
        this.debut = debut;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    public int getAnneescolaireID() {
        return anneescolaireID;
    }

    public void setAnneescolaireID(int anneescolaireID) {
        this.anneescolaireID = anneescolaireID;
    }
}
