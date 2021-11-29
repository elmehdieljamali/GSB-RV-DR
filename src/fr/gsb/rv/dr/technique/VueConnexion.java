/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.gsb.rv.dr.technique;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.Pair;

/**
 *
 * @author debian
 */
public class VueConnexion extends Dialog {
    String matricule ;
    String mdp ;
    public VueConnexion () {
        Dialog<Pair<String, String>> dialog = new Dialog<>();   
        dialog.setTitle("Authentification") ;
        dialog.setHeaderText("Saisir vos données de connexion");
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Matricule");
        PasswordField password = new PasswordField();
        password.setPromptText("Mot de passe");

        grid.add(new Label("Matricule:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Mot de passe:"), 0, 1);
        grid.add(password, 1, 1);

        dialog.getDialogPane().setContent(grid);

        ButtonType loginButtonType = new ButtonType("Se connecter", ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, cancelButtonType);

        setResultConverter (
            new Callback<ButtonType, Pair<String, String>>() {
                public Pair<String, String> call( ButtonType typeBouton) {
                    if ( typeBouton == loginButtonType) {
                        return new Pair<String, String>( matricule , mdp);
                    }
                    return null;
                }
            }
        );
    }
}
