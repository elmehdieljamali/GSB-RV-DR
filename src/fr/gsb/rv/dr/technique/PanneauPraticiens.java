/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.gsb.rv.dr.technique;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefConfiance;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefNotoriete;
import fr.gsb.rv.dr.utilitaires.ComparateurDateVisite;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author debian
 */
public class PanneauPraticiens extends VBox {
    
    public final int CRITERE_COEF_CONFIANCE = 1 ;
    public final int CRITERE_COEF_NOTOTRIETE = 2 ;
    public final int CRITERE_DATE_VISITE = 3 ;

    private int critereTri = CRITERE_COEF_CONFIANCE ;

    private RadioButton rbCoefConfiance ;
    private RadioButton rbCoefNotoriete ;
    private RadioButton rbDateVisite ;
    
    ToggleGroup groupeBouton ;
    
    private TableView<Praticien> tabPraticiens = new TableView<>();
    
    TableColumn<Praticien,Integer> colNumero = new TableColumn<Praticien,Integer>( "Numéro" ) ;
    TableColumn<Praticien,String> colNom = new TableColumn<Praticien,String>( "Nom" ) ;
    TableColumn<Praticien,String> colVille = new TableColumn<Praticien,String>( "Ville" ) ;
    
    private List<Praticien> praticiens ;
    
    private ObservableList<Praticien> observableListPraticiens ;
    
    public PanneauPraticiens () {
        VBox vbox = new VBox();
        Label label = new Label("Sélectionner un critère de tri :");
        vbox.setStyle("-fx-background-color: white");
        vbox.getChildren().add(label);        
        
        GridPane root = new GridPane();
        
        ToggleGroup groupeBouton = new ToggleGroup();
        RadioButton rbCoefConfiance = new RadioButton("Confiance");
        RadioButton rbCoefNotoriete = new RadioButton("Notoriété");
        RadioButton rbDateVisite = new RadioButton("Date Visite");
        
        GridPane.setHalignment(rbCoefConfiance, HPos.RIGHT);
        root.add(rbCoefConfiance, 0, 0);
       
        GridPane.setHalignment(rbCoefNotoriete, HPos.CENTER);
        root.add(rbCoefNotoriete, 1, 0);

        GridPane.setHalignment(rbDateVisite, HPos.LEFT);
        root.add(rbDateVisite, 2, 0);
       
        vbox.getChildren().add(root);
        
        
        rbCoefConfiance.setToggleGroup(groupeBouton) ;
        rbCoefNotoriete.setToggleGroup(groupeBouton) ;
        rbDateVisite.setToggleGroup(groupeBouton) ;
        rbCoefConfiance.setSelected(true) ;
        
        colNumero.setCellValueFactory( new PropertyValueFactory<>( "numero" ) ) ;
        colNom.setCellValueFactory( new PropertyValueFactory<>( "nom" ) ) ;
        colVille.setCellValueFactory( new PropertyValueFactory<>( "ville" ) ) ;
        
        tabPraticiens.getColumns().add( colNumero ) ;
        tabPraticiens.getColumns().add( colNom ) ;
        tabPraticiens.getColumns().add( colVille ) ;
        vbox.getChildren().add(tabPraticiens);
        
        this.getChildren().add(vbox);
    }
    
    public void rafrachir () throws ConnexionException {
        praticiens = ModeleGsbRv.getPraticiensHesitants() ;
        observableListPraticiens = FXCollections.observableArrayList(praticiens);
        
        if(this.getCritereTri() == CRITERE_COEF_CONFIANCE){
            observableListPraticiens.sort(new ComparateurCoefConfiance());
            tabPraticiens.setItems(observableListPraticiens);
        }
        else if (this.getCritereTri() == CRITERE_COEF_NOTOTRIETE){
            observableListPraticiens.sort(new ComparateurCoefNotoriete().reversed());
            tabPraticiens.setItems(observableListPraticiens);
        }
        else if (this.getCritereTri() == CRITERE_DATE_VISITE){
            observableListPraticiens.sort(new ComparateurDateVisite().reversed());
            tabPraticiens.setItems(observableListPraticiens);
        }
    }
    
    public int getCritereTri() {
        return critereTri ;
    }

    public void setCritereTri( int critereTri ) {
        this.critereTri = critereTri ;
    }
}
