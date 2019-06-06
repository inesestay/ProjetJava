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
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import projetjava.Connexion;
import sqlconnexion.DAO.DAO;
import sqlconnexion.Model.*;
import sqlconnexion.factory.DAOFactory;

public class MyWindow extends JFrame implements ActionListener {

    boolean affichageSupp;
    JButton button1, button2,buttonConnexionBDD, addMenu, delMenu, dispMenu, modifMenu, menu, addElement, delElement, modifElement;
    JLabel label1, label2, label3, label4, errorText;
    JPanel panelForButtons, panelPrincipal;
    JTextField idBDD, pswBDD;
    public static JTextField nomBDD;
    JComboBox tablesBox, tablesBoxAdd, tablesBoxDel, tablesBoxModif;
    ArrayList<MyWindow> mw;
    String tableEtudier;

    Connexion myBDD;

    ArrayList<JLabel> arrayJLabel;
    ArrayList<JTextField> arrayJTextField;

    public MyWindow(String nom) {
	super();

        arrayJLabel = null;
        arrayJTextField = null;
        affichageSupp = false;
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
        
        idBDD = new JTextField();
        pswBDD = new JTextField();
        nomBDD = new JTextField();

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


        mw = new ArrayList<MyWindow>();
        tableEtudier = new String();

        panelForButtons=new JPanel();
        panelForButtons.add(button1);

        panelPrincipal=new JPanel();

        add(panelPrincipal, BorderLayout.CENTER);
	add(panelForButtons, BorderLayout.SOUTH);
        
        
        if(!nom.equals("")){
            System.out.println("OUI");
            nomBDD.setText(nom);
        }
        
        System.out.println("Bonsoir : " + nomBDD.getText());
        updatePannelPrincipal(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button1) {
            setVisible(false); //you can't see me!
            dispose(); //Destroy the JFrame object
        }
        else if(e.getSource()==button2) {             
            delAllWindowSecondary();         
            updatePannelPrincipal(0);
        }
        else if(e.getSource()==menu) {
            delAllWindowSecondary();
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
            updateDisplayMenu((String)tablesBox.getSelectedItem(), panelPrincipal, 0,0);
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
        switch(option){
            //Menu connexion
            case 0:
                try{
                    remove(tablesBox);
                }catch(Exception e){}
                panelForButtons.removeAll();
                panelForButtons.add(button1);
                panelForButtons.updateUI();

                panelPrincipal.removeAll();
                panelPrincipal.setLayout(new GridBagLayout());

                GridBagConstraints c = new GridBagConstraints();

                

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
                affichageSupp = false;
                delMenu();
                break;
            case 4:
                panelPrincipal.removeAll();
                displayMenu();
                break;
            case 5:
                panelPrincipal.removeAll();
                affichageSupp = false;
                modifMenu();
                break;
        }

        panelPrincipal.updateUI();
    }

    public void creationObjetRequetteAjout(String table){
        //ici
        boolean ines = false;
        try{
            //En fonction de la table, appelle la bonne requette
                    /////////////a modifier
            if(table == "Personne"){
                DAO<Personne> pers = DAOFactory.getPersonneDAO();
                //idd a regler
                ines = pers.create(new Personne(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText()));
                errorText.setText("Personne ajoute !");
            }else if(table == "Inscription"){
                DAO<Inscription> obj = DAOFactory.getInscriptionDAO();
                //idd a regler
                ines = obj.create(new Inscription(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText()));
                errorText.setText("Inscription ajoute !");
            }else if(table == "AnneeScolaire"){
                DAO<AnneeScolaire> obj = DAOFactory.getAnneeScolaireDAO();
                //idd a regler
                ines = obj.create(new AnneeScolaire(arrayJTextField.get(0).getText()));
                errorText.setText("Annee scolaire ajoute !");
            }else if(table == "Bulletin"){
                DAO<Bulletin> obj = DAOFactory.getBulletinDAO();
                //idd a regler
                ines = obj.create(new Bulletin(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText()));
                errorText.setText(" Bulletin ajoute !");
            }
            else if(table == "Classe"){
                DAO<Classe> obj = DAOFactory.getClasseDAO();
                //idd a regler
                ines = obj.create(new Classe(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText()));
                errorText.setText(" Classe ajoute !");
            }else if(table == "DetailBulletin"){
                DAO<DetailBulletin> obj = DAOFactory.getDetailBulletinDAO();
                //idd a regler
                ines = obj.create(new DetailBulletin(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText()));
                errorText.setText(" DetailBulletin ajoute !");
            }else if(table == "Discipline"){
                DAO<Discipline> obj = DAOFactory.getDisciplineDAO();
                //idd a regler
                ines = obj.create(new Discipline(arrayJTextField.get(0).getText()));
                errorText.setText(" Discipline ajoute !");
            }else if(table == "Enseignement"){
                DAO<Enseignement> obj = DAOFactory.getEnseignementDAO();
                //idd a regler
                ines = obj.create(new Enseignement(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText()));
                errorText.setText(" Enseignement ajoute !");
            }else if(table == "Evaluation"){
                DAO<Evaluation> obj = DAOFactory.getEvaluationDAO();
                //idd a regler
                ines = obj.create(new Evaluation(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText()));
                errorText.setText(" Evaluation ajoute !");
            }else if(table == "Trimestre"){
                DAO<Trimestre> obj = DAOFactory.getTrimestreDAO();
                //idd a regler
                ines = obj.create(new Trimestre(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText()));
                errorText.setText(" Trimestre ajoute !");
            }else if(table == "Niveau"){
                DAO<Niveau> obj = DAOFactory.getNiveauDAO();
                //idd a regler
                ines = obj.create(new Niveau(arrayJTextField.get(0).getText()));
                errorText.setText(" Niveau ajoute !");
            }
            
            if(!ines){
                errorText.setText("Error : Requette non envoye");
            }

        }
        catch (Exception e1){
            errorText.setText("Error : " + (String)e1.getMessage());
        }
    }

    public void menuAjout(){
        //Liste de toute les tables à mettre dans la box depliant
        /////////////a modifier
        String[] listTableName = { "Personne", "Inscription", "AnneeScolaire","Bulletin","Classe","DetailBulletin","Discipline","Enseignement","Evaluation","Niveau","Trimestre"};

        tablesBoxAdd = new JComboBox(listTableName);
        tablesBoxAdd.addActionListener(this);

        panelPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints d = new GridBagConstraints();

        d.gridy = 0;
        d.gridx = 0;
        d.gridwidth =11;
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


        //ArrayList des noms de ligne dans chaque table
        /////////////a modifier
        if(ines == "Personne"){
            arrayElement.add("nom");
            arrayElement.add("prenom");
            arrayElement.add("type");
        }else if("Inscription" == ines){
            arrayElement.add("Classe");
            arrayElement.add("Personne");
        }else if("AnneeScolaire" == ines){
            arrayElement.add("Année");
        } else if("Bulletin" == ines){
            arrayElement.add("Appréciation");
            arrayElement.add("Trimestre ID");
            arrayElement.add("Trimestre ID");
        }else if("Classe" == ines){
            arrayElement.add("Nom");
            arrayElement.add("Niveau ID");
            arrayElement.add("Annee scolaire ID");
        }else if("DetailBulletin" == ines){
            arrayElement.add("Appreciation");
            arrayElement.add("Bulletin ID");
            arrayElement.add("Enseignemnt ID");
        }else if("Discipline" == ines){
            arrayElement.add("Nom");
        }else if("Enseignement" == ines){
            arrayElement.add("Classe ID");
            arrayElement.add("Enseignant ID");
            arrayElement.add("Discipline ID");
        }else if("Evaluation" == ines){
            arrayElement.add("Appréciation");
            arrayElement.add("Note");
            arrayElement.add("DétailBulletin ID");
        }else if("Niveau" == ines){
            arrayElement.add("Nom");
        }else if("Trimestre" == ines){
            arrayElement.add("Numéro");
            arrayElement.add("Début");
            arrayElement.add("Fin");
            arrayElement.add("Année Scolaire ID");
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
                /////////////a modifier
        String[] listTableName = { "Personne", "Inscription", "AnneeScolaire","Bulletin","Classe","DetailBulletin","Discipline","Enseignement","Evaluation","Niveau","Trimestre"};

        tablesBoxDel = new JComboBox(listTableName);
        tablesBoxDel.addActionListener(this);

        panelPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints d = new GridBagConstraints();

        d.gridy = 0;
        d.gridx = 0;
        d.gridwidth = 11;
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

        /////////////a modifier
        if(table == "Personne"){
            arrayElement.add("id");
            arrayElement.add("nom");
            arrayElement.add("prenom");
            arrayElement.add("type");
        }else if("Inscription" == table){
            arrayElement.add("id");
            arrayElement.add("Classe");
            arrayElement.add("Personne");
        }else if("AnneeScolaire" == table){
            arrayElement.add("Année");
        } else if("Bulletin" == table){
             arrayElement.add("id");
            arrayElement.add("Appréciation");
            arrayElement.add("Trimestre ID");
            arrayElement.add("Trimestre ID");
        }else if("Classe" == table){
             arrayElement.add("id");
            arrayElement.add("Nom");
            arrayElement.add("Niveau ID");
            arrayElement.add("Annee scolaire ID");
        }else if("DetailBulletin" == table){
             arrayElement.add("id");
            arrayElement.add("Appreciation");
            arrayElement.add("Bulletin ID");
            arrayElement.add("Enseignemnt ID");
        }else if("Discipline" == table){
             arrayElement.add("id");
            arrayElement.add("Nom");
        }else if("Enseignement" == table){
             arrayElement.add("id");
            arrayElement.add("Classe ID");
            arrayElement.add("Enseignant ID");
            arrayElement.add("Discipline ID");
        }else if("Evaluation" == table){
             arrayElement.add("id");
            arrayElement.add("Appréciation");
            arrayElement.add("Note");
            arrayElement.add("DétailBulletin ID");
        }else if("Niveau" == table){
             arrayElement.add("id");
            arrayElement.add("Nom");
        }else if("Trimestre" == table){
             arrayElement.add("id");
            arrayElement.add("Numéro");
            arrayElement.add("Début");
            arrayElement.add("Fin");
            arrayElement.add("Année Scolaire ID");
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
 
        if(affichageSupp){
            MyWindow helene = new MyWindow(nomBDD.getText());
            helene.setSize(500,1000);
            helene.setVisible(true);
            helene.updateDisplayMenu(table, helene.panelPrincipal, 0, 0);
            
            mw.add(helene);
        }else{
            affichageSupp = true;
        }
        
        
        panelPrincipal.updateUI();

    }

    public void creationObjetRequetteDel(String table){
        //ici
        try{

            /////////////a modifier
            if(table == "Personne"){
                DAO<Personne> pers = DAOFactory.getPersonneDAO();
                //idd a regler

                if(pers.delete(new Personne(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText()))){
                    errorText.setText("Personne supprimée !");

                }else{
                    errorText.setText("Personne pas supprimée !");
                }
            }

            else if(table == "Inscription"){
                DAO<Inscription> obj = DAOFactory.getInscriptionDAO();
                    //idd a regler
                if(obj.delete(new Inscription(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText()))){
                    errorText.setText("Inscription supprimée !");
                }else{
                    errorText.setText("Inscription non supprimée !");
                }

            }
            else if(table == "AnneeScolaire"){
                DAO<AnneeScolaire> obj = DAOFactory.getAnneeScolaireDAO();
                //idd a regler
                if(obj.delete(new AnneeScolaire(arrayJTextField.get(0).getText())))
                errorText.setText("AnneeScolaire supprimée !");
                else{
                    errorText.setText("AnneeScolaire non supprimée !");
                }

            }else if(table == "Bulletin"){
                DAO<Bulletin> obj = DAOFactory.getBulletinDAO();
                //idd a regler
               if( obj.delete(new Bulletin(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText())))
               errorText.setText("Bulletin supprimé !");
                else{
                    errorText.setText("Bulletin non supprimé !");
                }
            }
            else if(table == "Classe"){
                DAO<Classe> obj = DAOFactory.getClasseDAO();
                //idd a regler
                if(obj.delete(new Classe(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText())))
                errorText.setText("Classe supprimée!");
                else{
                    errorText.setText("Classe non supprimée !");
                }
            }else if(table == "DetailBulletin"){
                DAO<DetailBulletin> obj = DAOFactory.getDetailBulletinDAO();
                //idd a regler
               if( obj.delete(new DetailBulletin(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText())))
                errorText.setText("DetailBulletin supprimé !");
                else{
                    errorText.setText("DetailBulletin non supprimé !");
                }
            }else if(table == "Discipline"){
                DAO<Discipline> obj = DAOFactory.getDisciplineDAO();
                //idd a regler
                if(obj.delete(new Discipline(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText())))
               errorText.setText("Discipline supprimée !");
                else{
                    errorText.setText("Discipline non supprimée !");
                }
            }else if(table == "Enseignement"){
                DAO<Enseignement> obj = DAOFactory.getEnseignementDAO();
                //idd a regler
                if(obj.delete(new Enseignement(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText())))
                errorText.setText("Enseignement supprimé !");
                else{
                    errorText.setText("Enseignement non supprimé !");
                }
            }else if(table == "Evaluation"){
                DAO<Evaluation> obj = DAOFactory.getEvaluationDAO();
                //idd a regler
                if(obj.delete(new Evaluation(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText())))
                errorText.setText("Evaluation supprimée !");
                else{
                    errorText.setText("Evaluation non supprimée !");
                }
            }else if(table == "Trimestre"){
                DAO<Trimestre> obj = DAOFactory.getTrimestreDAO();
                //idd a regler
                if(obj.delete(new Trimestre(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText(),arrayJTextField.get(4).getText())))
                errorText.setText("Trimestre supprimé !");
                else{
                    errorText.setText("Trimestre non supprimé !");
                }
            }
            else if(table == "Niveau"){
                DAO<Niveau> obj = DAOFactory.getNiveauDAO();
                //idd a regler
                if(obj.delete(new Niveau(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText())))
                errorText.setText("Niveau supprimé !");
                else{
                    errorText.setText("Niveau non supprimé !");
                }
            }
            
            //Update all window
            updateAllWindowSecondary();
        
        
        }
        catch (Exception e1){
            errorText.setText("Error : " + (String)e1.getMessage() + " " + e1.getStackTrace());
            System.out.println("Error : " + (String)e1.getMessage() + " " + e1.getStackTrace());
            e1.getStackTrace();
        }
    }

    public void displayMenu(){
        
        String[] listTableName = { "Personne", "Inscription", "AnneeScolaire","Bulletin","Classe","DetailBulletin","Discipline","Enseignement","Evaluation","Niveau","Trimestre" };

        tablesBox = new JComboBox(listTableName);
        tablesBox.addActionListener(this);

        add(tablesBox, BorderLayout.PAGE_START);

        updateDisplayMenu((String)tablesBox.getSelectedItem(), panelPrincipal,0,0);
    }

    public void updateDisplayMenu(String nomCategorie, JPanel thePanel, int x, int y)  {
        thePanel.removeAll();
        thePanel.setBackground(Color.GRAY);
        tableEtudier = nomCategorie;
        
        ArrayList<Object> myArray = new ArrayList<Object>();
        ArrayList<JLabel> lea = new ArrayList<JLabel>();

        System.out.println("NOM BDD : " + nomBDD.getText());
        
                /////////////a modifier
        if(nomCategorie == "Personne"){
            DAO<Personne> pers = DAOFactory.getPersonneDAO();
            myArray = pers.retour();

            lea.add(new JLabel("ID"));
            lea.add(new JLabel("NOM"));
            lea.add(new JLabel("PRENOM"));
            lea.add(new JLabel("TYPE"));
        }
        else if(nomCategorie == "Inscription"){
            DAO<Inscription> pers = DAOFactory.getInscriptionDAO();
            myArray = pers.retour();
            lea.add(new JLabel("ID"));
            lea.add(new JLabel("CLASSE_ID"));
            lea.add(new JLabel("PERSONNE_ID"));
        }else if(nomCategorie == "AnneeScolaire"){
            DAO<AnneeScolaire> pers = DAOFactory.getAnneeScolaireDAO();
            myArray = pers.retour();
            lea.add(new JLabel("ANNEE"));
        }else if(nomCategorie == "Bulletin"){
            DAO<Bulletin> pers = DAOFactory.getBulletinDAO();
            myArray = pers.retour();
            lea.add(new JLabel("ID"));
            lea.add(new JLabel("APPRECIATION"));
            lea.add(new JLabel("TRIMESTRE_ID"));
            lea.add(new JLabel("INSCRIPTION_ID"));
        }else if(nomCategorie == "Classe"){
            DAO<Classe> pers = DAOFactory.getClasseDAO();
            myArray = pers.retour();
            lea.add(new JLabel("ID"));
            lea.add(new JLabel("NOM"));
            lea.add(new JLabel("NIVEAU_ID"));
            lea.add(new JLabel("ANNEE_ID"));
        }else if(nomCategorie == "DetailBulletin"){
            DAO<DetailBulletin> pers = DAOFactory.getDetailBulletinDAO();
            myArray = pers.retour();
            lea.add(new JLabel("ID"));
            lea.add(new JLabel("APPRECIATION"));
            lea.add(new JLabel("BULLETIN_ID"));
            lea.add(new JLabel("ENSEIGNEMENT_ID"));
        }else if(nomCategorie == "Discipline"){
            DAO<Discipline> pers = DAOFactory.getDisciplineDAO();
            myArray = pers.retour();
            lea.add(new JLabel("ID"));
            lea.add(new JLabel("NOM"));
        }else if(nomCategorie == "Enseignement"){
            DAO<Enseignement> pers = DAOFactory.getEnseignementDAO();
            myArray = pers.retour();
            lea.add(new JLabel("ID"));
            lea.add(new JLabel("CLASSE_ID"));
            lea.add(new JLabel("ENSEIGNANT_ID"));
            lea.add(new JLabel("DISCIPLINE_ID"));
        }else if(nomCategorie == "Evaluation"){
            DAO<Evaluation> pers = DAOFactory.getEvaluationDAO();
            myArray = pers.retour();
            lea.add(new JLabel("ID"));
            lea.add(new JLabel("APPRECIATION"));
            lea.add(new JLabel("NOTE"));
            lea.add(new JLabel("DETAIL_ID"));
        }else if(nomCategorie == "Niveau"){
            DAO<Niveau> pers = DAOFactory.getNiveauDAO();
            myArray = pers.retour();
            lea.add(new JLabel("ID"));
            lea.add(new JLabel("NOM"));
        }else if(nomCategorie == "Trimestre"){
            DAO<Trimestre> pers = DAOFactory.getTrimestreDAO();
            myArray = pers.retour();
            lea.add(new JLabel("ID"));
            lea.add(new JLabel("NUMERO"));
            lea.add(new JLabel("DEBUT"));
            lea.add(new JLabel("FIN"));
            lea.add(new JLabel("ANNEE_ID"));
        }

        GridBagConstraints d = new GridBagConstraints();

        d.gridy = x;
        d.gridx = y;
        d.weightx = 1;

        d.gridwidth = 1;

        for(int ines = 0; ines < lea.size(); ines ++){
            // lea.get(ines).setBackground(Color.white);
            //lea.get(ines).setBorder(BorderFactory.createLineBorder(Color.black));
            lea.get(ines).setForeground(Color.white);
            thePanel.add(lea.get(ines), d);
            d.gridx++;
        }

        d.fill = GridBagConstraints.HORIZONTAL;
        d.gridy = 2;

        for(int ines = 0; ines < myArray.size(); ines ++){
            d.gridy++;
            d.gridx = 0;

            ArrayList<JLabel> helene = new ArrayList<JLabel>();

                    /////////////a modifier
            if(nomCategorie == "Personne"){
                Personne nelly = (Personne)myArray.get(ines);
                helene.add(new JLabel(nelly.getId()));
                helene.add(new JLabel(nelly.getNom()));
                helene.add(new JLabel(nelly.getPrenom()));
                helene.add(new JLabel(nelly.getType()));
            }else if(nomCategorie == "Inscription"){
                Inscription nelly = (Inscription)myArray.get(ines);
                helene.add(new JLabel(nelly.getId()));
                helene.add(new JLabel(nelly.getClassID()));
                helene.add(new JLabel(nelly.getPersonneID()));
            }else if(nomCategorie == "AnneeScolaire"){
                AnneeScolaire nelly = (AnneeScolaire)myArray.get(ines);
                helene.add(new JLabel(nelly.getAnneeScolaireID()));
            }else if(nomCategorie == "Bulletin"){
                Bulletin nelly = (Bulletin)myArray.get(ines);
                helene.add(new JLabel(nelly.getId()));
                helene.add(new JLabel(nelly.getAppreciation()));
                 helene.add(new JLabel(nelly.getTrimestreID()));
                helene.add(new JLabel(nelly.getInscriptionID()));

            }else if(nomCategorie == "Classe"){
                Classe nelly = (Classe)myArray.get(ines);
                helene.add(new JLabel(nelly.getId()));
                helene.add(new JLabel(nelly.getNom()));
                helene.add(new JLabel(nelly.getNiveauID()));
                helene.add(new JLabel(nelly.getAnneescolaireID()));

            }else if(nomCategorie == "DetailBulletin"){
                DetailBulletin nelly = (DetailBulletin)myArray.get(ines);
                helene.add(new JLabel(nelly.getId()));
                 helene.add(new JLabel(nelly.getAppreciation()));
                helene.add(new JLabel(nelly.getBulletinID()));
                helene.add(new JLabel(nelly.getEnseignementID()));
            }else if(nomCategorie == "Discipline"){
                Discipline nelly = (Discipline)myArray.get(ines);
                helene.add(new JLabel(nelly.getId()));
                helene.add(new JLabel(nelly.getNom()));
            }else if(nomCategorie == "Enseignement"){
                Enseignement nelly = (Enseignement)myArray.get(ines);
                helene.add(new JLabel(nelly.getId()));
                helene.add(new JLabel(nelly.getClasseID()));
                helene.add(new JLabel(nelly.getEnseignantID()));
                helene.add(new JLabel(nelly.getDisciplineID()));

            }else if(nomCategorie == "Evaluation"){
                Evaluation nelly = (Evaluation)myArray.get(ines);
                helene.add(new JLabel(nelly.getId()));
                helene.add(new JLabel(nelly.getAppreciation()));
                helene.add(new JLabel(nelly.getNote()));
                helene.add(new JLabel(nelly.getDetailBulletinID()));

            }else if(nomCategorie == "Niveau"){
                Niveau nelly = (Niveau)myArray.get(ines);
                helene.add(new JLabel(nelly.getId()));
                helene.add(new JLabel(nelly.getNom()));
            }else if(nomCategorie == "Trimestre"){
                Trimestre nelly = (Trimestre)myArray.get(ines);
                helene.add(new JLabel(nelly.getId()));
                helene.add(new JLabel(nelly.getNum()));
                helene.add(new JLabel(nelly.getDebut()));
                helene.add(new JLabel(nelly.getFin()));
                helene.add(new JLabel(nelly.getAnneescolaireID()));
            }



            for(int adrien = 0; adrien < helene.size(); adrien ++){
                helene.get(adrien).setOpaque(true);
                helene.get(adrien).setBackground(Color.white);
                helene.get(adrien).setBorder(BorderFactory.createLineBorder(Color.black));
                thePanel.add(helene.get(adrien), d);
                d.gridx++;
            }
        }

        thePanel.updateUI();

    }

    public void modifMenu(){
                /////////////a modifier
        String[] listTableName = { "Personne", "Inscription", "AnneeScolaire","Bulletin","Classe","DetailBulletin","Discipline","Enseignement","Evaluation","Niveau","Trimestre"};

        tablesBoxModif = new JComboBox(listTableName);
        tablesBoxModif.addActionListener(this);

        panelPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints d = new GridBagConstraints();

        d.gridy = 0;
        d.gridx = 0;
        d.gridwidth = 2;
        panelPrincipal.add(tablesBoxModif, d);

        updateMenuModif((String)tablesBoxModif.getSelectedItem());
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

                /////////////a modifier
        if(table == "Personne"){
            arrayElement.add("id");
            arrayElement.add("nom");
            arrayElement.add("prenom");
            arrayElement.add("type");
        }else if("Inscription" == table){
            arrayElement.add("id");
            arrayElement.add("Classe");
            arrayElement.add("Personne");
        }else if("AnneeScolaire" == table){
            arrayElement.add("Année");
        } else if("Bulletin" == table){
             arrayElement.add("id");
            arrayElement.add("Appréciation");
            arrayElement.add("Trimestre ID");
            arrayElement.add("Trimestre ID");
        }else if("Classe" == table){
             arrayElement.add("id");
            arrayElement.add("Nom");
            arrayElement.add("Niveau ID");
            arrayElement.add("Annee scolaire ID");
        }else if("DetailBulletin" == table){
             arrayElement.add("id");
            arrayElement.add("Appreciation");
            arrayElement.add("Bulletin ID");
            arrayElement.add("Enseignemnt ID");
        }else if("Discipline" == table){
             arrayElement.add("id");
            arrayElement.add("Nom");
        }else if("Enseignement" == table){
             arrayElement.add("id");
            arrayElement.add("Classe ID");
            arrayElement.add("Enseignant ID");
            arrayElement.add("Discipline ID");
        }else if("Evaluation" == table){
             arrayElement.add("id");
            arrayElement.add("Appréciation");
            arrayElement.add("Note");
            arrayElement.add("DétailBulletin ID");
        }else if("Niveau" == table){
             arrayElement.add("id");
            arrayElement.add("Nom");
        }else if("Trimestre" == table){
             arrayElement.add("id");
            arrayElement.add("Numéro");
            arrayElement.add("Début");
            arrayElement.add("Fin");
            arrayElement.add("Année Scolaire ID");
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
        
        if(affichageSupp){
            
            MyWindow helene = new MyWindow(nomBDD.getText());
            helene.setSize(500,1000);
            helene.setVisible(true);
            helene.updateDisplayMenu(table, helene.panelPrincipal, 0, 0);
            
            mw.add(helene);
        }else{
            affichageSupp = true;
        }
                
        panelPrincipal.updateUI();
    }

    public void creationObjetRequetteModif(String table){
        //ici
        try{
                    /////////////a modifier
            if(table == "Personne"){
                DAO<Personne> pers = DAOFactory.getPersonneDAO();
                //idd a regler
                if(pers.update(new Personne(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText()))){
                    errorText.setText("Personne modifiée !");
                }else{
                    errorText.setText("Personne non modifiée !");
                }
            }
            else if(table == "Inscription"){
                DAO<Inscription> obj = DAOFactory.getInscriptionDAO();
                //idd a regler
                if(obj.update(new Inscription(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText(), arrayJTextField.get(2).getText()))){
                    errorText.setText("Inscription modifier !");
                }else{
                    errorText.setText("Inscription non modifiée !");
                }

            }
            else if(table == "AnneeScolaire"){
                DAO<AnneeScolaire> obj = DAOFactory.getAnneeScolaireDAO();
                //idd a regler
                if(obj.update(new AnneeScolaire(arrayJTextField.get(0).getText())))
                errorText.setText("AnneeScolaire modifiée !");
                else{
                    errorText.setText("AnneeScolaire non modifiée !");
                }

            }else if(table == "Bulletin"){
                DAO<Bulletin> obj = DAOFactory.getBulletinDAO();
                //idd a regler
               if( obj.update(new Bulletin(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText())))
               errorText.setText("Bulletin modifié !");
                else{
                    errorText.setText("Bulletin non modifié !");
                }
            }
            else if(table == "Classe"){
                DAO<Classe> obj = DAOFactory.getClasseDAO();
                //idd a regler
                if(obj.update(new Classe(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText())))
                errorText.setText("Classe modifiée!");
                else{
                    errorText.setText("Classe non modifiée !");
                }
            }else if(table == "DetailBulletin"){
                DAO<DetailBulletin> obj = DAOFactory.getDetailBulletinDAO();
                //idd a regler
               if( obj.update(new DetailBulletin(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText())))
                errorText.setText("DetailBulletin modifié !");
                else{
                    errorText.setText("DetailBulletin non modifié !");
                }
            }else if(table == "Discipline"){
                DAO<Discipline> obj = DAOFactory.getDisciplineDAO();
                //idd a regler
                if(obj.update(new Discipline(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText())))
               errorText.setText("Discipline modifiée !");
                else{
                    errorText.setText("Discipline non modifiée !");
                }
            }else if(table == "Enseignement"){
                DAO<Enseignement> obj = DAOFactory.getEnseignementDAO();
                //idd a regler
                if(obj.update(new Enseignement(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText())))
                errorText.setText("Enseignement modifié !");
                else{
                    errorText.setText("Enseignement non modifié !");
                }
            }else if(table == "Evaluation"){
                DAO<Evaluation> obj = DAOFactory.getEvaluationDAO();
                //idd a regler
                if(obj.update(new Evaluation(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText())))
                errorText.setText("Evaluation modifiée !");
                else{
                    errorText.setText("Evaluation non modifiée !");
                }
            }else if(table == "Trimestre"){
                DAO<Trimestre> obj = DAOFactory.getTrimestreDAO();
                //idd a regler
                if(obj.update(new Trimestre(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText(),arrayJTextField.get(4).getText())))
                errorText.setText("Trimestre modifié !");
                else{
                    errorText.setText("Trimestre non modifié !");
                }
            }else if(table == "Niveau"){
                DAO<Niveau> obj = DAOFactory.getNiveauDAO();
                //idd a regler
                if(obj.update(new Niveau(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText())))
                errorText.setText("Niveau modifié !");
                else{
                    errorText.setText("Niveau non modifié !");
                }
            }
            
            updateAllWindowSecondary();
        }
        catch (Exception e1){
            errorText.setText("Error : " + (String)e1.getMessage());
        }
    }
    
    public void updateAllWindowSecondary(){
        for(MyWindow nelly : mw){
             nelly.updateDisplayMenu(nelly.tableEtudier, nelly.panelPrincipal, 0, 0);
        }
    }
    
    public void delAllWindowSecondary(){
        for(MyWindow nelly : mw){
            nelly.setVisible(false); //you can't see me!
            nelly.dispose(); //Destroy the JFrame object 
        }
        
        mw.clear();
    }
}
