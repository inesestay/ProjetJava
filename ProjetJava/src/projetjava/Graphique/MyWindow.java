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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyWindow extends JFrame implements ActionListener {
	
    JButton button1, buttonConnexionBDD;
    JLabel label1, label2, label3;
    JPanel panelForButtons;    
    JPanel panelConnexion;
    JTextField idBDD;
    JTextField pswBDD;
    JTextField nomBDD;

    MyCanvas canvas;
		
    public MyWindow() {
	super();
	setLayout(new BorderLayout());
	button1= new JButton("Exit");
			
        button1.addActionListener(this);		
        panelForButtons=new JPanel();
        panelForButtons.add(button1);

	canvas = new MyCanvas(); 
        canvas.setOption(0);
        
        panelConnexion=new JPanel();
        
        panelConnexion.setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        
        buttonConnexionBDD= new JButton("Connexion");		
        buttonConnexionBDD.addActionListener(this);
            
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
        panelConnexion.add(label1, c);
        
        c.gridx = 1;
        panelConnexion.add(idBDD, c);
        
        c.gridy = 1;
        c.gridx = 0;
        panelConnexion.add(label2, c);
        
        c.gridx = 1;
        panelConnexion.add(pswBDD, c);
        
        c.gridy = 2;
        c.gridx = 0;
        panelConnexion.add(label3, c);
        
        c.gridx = 1;
        panelConnexion.add(nomBDD, c);
        
        c.gridy = 3;
        c.gridx = 1;
        panelConnexion.add(buttonConnexionBDD, c);

        panelConnexion.setBackground(Color.GREEN);
        
        add(panelConnexion, BorderLayout.CENTER);	
	add(panelForButtons, BorderLayout.SOUTH);      
    }
                                
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button1) {
            System.exit(0);
        }
        else if(e.getSource()==buttonConnexionBDD) {
            /*
            System.out.println("Val 1 : " + idBDD.getText());            
            System.out.println("Val 2 : " + pswBDD.getText());
            System.out.println("Val 3 : " + nomBDD.getText());
            */
            //Mettre la connexion à la BDD
        }
    }
}

