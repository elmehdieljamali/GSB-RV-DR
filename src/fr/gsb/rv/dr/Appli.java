/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fr.gsb.rv.dr;

import fr.gsb.rv.dr.entites.Praticien;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.Session;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.technique.PanneauAccueil;
import fr.gsb.rv.dr.technique.PanneauPraticiens;
import fr.gsb.rv.dr.technique.PanneauRapports;
import fr.gsb.rv.dr.technique.VueConnexion;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefConfiance;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefNotoriete;
import fr.gsb.rv.dr.utilitaires.ComparateurDateVisite;
import java.util.Collections;
import java.util.List;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import javafx.util.Pair;

/**
 *
 * @author debian
 */
public class Appli extends Application {

    boolean session = false ;
    // Visiteur v1 ;
    Visiteur visiteur ;
    
    PanneauAccueil vueAccueil  ;
    PanneauRapports vueRapports ;
    PanneauPraticiens vuePraticiens ;
    
    MenuBar barreMenus = new MenuBar() ;
        
    Menu menuFichier = new Menu( "Fichier" ) ;
    MenuItem itemSeConnecter = new MenuItem( "Se connecter" ) ;
    MenuItem itemSeDeconnecter = new MenuItem( "Se déconnecter" ) ;
    MenuItem itemQuitter = new MenuItem( "Quitter" ) ;
        
    Menu menuRapports = new Menu( "Rapports" ) ;
    MenuItem itemConsulter = new MenuItem( "Consulter" ) ;
    Menu menuPraticiens = new Menu( "Praticiens" ) ;
    MenuItem itemHesitants = new MenuItem( "Hesitants" ) ;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ConnexionException {
        // TODO code application logic here
        launch(args);
        
        
        /*try {
            ConnexionBD.getConnexion();
            System.out.println("dsfdsffds");
        }
        catch( Exception e ) {
            System.out.println(e);
        }*/
        
        
        /*
        try {
            ModeleGsbRv.seConnecter("a131", "azerty") ;
        }
        catch( Exception e ) {
            System.out.println(e);
        }
        */
        
        List<Praticien> praticiens = ModeleGsbRv.getPraticiensHesitants() ;
        
        /* for( Praticien unPraticien : praticiens ){
            System.out.println(unPraticien);
        }
        
        Collections.sort( praticiens , new ComparateurCoefConfiance() ) ;
        
        for( Praticien unPraticien : praticiens ){
            System.out.println(unPraticien);
        }
        
        Collections.sort( praticiens , new ComparateurCoefNotoriete().reversed() ) ;
        
        for( Praticien unPraticien : praticiens ){
            System.out.println(unPraticien);
        } */
        
        Collections.sort( praticiens , new ComparateurDateVisite().reversed() ) ;
        
        for( Praticien unPraticien : praticiens ){
            System.out.println(unPraticien);
        } 
    }
    
    public void etatSession() {
        if ( session == false ) {
            itemSeDeconnecter.setDisable(true);
            menuRapports.setDisable(true);
            menuPraticiens.setDisable(true);
            itemSeConnecter.setDisable(false);
        }
        else {
            itemSeConnecter.setDisable(true);
            itemSeDeconnecter.setDisable(false);
            menuRapports.setDisable(false);
            menuPraticiens.setDisable(false);
        }
    }
    
    @Override
    public void start(Stage primaryStage) {  
        itemQuitter.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        
        menuFichier.getItems().add( itemSeConnecter ) ;
        menuFichier.getItems().add( itemSeDeconnecter ) ;
        menuFichier.getItems().add( itemQuitter ) ;
        barreMenus.getMenus().add( menuFichier ) ;
        menuRapports.getItems().add( itemConsulter ) ;
        barreMenus.getMenus().add( menuRapports ) ;
        menuPraticiens.getItems().add( itemHesitants ) ;
        barreMenus.getMenus().add( menuPraticiens ) ;
        
        
        itemQuitter.setOnAction( actionEvent -> {
                Alert alertQuitter = new Alert( Alert.AlertType.CONFIRMATION );
                alertQuitter.setTitle("Quitter");
                alertQuitter.setHeaderText("Demande de confirmation");
                alertQuitter.setContentText("Voulez-vous quitter l'application ?");
                ButtonType btnOui = new ButtonType("Oui" , ButtonData.YES);
                ButtonType btnNon = new ButtonType("Non" , ButtonData.NO);
                alertQuitter.getButtonTypes().setAll( btnOui , btnNon ) ;
                Optional<ButtonType> reponse = alertQuitter.showAndWait() ;
                if ( reponse.get() == btnOui ) {
                    Platform.exit() ;
                }
            }
        );
        
        itemSeConnecter.setOnAction( actionEvent -> {
                /*
                v1 = new Visiteur ("OB001", "BOUAICHI", "Oumayma") ;
                Session.ouvrir(v1) ;
                primaryStage.setTitle(Session.getSession().getLeVisiteur().getNom() + Session.getSession().getLeVisiteur().getPrenom()) ;
                */
                VueConnexion vue = new VueConnexion() ;
                Optional<Pair<String, String>> reponse = vue.showAndWait() ;
                if( reponse.isPresent() ){
                    try {
                        visiteur = ModeleGsbRv.seConnecter(reponse.get().getKey()  , reponse.get().getValue()) ;
                        if( visiteur != null ) {
                            session = true ;
                            etatSession();
                            primaryStage.setTitle(visiteur.getNom() + " " + visiteur.getPrenom()) ;
                        }
                        else{
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Erreur");
                            alert.setHeaderText("Echec de l'authentification");
                            alert.setContentText("Matricule et/ou mot de passe incoorrect. Réessayer");

                            alert.showAndWait();
                        }
                    }
                    catch( Exception e ) {
                        System.out.println(e);
                    }
                }
            }
        );
        
        itemSeDeconnecter.setOnAction( actionEvent -> {
                session = false ;
                etatSession();
                primaryStage.setTitle("GSB-RV-DR");
                vueAccueil.toFront();
            }
        );
        
        itemConsulter.setOnAction( actionEvent -> {
                // System.out.println(itemConsulter);
                vueRapports.toFront();
                // System.out.println("[Rapports]" + Session.getSession().getLeVisiteur().getPrenom() + " " + Session.getSession().getLeVisiteur().getNom());
            }
        );
        
        itemHesitants.setOnAction( actionEvent -> {
                // System.out.println(itemHesitants);
                vuePraticiens.toFront();
                // System.out.println("[Praticiens]" + Session.getSession().getLeVisiteur().getPrenom() + " " + Session.getSession().getLeVisiteur().getNom());
            }
        );
        
        if ( session == false ) {
            itemSeDeconnecter.setDisable(true);
            menuRapports.setDisable(true);
            menuPraticiens.setDisable(true);
        }
        else {
            itemSeConnecter.setDisable(true);
        }
        
        BorderPane root = new BorderPane() ;
        root.setTop( barreMenus ) ;
        
        StackPane stackPane = new StackPane() ;
        vueAccueil = new PanneauAccueil() ;
        vueRapports = new PanneauRapports() ;
        vuePraticiens = new PanneauPraticiens() ;
        stackPane.getChildren().add(vuePraticiens);
        stackPane.getChildren().add(vueRapports);
        stackPane.getChildren().add(vueAccueil);
        vueAccueil.toFront();
        
        root.setCenter(stackPane);
        
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("GSB-RV-DR");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}