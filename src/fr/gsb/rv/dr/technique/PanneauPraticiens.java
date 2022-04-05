/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.gsb.rv.dr.technique;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;

/**
 *
 * @author debian
 */
public class PanneauPraticiens extends VBox {
    
    public final int CRITERE_COEF_CONFIANCE = 1 ;
    public final int CRITERE_COEF_NOTOTRIETE = 2 ;
    public final int CRITERE_COEF_VISITE = 3 ;

    private int critereTri = CRITERE_COEF_CONFIANCE ;

    private RadioButton rbCoefConfiance ;
    private RadioButton rbCoefNotoriete ;
    private RadioButton rbDateVisite ;
    
    public PanneauPraticiens () {
        VBox vbox = new VBox();
        Label label = new Label("Praticiens");
        vbox.setStyle("-fx-background-color: white");
        vbox.getChildren().add(label);
        this.getChildren().add(vbox);
    }
    
    public void rafrachir () {
        
    }
    
    public int getCritereTri() {
        return critereTri ;
    }

    public void setCritereTri( int critereTri ) {
        this.critereTri = critereTri ;
    }
}
