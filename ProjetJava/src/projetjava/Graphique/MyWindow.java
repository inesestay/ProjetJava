/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava.Graphique;

/**
 *
 * @author quentin coucou
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyWindow extends JFrame implements ActionListener {
	
    JButton button1, button2,buttonConnexionBDD, addMenu, delMenu, dispMenu, menu;
    JLabel label1, label2, label3;
    JPanel panelForButtons, panelPrincipal;
    JTextField idBDD, pswBDD, nomBDD;
    JComboBox tablesBox;
    MyCanvas canvas;
		
    public MyWindow() {
	super();
	setLayout(new BorderLayout());
        
        buttonConnexionBDD= new JButton("Connexion");		
        
	button1= new JButton("Exit");
        button2= new JButton("Deconnexion");
        
        addMenu = new JButton("Ajout element");        
        delMenu = new JButton("Suppresion element");
        dispMenu = new JButton("Affichage table");
        menu = new JButton("Menu principal");

			
        button1.addActionListener(this);
        button2.addActionListener(this);
        
        buttonConnexionBDD.addActionListener(this);
        
        addMenu.addActionListener(this);        
        delMenu.addActionListener(this);
        dispMenu.addActionListener(this);
        menu.addActionListener(this);


        panelForButtons=new JPanel();
        panelForButtons.add(button1);

        /*
	canvas = new MyCanvas(); 
        canvas.setOption(0);
        */
        panelPrincipal=new JPanel();
        
        add(panelPrincipal, BorderLayout.CENTER);	
	add(panelForButtons, BorderLayout.SOUTH); 
        
        updatePannelPrincipal(0);
    }
                                
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button1) {
            System.exit(0);
        }
        else if(e.getSource()==button2) {
            updatePannelPrincipal(0);
        }
        else if(e.getSource()==menu) {
            updatePannelPrincipal(1);
        }
        else if(e.getSource()==buttonConnexionBDD) {
            /*
            System.out.println("Val 1 : " + idBDD.getText());            
            System.out.println("Val 2 : " + pswBDD.getText());
            System.out.println("Val 3 : " + nomBDD.getText());
            */
            //Mettre la connexion à la BDD
            panelForButtons.add(button2);
            panelForButtons.updateUI();
            
            updatePannelPrincipal(1);
            
        }else if(e.getSource()==addMenu) {
            updatePannelPrincipal(2);  
        }
        else if(e.getSource()==delMenu) {
            updatePannelPrincipal(3);  
        }else if(e.getSource()==dispMenu) {
            updatePannelPrincipal(4);  
        }else if(e.getSource()==tablesBox) {
            System.out.println((String)tablesBox.getSelectedItem());
        }
    }
    // 0 : menu connexion
    // 1 : menu principal
    // 2 : menu add
    // 3 : menu del
    // 4 : menu disp
    public void updatePannelPrincipal(int option){
        switch(option){
            //Menu connexion
            case 0:
                panelForButtons.removeAll(); 
                panelForButtons.add(button1);
                panelForButtons.updateUI();
            
                panelPrincipal.removeAll();
                panelPrincipal.setLayout(new GridBagLayout());

                GridBagConstraints c = new GridBagConstraints();

                idBDD = new JTextField();
                pswBDD = new JTextField();
                nomBDD = new JTextField();

                label1 = new JLabel("ID connexion");        
                label2 = new JLabel("Password connexion");
                label3 = new JLabel("Nom BDD");


                idBDD.setColumns(10);            
                pswBDD.setColumns(10);
                nomBDD.setColumns(10);

                c.gridy = 0;
                c.gridx = 0;
                panelPrincipal.add(label1, c);

                c.gridx = 1;
                panelPrincipal.add(idBDD, c);

                c.gridy = 1;
                c.gridx = 0;
                panelPrincipal.add(label2, c);

                c.gridx = 1;
                panelPrincipal.add(pswBDD, c);

                c.gridy = 2;
                c.gridx = 0;
                panelPrincipal.add(label3, c);

                c.gridx = 1;
                panelPrincipal.add(nomBDD, c);

                c.gridy = 3;
                c.gridx = 0;
                c.gridwidth = 2;
                panelPrincipal.add(buttonConnexionBDD, c);

                panelPrincipal.setBackground(Color.CYAN);
                
                panelPrincipal.updateUI();
                break;
            //menu principal
            case 1:
                //on enleve tout ce qui a ete ajouter dans window pour etre sur que ca reste pas
                try{
                    remove(tablesBox);
                }catch(Exception e){}
                panelForButtons.add(menu);
                panelPrincipal.removeAll();
                panelPrincipal.setLayout(new GridBagLayout());

                GridBagConstraints d = new GridBagConstraints();

                d.gridy = 0;
                d.gridx = 0;
                panelPrincipal.add(addMenu, d);
                
                d.gridy = 1;
                panelPrincipal.add(delMenu, d);
                
                d.gridy = 2;
                panelPrincipal.add(dispMenu, d);

                panelPrincipal.setBackground(Color.GRAY);
                break;
            case 2:
                
                break;
            case 3:
                
                break;
            case 4:
                panelPrincipal.removeAll();
                displayMenu();
                break;
        }
        
        panelPrincipal.updateUI();
    }
    
    public void displayMenu(){
        
        String[] listTableName = { "Personne", "Inscription" };

        tablesBox = new JComboBox(listTableName);
        tablesBox.addActionListener(this);
        
        add(tablesBox, BorderLayout.PAGE_START);
        
        
        
    }
}

