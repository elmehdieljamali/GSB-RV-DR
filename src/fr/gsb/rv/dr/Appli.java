/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fr.gsb.rv.dr;

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

/**
 *
 * @author debian
 */
public class Appli extends Application {

    boolean session = false ;
    Visiteur v1 ;
    
    
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
    public static void main(String[] args) {
        // TODO code application logic here
        // launch(args);
        
        /*
        try {
            ConnexionBD.getConnexion();
        }
        catch( Exception e ) {
            System.out.println(e);
        }
        */
        
        /*
        try {
            ModeleGsbRv.seConnecter("a131", "azerty") ;
        }
        catch( Exception e ) {
        }
        */
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
                session = true ;
                etatSession();
                /*
                v1 = new Visiteur ("OB001", "BOUAICHI", "Oumayma") ;
                Session.ouvrir(v1) ;
                primaryStage.setTitle(Session.getSession().getLeVisiteur().getNom() + Session.getSession().getLeVisiteur().getPrenom()) ;
                */
            }
        );
        
        itemSeDeconnecter.setOnAction( actionEvent -> {
                session = false ;
                etatSession();
                // primaryStage.setTitle("GSB-RV-DR");
            }
        );
        
        itemConsulter.setOnAction( actionEvent -> {
                System.out.println(itemConsulter);
                // System.out.println("[Rapports]" + Session.getSession().getLeVisiteur().getPrenom() + " " + Session.getSession().getLeVisiteur().getNom());
            }
        );
        
        itemHesitants.setOnAction( actionEvent -> {
                System.out.println(itemHesitants);
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
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("GSB-RV-DR");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}