/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.gsb.rv.dr.technique;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author debian
 */
public class PanneauRapports extends VBox {
    
    public PanneauRapports () {
        VBox vbox = new VBox();
        Label label = new Label("Rapports");
        vbox.setStyle("-fx-background-color: white");
        vbox.getChildren().add(label);
        this.getChildren().add(vbox);
    }
}
