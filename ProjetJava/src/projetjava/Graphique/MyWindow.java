package projetjava.Graphique;

/**
 *
 * @author quentin
 * 
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

/**
 * Class MyWindow
 * Cette classe est la classe qui genere une fenetre pour le projet
 * Elle est construit de maniere a pouvoir etre utiliser comme la fenetre
 * principal ou secondaire.
 */
public class MyWindow extends JFrame implements ActionListener {

    //boolean pour savoir si on vient d arriver sur la fenetre ou action realiser
    boolean affichageSupp;
    //Les differents bouton 
    JButton button1, button2,buttonConnexionBDD, addMenu, delMenu, dispMenu, modifMenu, menu, addElement, delElement, modifElement,session,connexionSession;
    //Label pour les information a afficher
    JLabel label1, label2, label3, label4, errorText,info;
    //Les deux panel de la fenetre, le premier pour les boutons du bas de la fenetre et le panelPrincipal ou tout est afficher dessus
    JPanel panelForButtons, panelPrincipal;
    //Lieu ou l'utilisateur peut rentrer des informations
    JTextField idBDD, pswBDD, idSession;
    //nom de la bdd en static pour que tout les classes ai accee
    public static JTextField nomBDD;
    //Combo box sont les menus déroulant pour les differentes table avec des action lie
    JComboBox tablesBox, tablesBoxAdd, tablesBoxDel, tablesBoxModif;
    //ArrayList de toutes les fenetre secondaire
    ArrayList<MyWindow> mw;
    //Pour les fenetres secondaire on stock le nom de la table a afficher ici
    String tableEtudier;
    //Classe pour se connecter a la BDD
    Connexion myBDD;

    //Lors des differents affichage, on peut avoir des Array de Jlabel qu on pourait update/enlever facilement si on les stocks
    ArrayList<JLabel> arrayJLabel;
    //Pour optimisation des methode de recuperation
    ArrayList<JTextField> arrayJTextField;
    
    /**
 * Constructeur d'une frame
 * @param nom nom de la table a afficher par defaut si c est une fenetre secondaire
 * On initialise tout les attribue graphique qui seront utiliser dans la fenetre
 * afin de pouvoir les ajouter ou retirer facilement
 */
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
        session = new JButton("Session");
        
        connexionSession = new JButton("Ouvrir ma session");
                
        idBDD = new JTextField();
        pswBDD = new JTextField();
        nomBDD = new JTextField();
        idSession = new JTextField();
        
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
        session.addActionListener(this);
        
        connexionSession.addActionListener(this);


        mw = new ArrayList<MyWindow>();
        tableEtudier = new String();

        panelForButtons=new JPanel();
        panelForButtons.add(button1);

        panelPrincipal=new JPanel();

        add(panelPrincipal, BorderLayout.CENTER);
	add(panelForButtons, BorderLayout.SOUTH);

        //Si c est une fenetre secondaire
        if(!nom.equals("")){
            nomBDD.setText(nom);
        }
        
        //Lance le chargement du menu principal
        updatePannelPrincipal(0);
    }
/**
 * Listener des action de la frame
 * @param e ActionEvent
 * Listener de tout les bouton ou JComboBox de la fenetre
 */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Boutton quiter
        if(e.getSource()==button1) {
            delAllWindowSecondary();
            setVisible(false); //you can't see me!
            dispose(); //Destroy the JFrame object
        }
        //Bouton deconexion
        else if(e.getSource()==button2) {             
            delAllWindowSecondary();         
            updatePannelPrincipal(0);
        }
        //Bouton menu principal
        else if(e.getSource()==menu) {
            delAllWindowSecondary();
            updatePannelPrincipal(1);
        }
        //Boutton de connexion a la BDD
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

        //Boutton pour rejoindre l'ajout d element
        }else if(e.getSource()==addMenu) {
            updatePannelPrincipal(2);
        }
        //Boutton pour rejoindre la suppression d'éléments
        else if(e.getSource()==delMenu) {
            updatePannelPrincipal(3);
        //Boutton pour rejoindre l'affichage des elements
        }else if(e.getSource()==dispMenu) {
            updatePannelPrincipal(4);
        //Boutton pour rejoindre la modification des éléments
        }else if(e.getSource()==modifMenu) {
            updatePannelPrincipal(5);
        //Boutton pour rejoindre la session des stats
        }else if(e.getSource()==session) {
            updatePannelPrincipal(6);
        }
        
        //Menu deroulant dans le menu d affichage
        else if(e.getSource()==tablesBox) {
            System.out.println((String)tablesBox.getSelectedItem());
            updateDisplayMenu((String)tablesBox.getSelectedItem(), panelPrincipal, 0,0);
        //Menu deroulant dans le menu d ajout
        }else if(e.getSource()==tablesBoxAdd) {
            updateMenuAjout((String)tablesBoxAdd.getSelectedItem());
        //Boutton pour ajouter un element
        }else if(e.getSource()==addElement){
            creationObjetRequetteAjout((String)tablesBoxAdd.getSelectedItem());
        //Menu deroulant dans le menu de suppression
        }else if(e.getSource()==tablesBoxDel){
            updateMenuDel((String)tablesBoxDel.getSelectedItem());
        //Boutton pour supprimer un element
        }else if(e.getSource()==delElement){
            creationObjetRequetteDel((String)tablesBoxDel.getSelectedItem());
        //Menu deroulant dans le menu de modification
        }else if(e.getSource()==tablesBoxModif){
            updateMenuModif((String)tablesBoxModif.getSelectedItem());
        //Boutton pour modifier un element
        }else if(e.getSource()==modifElement){
            creationObjetRequetteModif((String)tablesBoxModif.getSelectedItem());
        }
        //Boutton pour se connecter a la sessions
        else if(e.getSource()==connexionSession){
            ouvertureSession();
        }
    }

    // 0 : menu connexion
    // 1 : menu principal
    // 2 : menu add
    // 3 : menu del
    // 4 : menu disp
    // 5 : modif
    // 6 : session
    // 7 : affichage de la session
    
    /**
 * Met a jour le pannelPrincipal en fonction du menu souhaiter
 * @param option numero du menu a afficher
 * Va mettre a jour le panelPrincipal en fonction du menu souhaiter
 */
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
                info = new JLabel("");


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
                
                d.gridy = 4;
                panelPrincipal.add(session, d);

                errorText.setText("");
                panelPrincipal.setBackground(Color.GRAY);
                
                DAO<Bulletin> obj = DAOFactory.getBulletinDAO();
                DAO<Personne> p = DAOFactory.getPersonneDAO();
             
                
                
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
            case 6:
                panelPrincipal.removeAll();
                connexionSession();
                break;
        }

        panelPrincipal.updateUI();
    }
    
    /**
 * Menu de connexion a la sessios
 * Menu pour rentrer son ID et avoir des statistiques en fonction de son ID
 */
    public void connexionSession(){
        GridBagConstraints d = new GridBagConstraints();

        d.gridy = 0;
        d.gridx = 0;
        
        d.gridy = 0;
        d.gridx = 0;
        d.gridwidth =11;
        JLabel id = new JLabel("id de connexion: ");
        panelPrincipal.add(id, d);
        
        
        d.gridy++;
        d.gridx++;
        panelPrincipal.add(connexionSession, d);
   
        
        idSession.setColumns(10);
        panelPrincipal.add(idSession);
        
        
    }
    
    /**
 * Lance la requette d ajout d un element a la BDD
 * @param table nom de la table ou il faut ajouter l element
 */
    public void creationObjetRequetteAjout(String table){
        //ici
        boolean ines = false;
        try{
            //En fonction de la table, appelle la bonne requette
           
            if(table == "Personne"){
                //Creer un objet de la table en DAO
                DAO<Personne> pers = DAOFactory.getPersonneDAO();
                //Lance la requette a la bdd
                ines = pers.create(new Personne(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),0 ));
                //anonce que la requette a ete envoye
                errorText.setText("Personne ajoute !");
            }else if(table == "Inscription"){
                DAO<Inscription> obj = DAOFactory.getInscriptionDAO();
                ines = obj.create(new Inscription(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText()));
                errorText.setText("Inscription ajoute !");
            }else if(table == "AnneeScolaire"){
                DAO<AnneeScolaire> obj = DAOFactory.getAnneeScolaireDAO();
                ines = obj.create(new AnneeScolaire(arrayJTextField.get(0).getText()));
                errorText.setText("Annee scolaire ajoute !");
            }else if(table == "Bulletin"){
                DAO<Bulletin> obj = DAOFactory.getBulletinDAO();
                ines = obj.create(new Bulletin(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText()));
                errorText.setText(" Bulletin ajoute !");
            }
            else if(table == "Classe"){
                DAO<Classe> obj = DAOFactory.getClasseDAO();
                ines = obj.create(new Classe(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText()));
                errorText.setText(" Classe ajoute !");
            }else if(table == "DetailBulletin"){
                DAO<DetailBulletin> obj = DAOFactory.getDetailBulletinDAO();
                ines = obj.create(new DetailBulletin(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText()));
                errorText.setText(" DetailBulletin ajoute !");
            }else if(table == "Discipline"){
                DAO<Discipline> obj = DAOFactory.getDisciplineDAO();
                ines = obj.create(new Discipline(arrayJTextField.get(0).getText()));
                errorText.setText(" Discipline ajoute !");
            }else if(table == "Enseignement"){
                DAO<Enseignement> obj = DAOFactory.getEnseignementDAO();
                ines = obj.create(new Enseignement(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText()));
                errorText.setText(" Enseignement ajoute !");
            }else if(table == "Evaluation"){
                DAO<Evaluation> obj = DAOFactory.getEvaluationDAO();
                ines = obj.create(new Evaluation(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText()));
                errorText.setText(" Evaluation ajoute !");
            }else if(table == "Trimestre"){
                DAO<Trimestre> obj = DAOFactory.getTrimestreDAO();
                ines = obj.create(new Trimestre(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText()));
                errorText.setText(" Trimestre ajoute !");
            }else if(table == "Niveau"){
                DAO<Niveau> obj = DAOFactory.getNiveauDAO();
                ines = obj.create(new Niveau(arrayJTextField.get(0).getText()));
                errorText.setText(" Niveau ajoute !");
            }
            //Si la requette est incorect et n a pas ete lance 
            if(!ines){
                errorText.setText("Error : Requette non envoye");
            }

        }
        catch (Exception e1){
            errorText.setText("Error : " + (String)e1.getMessage());
        }
    }

    /**
 * Initialise le menu pour ajouter un element a la BDD
 * Prepare le menu deroulant pour toutes les tables ou on peut ajouter des elements
 */
    public void menuAjout(){
        //Liste de toute les tables à mettre dans la box depliant
        String[] listTableName = { "Personne", "Inscription", "AnneeScolaire","Bulletin","Classe","DetailBulletin","Discipline","Enseignement","Evaluation","Niveau","Trimestre"};

        tablesBoxAdd = new JComboBox(listTableName);
        tablesBoxAdd.addActionListener(this);

        panelPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints d = new GridBagConstraints();

        d.gridy = 0;
        d.gridx = 0;
        d.gridwidth =11;
        panelPrincipal.add(tablesBoxAdd, d);

        //Lance l'update pour afficher les differents champs pour ajouter l element
        updateMenuAjout((String)tablesBoxAdd.getSelectedItem());
    }
/**
 * Prepare les champrs pour rentrer les information pour ajout
 * @param ines nom de la table ou on veut ajouter l element
 * Va generer tout les JTextField et le bouton ajouter sous le menu deroulant de la selection de table
 */
    public void updateMenuAjout(String ines){

        //on enleve les ancien composant qui etait present
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


        //On creer les element pour la generation des champs en fonction de l'ArrayList
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
        //Ajout du menu déroulant de table
        panelPrincipal.add(tablesBoxAdd, d);

        d.gridwidth = 1;
        
        //Pour chaque element dans notre array list on va ajouter son nom et un JTextField
        for(int i = 0; i < arrayElement.size(); i++){
            //au moins on a fait un peu de code modulaire ^^'
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
        //on oublie pas le bouton d ajout d element
        panelPrincipal.add(addElement, d);
        d.gridy++;
        panelPrincipal.add(errorText,d);

        //on rafraichis tout ça
        panelPrincipal.updateUI();
    }

    /**
 * Chargement du menu supprimer
 * On charge le debut du menu de suppression d element
 */
    public void delMenu(){
        //Bizzarement ça ressemble a ce qu on a fait dans supprimer, et ba c est parce que c est la meme chose mais voila O:-)
        String[] listTableName = { "Personne", "Inscription", "AnneeScolaire","Bulletin","Classe","DetailBulletin","Discipline","Enseignement","Evaluation","Niveau","Trimestre"};

        //Ajout du menu deroulant de toutes les tables avec 
        tablesBoxDel = new JComboBox(listTableName);
        tablesBoxDel.addActionListener(this);

        panelPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints d = new GridBagConstraints();

        d.gridy = 0;
        d.gridx = 0;
        d.gridwidth = 11;
        panelPrincipal.add(tablesBoxDel, d);
        
        //On met a jour le menu de suppression avec les champs
        updateMenuDel((String)tablesBoxDel.getSelectedItem());

    }

    /**
 * Mise a jour du menu de suppresion
 * @param table nom de la table ou l on veut supprimer un element
 */
    public void updateMenuDel(String table){
        
        //On retire tout les ancien element du panelPrincipal
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

        //On refait comme dans l ajout, une liste d element
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

        //On ajoute ces element au array de JLabel et des JTextField
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

        //Ajoute tous les elements graphiqeu 
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
 
        //Si on est sur une action et que la fenetre secondaire n'a pas encore ete cree
        if(affichageSupp && !(checkForWindowSecondary(table))){
            //On creer la fenetre secondaire
            MyWindow helene = new MyWindow(nomBDD.getText());
            helene.setSize(500,1000);
            helene.setVisible(true);
            helene.updateDisplayMenu(table, helene.panelPrincipal, 0, 0);
            
            mw.add(helene);
        }else{
            //Ca signifie qu on etait sur l initialisation du menu donc la prochaine action
            // qui va declancher l update aura ete un choix de table donc il faudra 
            //generer la creation d une fenetre secondaire
            affichageSupp = true;
        }
        
        
        panelPrincipal.updateUI();

    }

    /**
 * cCreation de la requette sql pour la modification d un element
 * @param table nom de la table a modifier
 * Va lancer la demande de requette de modification des champs remplis
 */
    public void creationObjetRequetteDel(String table){
        try{
            if(table == "Personne"){
                DAO<Personne> pers = DAOFactory.getPersonneDAO();
                   ArrayList<String> dd = new ArrayList<>();
                //Si la requette a bien ete envoye
                if(pers.delete(new Personne(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText()))){
                    errorText.setText("Personne supprimée !");
                }else{
                    errorText.setText("Personne pas supprimée !");
                }
            }
            else if(table == "Inscription"){
                DAO<Inscription> obj = DAOFactory.getInscriptionDAO();
                if(obj.delete(new Inscription(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText()))){
                    errorText.setText("Inscription supprimée !");
                }else{
                    errorText.setText("Inscription non supprimée !");
                }
            }
            else if(table == "AnneeScolaire"){
                DAO<AnneeScolaire> obj = DAOFactory.getAnneeScolaireDAO();
                if(obj.delete(new AnneeScolaire(arrayJTextField.get(0).getText())))
                errorText.setText("AnneeScolaire supprimée !");
                else{
                    errorText.setText("AnneeScolaire non supprimée !");
                }
            }else if(table == "Bulletin"){
                DAO<Bulletin> obj = DAOFactory.getBulletinDAO();
               if( obj.delete(new Bulletin(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText())))
               errorText.setText("Bulletin supprimé !");
                else{
                    errorText.setText("Bulletin non supprimé !");
                }
            }
            else if(table == "Classe"){
                DAO<Classe> obj = DAOFactory.getClasseDAO();
                if(obj.delete(new Classe(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText())))
                errorText.setText("Classe supprimée!");
                else{
                    errorText.setText("Classe non supprimée !");
                }
            }else if(table == "DetailBulletin"){
                DAO<DetailBulletin> obj = DAOFactory.getDetailBulletinDAO();
               if( obj.delete(new DetailBulletin(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText())))
                errorText.setText("DetailBulletin supprimé !");
                else{
                    errorText.setText("DetailBulletin non supprimé !");
                }
            }else if(table == "Discipline"){
                DAO<Discipline> obj = DAOFactory.getDisciplineDAO();
                if(obj.delete(new Discipline(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText())))
               errorText.setText("Discipline supprimée !");
                else{
                    errorText.setText("Discipline non supprimée !");
                }
            }else if(table == "Enseignement"){
                DAO<Enseignement> obj = DAOFactory.getEnseignementDAO();
                if(obj.delete(new Enseignement(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText())))
                errorText.setText("Enseignement supprimé !");
                else{
                    errorText.setText("Enseignement non supprimé !");
                }
            }else if(table == "Evaluation"){
                DAO<Evaluation> obj = DAOFactory.getEvaluationDAO();
                if(obj.delete(new Evaluation(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText())))
                errorText.setText("Evaluation supprimée !");
                else{
                    errorText.setText("Evaluation non supprimée !");
                }
            }else if(table == "Trimestre"){
                DAO<Trimestre> obj = DAOFactory.getTrimestreDAO();
                if(obj.delete(new Trimestre(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText(),arrayJTextField.get(4).getText())))
                errorText.setText("Trimestre supprimé !");
                else{
                    errorText.setText("Trimestre non supprimé !");
                }
            }
            else if(table == "Niveau"){
                DAO<Niveau> obj = DAOFactory.getNiveauDAO();
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
        }
    }

    /**
 * Charge le menu qui affiche les tables
 * Charge le menu qui va afficher le contenue de la table choisis dans le menu deroulant
 */
    public void displayMenu(){
        
        String[] listTableName = { "Personne", "Inscription", "AnneeScolaire","Bulletin","Classe","DetailBulletin","Discipline","Enseignement","Evaluation","Niveau","Trimestre" };

        tablesBox = new JComboBox(listTableName);
        tablesBox.addActionListener(this);

        add(tablesBox, BorderLayout.PAGE_START);

        updateDisplayMenu((String)tablesBox.getSelectedItem(), panelPrincipal,0,0);
    }

    /**
 * Met a jour l affichage des information de la table donne dans le panel donnee
 * @param nomCategorie nom de la table a afficher le contenue
 * @param thePanel panel ou la table dois etre afficher (generalement panelPrincipal)
 * @param x position en x du debut de l affichage du tableau 
 * @param y position en y du debut de l affichage du tableau

 */
    public void updateDisplayMenu(String nomCategorie, JPanel thePanel, int x, int y)  {
        thePanel.removeAll();
        thePanel.setBackground(Color.GRAY);
        tableEtudier = nomCategorie;
        
        ArrayList<Object> myArray = new ArrayList<Object>();
        ArrayList<JLabel> lea = new ArrayList<JLabel>();

        System.out.println("NOM BDD : " + nomBDD.getText());
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
            lea.get(ines).setForeground(Color.white);
            thePanel.add(lea.get(ines), d);
            d.gridx++;
        }

        d.fill = GridBagConstraints.HORIZONTAL;
        d.gridy = 2;

        //On repete pour chaque ligne
        for(int ines = 0; ines < myArray.size(); ines ++){
            d.gridy++;
            d.gridx = 0;

            ArrayList<JLabel> helene = new ArrayList<JLabel>();
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

            //On ajoute tout les label à la ligne
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

/**
 * Menu de modification des champs
 */
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

 /**
 * Mise a jour du menu de modification
 * @param table nom de la table à modifier
 */
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
        
        if(affichageSupp && !(checkForWindowSecondary(table))){
            
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

       /**
 * Creation de la requette pour modifier un element d une table
 * @param table nom de la table a modifier
 */
    public void creationObjetRequetteModif(String table){
        try{
            if(table == "Personne"){
                DAO<Personne> pers = DAOFactory.getPersonneDAO();
                if(pers.update(new Personne(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText()))){
                    errorText.setText("Personne modifiée !");
                }else{
                    errorText.setText("Personne non modifiée !");
                }
            }
            else if(table == "Inscription"){
                DAO<Inscription> obj = DAOFactory.getInscriptionDAO();
                if(obj.update(new Inscription(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText(), arrayJTextField.get(2).getText()))){
                    errorText.setText("Inscription modifier !");
                }else{
                    errorText.setText("Inscription non modifiée !");
                }
            }
            else if(table == "AnneeScolaire"){
                DAO<AnneeScolaire> obj = DAOFactory.getAnneeScolaireDAO();
                if(obj.update(new AnneeScolaire(arrayJTextField.get(0).getText())))
                errorText.setText("AnneeScolaire modifiée !");
                else{
                    errorText.setText("AnneeScolaire non modifiée !");
                }
            }else if(table == "Bulletin"){
                DAO<Bulletin> obj = DAOFactory.getBulletinDAO();
               if( obj.update(new Bulletin(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText())))
               errorText.setText("Bulletin modifié !");
                else{
                    errorText.setText("Bulletin non modifié !");
                }
            }
            else if(table == "Classe"){
                DAO<Classe> obj = DAOFactory.getClasseDAO();
                if(obj.update(new Classe(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText())))
                errorText.setText("Classe modifiée!");
                else{
                    errorText.setText("Classe non modifiée !");
                }
            }else if(table == "DetailBulletin"){
                DAO<DetailBulletin> obj = DAOFactory.getDetailBulletinDAO();
               if( obj.update(new DetailBulletin(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText())))
                errorText.setText("DetailBulletin modifié !");
                else{
                    errorText.setText("DetailBulletin non modifié !");
                }
            }else if(table == "Discipline"){
                DAO<Discipline> obj = DAOFactory.getDisciplineDAO();
                if(obj.update(new Discipline(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText())))
               errorText.setText("Discipline modifiée !");
                else{
                    errorText.setText("Discipline non modifiée !");
                }
            }else if(table == "Enseignement"){
                DAO<Enseignement> obj = DAOFactory.getEnseignementDAO();
                if(obj.update(new Enseignement(arrayJTextField.get(0).getText(), arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText())))
                errorText.setText("Enseignement modifié !");
                else{
                    errorText.setText("Enseignement non modifié !");
                }
            }else if(table == "Evaluation"){
                DAO<Evaluation> obj = DAOFactory.getEvaluationDAO();
                if(obj.update(new Evaluation(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText())))
                errorText.setText("Evaluation modifiée !");
                else{
                    errorText.setText("Evaluation non modifiée !");
                }
            }else if(table == "Trimestre"){
                DAO<Trimestre> obj = DAOFactory.getTrimestreDAO();
                if(obj.update(new Trimestre(arrayJTextField.get(0).getText(),arrayJTextField.get(1).getText(),arrayJTextField.get(2).getText(),arrayJTextField.get(3).getText(),arrayJTextField.get(4).getText())))
                errorText.setText("Trimestre modifié !");
                else{
                    errorText.setText("Trimestre non modifié !");
                }
            }else if(table == "Niveau"){
                DAO<Niveau> obj = DAOFactory.getNiveauDAO();
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
    
 /**
 * Mise a jour de l affichage de toutes les fenetres secondaire
 */
    public void updateAllWindowSecondary(){
        for(MyWindow nelly : mw){
             nelly.updateDisplayMenu(nelly.tableEtudier, nelly.panelPrincipal, 0, 0);
        }
    }
 /**
 * Supprime toute les fenetres secondaire
 */
    public void delAllWindowSecondary(){
        for(MyWindow nelly : mw){
            nelly.setVisible(false); //you can't see me!
            nelly.dispose(); //Destroy the JFrame object 
        }
        
        mw.clear();
    }
    
 /**
 * Verifie si la table secondaire a deja ete cree
 * @param nomTable nom de la table de la fenetre secondaire qu on veut verifier l existance
 * @return True si la table existe, False sinon
 */
    public boolean checkForWindowSecondary(String nomTable){
        
        for(MyWindow nelly  : mw){
            if(nomTable.equals(nelly.tableEtudier)){
                return true;
            }
        }
        
        return false;
    }

    
/**
 * Methode qui permet d 'identifier la bonne personnequi a été ajouté
 */
    public void ouvertureSession() {
       idSession.getText();
        DAO<Personne> pers = DAOFactory.getPersonneDAO();
        
        ArrayList<Object> myArray = new ArrayList();
       
        myArray = pers.retour();
             
        GridBagConstraints d = new GridBagConstraints();

        d.gridy = 0;
        d.gridx = 0;
        
        for (int i=0;i<myArray.size();i++) {
            Personne p = (Personne)myArray.get(i);
            p.getId();
           
            if(p.getId().equals(idSession.getText())){
              updateSession(p.getId(),pers,p);
            }
        }
    }
    
    
/**
 * Methode qui met à jour ouvertureSession pour afficher les informations de la personne selectionnées. 
 */
     public void updateSession(String id, DAO pers,Personne p){
        
        panelPrincipal.removeAll();
        
        GridBagConstraints d = new GridBagConstraints();
        
        d.gridy = 0;
        d.gridx = 0;
        
        Personne nelly = (Personne) pers.find(p.getId());
        
        info.setText("moyenne de : "+nelly.getPrenom()+" "+nelly.getNom()+" est de "+nelly.getMoyenne());
        
        panelPrincipal.add(info, d);
        panelPrincipal.updateUI();
    }
}