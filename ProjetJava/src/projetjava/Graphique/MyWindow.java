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
        
        panelConnexion.setLayout(new GridLayout(4,1));
        
        
        buttonConnexionBDD= new JButton("Connexion");		
        buttonConnexionBDD.addActionListener(this);
            
        JTextField idBDD = new JTextField();
        JTextField pswBDD = new JTextField();
        JTextField nomBDD = new JTextField();

        idBDD.setColumns(10);            
        pswBDD.setColumns(10);
        nomBDD.setColumns(10);
        
        panelConnexion.add(idBDD);        
        panelConnexion.add(pswBDD);
        panelConnexion.add(nomBDD);
        panelConnexion.add(buttonConnexionBDD);

        
        add(panelConnexion, BorderLayout.CENTER);	
	add(panelForButtons, BorderLayout.SOUTH);      
    }
                                
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button1) {
            System.exit(0);
        }
        else if(e.getSource()==buttonConnexionBDD) {
            System.out.println("YES");
        }
    }
}

