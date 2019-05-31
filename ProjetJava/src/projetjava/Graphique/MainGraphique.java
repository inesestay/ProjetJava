/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava.Graphique;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author ququq
 */
public class MainGraphique {
    public static void launch(){
        System.out.println("Hello World !");
        Scanner sc = new Scanner(System.in);
        MyWindow mw = new MyWindow();
	mw.setSize(1000,800);
	mw.setVisible(true);
	mw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
