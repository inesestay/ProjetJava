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
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import projetjava.Connexion;
import sqlconnexion.DAO.DAO;
import sqlconnexion.Model.Inscription;
import sqlconnexion.Model.Personne;
import sqlconnexion.factory.DAOFactory;

public class MyWindow extends JFrame implements ActionListener {
	
    JButton button1, button2,buttonConnexionBDD, addMenu, delMenu, dispMenu, modifMenu, menu, addElement, delElement, modifElement;
    JLabel label1, label2, label3, label4, errorText;
    JPanel panelForButtons, panelPrincipal;
    JTextField idBDD, pswBDD, nomBDD;
    JComboBox tablesBox, tablesBoxAdd, tablesBoxDel, tablesBoxModif;
    
    Connexion myBDD;
		
    ArrayList<JLabel> arrayJLabel;
    ArrayList<JTextField> arrayJTextField;
    
    public MyWindow() {
	super();
        
        arrayJLabel = null;
        arrayJTextField = null;
        
	setLayout(new BorderLayout());
        
        buttonConnexionBDD= new JButton("Connexion");		
        
	button1= new JButton("Exit");
        button2= new JButton("Deconnexion");
        
        addMenu = new JButton("Ajout element");        
        delMenu = new JButton("Suppresion element");
        dispMenu = new JButton("Affichage table");
        modifMenu = new JButton("Modification element");
        menu = new JButton("Menu principal");
        addElement = new JButton("Ajout Element");
        delElement = new JButton("Supprimer Element");
        modifElement = new JButton("Modifier Element");

			
        button1.addActionListener(this);
        button2.addActionListener(this);
        
        buttonConnexionBDD.addActionListener(this);
        
        addMenu.addActionListener(this);        
        delMenu.addActionListener(this);
        dispMenu.addActionListener(this);
        modifMenu.addActionListener(this);
        menu.addActionListener(this);
        addElement.addActionListener(this);
        delElement.addActionListener(this);
        modifElement.addActionListener(this);




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
            if(tablesBox != null){
                panelPrincipal.remove(tablesBox);
            }
            updatePannelPrincipal(0);
        }
        else if(e.getSource()==menu) {
            updatePannelPrincipal(1);
        }
        else if(e.getSource()==buttonConnexionBDD) {
            try{
                myBDD = null;
                myBDD = new Connexion(nomBDD.getText(),idBDD.getText(),pswBDD.getText());
            }
            catch(SQLException e1){
                errorText.setText("Error sql : " + (String)e1.getMessage());
            }
            catch(ClassNotFoundException e1){
                errorText.setText("Error class not found : " + (String)e1.getMessage());
            }
            catch (Exception e1){
                errorText.setText("Error : " + (String)e1.getMessage());

            }
            
            
            if(myBDD != null){
                panelForButtons.add(button2);
                panelForButtons.updateUI();
            
                updatePannelPrincipal(1);
            }
            panelPrincipal.updateUI();
            
            
        }else if(e.getSource()==addMenu) {
            updatePannelPrincipal(2);  
        }
        else if(e.getSource()==delMenu) {
            updatePannelPrincipal(3);  
        }else if(e.getSource()==dispMenu) {
            updatePannelPrincipal(4);  
        }else if(e.getSource()==modifMenu) {
            updatePannelPrincipal(5);  
        }

        else if(e.getSource()==tablesBox) {
            System.out.println((String)tablesBox.getSelectedItem());
            updateDisplayMenu((String)tablesBox.getSelectedItem());
        }else if(e.getSource()==tablesBoxAdd) {
            updateMenuAjout((String)tablesBoxAdd.getSelectedItem());
        }else if(e.getSource()==addElement){
            creationObjetRequetteAjout((String)tablesBoxAdd.getSelectedItem());
        }else if(e.getSource()==tablesBoxDel){
            updateMenuDel((String)tablesBoxDel.getSelectedItem());
        }else if(e.getSource()==delElement){
            creationObjetRequetteDel((String)tablesBoxDel.getSelectedItem());
        }else if(e.getSource()==tablesBoxModif){
            updateMenuModif((String)tablesBoxModif.getSelectedItem());
        }else if(e.getSource()==modifElement){
            creationObjetRequetteModif((String)tablesBoxModif.getSelectedItem());
        }
    }
    
    // 0 : menu connexion
    // 1 : menu principal
    // 2 : menu add
    // 3 : menu del
    // 4 : menu disp
    // 5 : modif
    public void updatePannelPrincipal(int option){
        if(tablesBox != null){
            panelPrincipal.remove(tablesBox);
        }
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
                errorText = new JLabel("");


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
                
                c.gridy = 4;
                c.gridx = 0;
                c.gridwidth = 2;
                panelPrincipal.add(errorText, c);

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
                
                d.gridy = 3;
                panelPrincipal.add(modifMenu, d);

                errorText.setText("");
                panelPrincipal.setBackground(Color.GRAY);
                break;
            case 2:
                panelPrincipal.removeAll();
                menuAjout();
                break;
            case 3:
                panelPrincipal.removeAll();
                delMenu();
                break;
            case 4:
                panelPrincipal.removeAll();
                displayMenu();
                break;
            case 5:
                System.out.println("Proute");
                panelPrincipal.removeAll();
                modifMenu();
                break;
        }
        
        panelPrincipal.updateUI();
    }
    
    public void creationObjetRequetteAjout(String table){
        //ici
        try{
            if(table == "Personne"){
                DAO<Personne> pers = DAOFactory.getPersonneDAO();
                //idd a regler
                pers.create(new Personne(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText()));
                errorText.setText("Personne ajoute !");
            }else if(table == "Inscription"){
                DAO<Inscription> obj = DAOFactory.getInscriptionDAO();
                //idd a regler
                obj.create(new Inscription(Integer.parseInt(arrayJTextField.get(0).getText()),Integer.parseInt(arrayJTextField.get(1).getText())));
                errorText.setText("Inscription ajoute !");
            }
        }
        catch (Exception e1){
            errorText.setText("Error : " + (String)e1.getMessage());
        }
    }
    
    public void menuAjout(){
        String[] listTableName = { "Personne", "Inscription"};
        
        tablesBoxAdd = new JComboBox(listTableName);
        tablesBoxAdd.addActionListener(this);
        
        panelPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints d = new GridBagConstraints();
        
        d.gridy = 0;
        d.gridx = 0;
        d.gridwidth = 2;
        panelPrincipal.add(tablesBoxAdd, d);
        
        updateMenuAjout((String)tablesBoxAdd.getSelectedItem());
    }
    
    public void updateMenuAjout(String ines){
        
        panelPrincipal.remove(addElement);
        panelPrincipal.remove(errorText);

        
        if(arrayJLabel == null){
            arrayJLabel = new ArrayList<JLabel>();
        }else{
            for(JLabel nelly : arrayJLabel){
                panelPrincipal.remove(nelly);
                
            }
            arrayJLabel = new ArrayList<JLabel>();
        }
        
        if(arrayJTextField == null){
            arrayJTextField = new ArrayList<JTextField>();
        }else{
            for(JTextField nelly : arrayJTextField){
                panelPrincipal.remove(nelly);
                
            }
            arrayJTextField = new ArrayList<JTextField>();
        }
        ArrayList<String> arrayElement = new ArrayList<String>();
        
        if(ines == "Personne"){
            arrayElement.add("nom");            
            arrayElement.add("prenom");
            arrayElement.add("type");
        }else if("Inscription" == ines){
            arrayElement.add("Classe");            
            arrayElement.add("Personne");
        }
        
        for(String nelly : arrayElement){
            arrayJLabel.add(new JLabel(nelly));
            arrayJTextField.add(new JTextField());
        }
        
        GridBagConstraints d = new GridBagConstraints();
        
        d.gridy = 0;
        d.gridx = 0;
        
        d.gridwidth = 2;
        panelPrincipal.add(tablesBoxAdd, d);
        
        d.gridwidth = 1;
        
        for(int i = 0; i < arrayElement.size(); i++){
            
            arrayJTextField.get(i).setColumns(15);
            d.gridy ++;
            d.gridx = 0;
            panelPrincipal.add(arrayJLabel.get(i), d);
            d.gridx = 1;
            panelPrincipal.add(arrayJTextField.get(i), d);
        }
        
        d.gridy += 2;
        d.gridx = 0;
        d.gridwidth = 2;
        panelPrincipal.add(addElement, d);
        d.gridy++;
        panelPrincipal.add(errorText,d);
        
        panelPrincipal.updateUI();
    }
    
    public void delMenu(){
        String[] listTableName = { "Personne", "Inscription"};
        
        tablesBoxDel = new JComboBox(listTableName);
        tablesBoxDel.addActionListener(this);
        
        panelPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints d = new GridBagConstraints();
        
        d.gridy = 0;
        d.gridx = 0;
        d.gridwidth = 2;
        panelPrincipal.add(tablesBoxDel, d);
        
        updateMenuDel((String)tablesBoxDel.getSelectedItem());
    
    }
    
    public void updateMenuDel(String table){
        panelPrincipal.remove(delElement);
        panelPrincipal.remove(errorText);

        
        if(arrayJLabel == null){
            arrayJLabel = new ArrayList<JLabel>();
        }else{
            for(JLabel nelly : arrayJLabel){
                panelPrincipal.remove(nelly);
                
            }
            arrayJLabel = new ArrayList<JLabel>();
        }
        
        if(arrayJTextField == null){
            arrayJTextField = new ArrayList<JTextField>();
        }else{
            for(JTextField nelly : arrayJTextField){
                panelPrincipal.remove(nelly);
                
            }
            arrayJTextField = new ArrayList<JTextField>();
        }
        ArrayList<String> arrayElement = new ArrayList<String>();
        
        if(table == "Personne"){
            arrayElement.add("id");            
            arrayElement.add("nom");            
            arrayElement.add("prenom");
            arrayElement.add("type");
        }else if("Inscription" == table){
            arrayElement.add("id");  
            arrayElement.add("Classe");            
            arrayElement.add("Personne");
        }
        
        for(String nelly : arrayElement){
            arrayJLabel.add(new JLabel(nelly));
            arrayJTextField.add(new JTextField());
        }
        
        GridBagConstraints d = new GridBagConstraints();
        
        d.gridy = 0;
        d.gridx = 0;
        
        d.gridwidth = 2;
        panelPrincipal.add(tablesBoxDel, d);
        
        
        d.gridwidth = 1;
        
        for(int i = 0; i < arrayElement.size(); i++){
            
            arrayJTextField.get(i).setColumns(15);
            d.gridy ++;
            d.gridx = 0;
            panelPrincipal.add(arrayJLabel.get(i), d);
            d.gridx = 1;
            panelPrincipal.add(arrayJTextField.get(i), d);
        }
        
        d.gridy += 2;
        d.gridx = 0;
        d.gridwidth = 2;
        panelPrincipal.add(delElement, d);
        
        d.gridy++;
        panelPrincipal.add(errorText, d);
        panelPrincipal.updateUI();
    }
    
    public void creationObjetRequetteDel(String table){
        //ici
        try{
            if(table == "Personne"){
                DAO<Personne> pers = DAOFactory.getPersonneDAO();
                //idd a regler
                if(pers.delete(new Personne(Integer.parseInt(arrayJTextField.get(0).getText()),arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText()))){
                    errorText.setText("Personne supprimer !");
                }else{
                    errorText.setText("Personne pas supprimer !");
                }
            }
                
            else if(table == "Inscription"){
                DAO<Inscription> obj = DAOFactory.getInscriptionDAO();
                    //idd a regler
                if(obj.delete(new Inscription(Integer.parseInt(arrayJTextField.get(0).getText()),Integer.parseInt(arrayJTextField.get(1).getText()), Integer.parseInt(arrayJTextField.get(2).getText())))){
                    errorText.setText("Inscription supprimer !");
                }else{
                    errorText.setText("Inscription non supprimer !");
                }
                    
            }
        }
        catch (Exception e1){
            errorText.setText("Error : " + (String)e1.getMessage());
        }
    }
    
    public void displayMenu(){
        
        String[] listTableName = { "Personne", "Inscription" };

        tablesBox = new JComboBox(listTableName);
        tablesBox.addActionListener(this);
        
        add(tablesBox, BorderLayout.PAGE_START); 
        
        updateDisplayMenu((String)tablesBox.getSelectedItem());
    }
    
    public void updateDisplayMenu(String nomCategorie){
        ArrayList<Object> myArray = new ArrayList<Object>();
        if(nomCategorie == "Personne"){   
        }
        else if(nomCategorie == "Inscription"){
        }
        
        GridBagConstraints d = new GridBagConstraints();
        
        d.gridy = -1;
        d.gridx = 0;
        
        d.gridwidth = 1;
        
        for(int ines = 0; ines < myArray.size(); ines ++){
            d.gridy++;
            d.gridx = 0;
            
            ArrayList<JLabel> helene = new ArrayList<JLabel>();
            
            if(nomCategorie == "Personne"){
                Personne nelly = (Personne)myArray.get(ines);
                helene.add(new JLabel(Integer.toString(nelly.getId())));
                helene.add(new JLabel(nelly.getNom()));
                helene.add(new JLabel(nelly.getPrenom()));
                helene.add(new JLabel(nelly.getType()));
            }else if(nomCategorie == "Inscription"){
                Inscription nelly = (Inscription)myArray.get(ines);
                helene.add(new JLabel(Integer.toString(nelly.getId())));
                helene.add(new JLabel(Integer.toString(nelly.getClassID())));
                helene.add(new JLabel(Integer.toString(nelly.getPersonneID())));
            }
            
            
            for(int adrien = 0; adrien < helene.size(); adrien ++){
                panelPrincipal.add(helene.get(adrien), d);
                d.gridx++;
            }
        }
        
    }
    public void updateMenuModif(String table){
        panelPrincipal.remove(modifElement);
        panelPrincipal.remove(errorText);

        
        if(arrayJLabel == null){
            arrayJLabel = new ArrayList<JLabel>();
        }else{
            for(JLabel nelly : arrayJLabel){
                panelPrincipal.remove(nelly);
                
            }
            arrayJLabel = new ArrayList<JLabel>();
        }
        
        if(arrayJTextField == null){
            arrayJTextField = new ArrayList<JTextField>();
        }else{
            for(JTextField nelly : arrayJTextField){
                panelPrincipal.remove(nelly);
                
            }
            arrayJTextField = new ArrayList<JTextField>();
        }
        ArrayList<String> arrayElement = new ArrayList<String>();
        
        if(table == "Personne"){
            arrayElement.add("id");            
            arrayElement.add("nom");            
            arrayElement.add("prenom");
            arrayElement.add("type");
        }else if("Inscription" == table){
            arrayElement.add("id");  
            arrayElement.add("Classe");            
            arrayElement.add("Personne");
        }
        
        for(String nelly : arrayElement){
            arrayJLabel.add(new JLabel(nelly));
            arrayJTextField.add(new JTextField());
        }
        
        GridBagConstraints d = new GridBagConstraints();
        
        d.gridy = 0;
        d.gridx = 0;
        
        d.gridwidth = 2;
        panelPrincipal.add(tablesBoxModif, d);
        
        
        d.gridwidth = 1;
        
        for(int i = 0; i < arrayElement.size(); i++){
            
            arrayJTextField.get(i).setColumns(15);
            d.gridy ++;
            d.gridx = 0;
            panelPrincipal.add(arrayJLabel.get(i), d);
            d.gridx = 1;
            panelPrincipal.add(arrayJTextField.get(i), d);
        }
        
        d.gridy += 2;
        d.gridx = 0;
        d.gridwidth = 2;
        panelPrincipal.add(modifElement, d);
        
        d.gridy++;
        panelPrincipal.add(errorText, d);
        panelPrincipal.updateUI();
    }
    
    public void creationObjetRequetteModif(String table){
        //ici
        try{
            if(table == "Personne"){
                DAO<Personne> pers = DAOFactory.getPersonneDAO();
                //idd a regler
                if(pers.update(new Personne(Integer.parseInt(arrayJTextField.get(0).getText()),arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText()))){
                    errorText.setText("Personne modifier !");
                }else{
                    errorText.setText("Personne non modifier !");
                }
            }
            else if(table == "Inscription"){
                DAO<Inscription> obj = DAOFactory.getInscriptionDAO();
                //idd a regler
                if(obj.update(new Inscription(Integer.parseInt(arrayJTextField.get(0).getText()),Integer.parseInt(arrayJTextField.get(1).getText()), Integer.parseInt(arrayJTextField.get(2).getText())))){
                    errorText.setText("Inscription modifier !");
                }else{
                    errorText.setText("Inscription non modifier !");
                }
                
            }
        }
        catch (Exception e1){
            errorText.setText("Error : " + (String)e1.getMessage());
        }
    }
}

