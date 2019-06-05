/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JTextField;
import sqlconnexion.Model.*;
import sqlconnexion.DAO.DAO;
import sqlconnexion.DAO.PersonneDAO;
import sqlconnexion.factory.DAOFactory;
import projetjava.Graphique.MainGraphique;

/**
 *
 * @author inese
 */
public class ProjetJava {

    /**
     * @param args the command line arguments
     */
   static JTextField nomBDD;
    
    public static void main(String[] args) {
        
        //Scanner sc = new Scanner(System.in);
       // System.out.println("saisir le nom de la base svp :");
       // bdd = sc.next();
        MainGraphique.launch();
    }
}
    

