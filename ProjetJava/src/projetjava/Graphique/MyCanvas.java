/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava.Graphique;

/**
 *
 * @author inese
 */

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import javax.swing.Timer;



public class MyCanvas extends Canvas implements KeyListener, ActionListener{
    int option;	
	
    BufferedImage menu;
    BufferedImage bim = new BufferedImage(1500,1000, BufferedImage.TYPE_INT_RGB);
    int x,y;
    
    JTextField idBDD;
    JTextField pswBDD;
    JTextField nomBDD;
    
    JButton buttonConnexionBDD;
        
    public MyCanvas() {
        super();	
        this.option=option;
	
        /*
	try {
            menu = ImageIO.read(new File("img/menu.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
	addKeyListener(this);
    }	
    
    public int getOption() {
	return option;
    }


    public void setOption(int option) {
        this.option = option;
	repaint();
    }
	

    public void paint (Graphics g) {
	System.out.println("Option : " + option);
        Graphics buf =bim.getGraphics();
        buf.setColor(Color.BLACK);
        buf.fillRect(0, 0, this.WIDTH, this.HEIGHT);
			
	//if(option==1)
            //buf.drawImage(menu, 0, 0, this.getWidth(), this.getHeight(), this);	
            
        //Menu Connexion BDD
        if(option == 0){
            
            
        }
        //Exit
        else if(option==1) {
            System.exit(0);
        }
             
        //Recuperation de fichier
        /*
        else if(option==4 || option==5 || option==6){
            JFileChooser choix = new JFileChooser();
            int retour=choix.showOpenDialog(this);
            if(retour==JFileChooser.APPROVE_OPTION){
                choix.getSelectedFile().getName();
                choix.getSelectedFile().
                getAbsolutePath();
            }
            String nom = choix.getSelectedFile().getName();
        }*/
	
        g.drawImage(bim, 0, 0, null);
    }
        
        @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
            break;
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}