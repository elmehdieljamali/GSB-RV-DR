/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.gsb.rv.dr.entites;

import java.time.LocalDate;

/**
 *
 * @author debian
 */
public class Praticien {
    private int numero ;
    private String nom ;
    private String prenom ;
    private String adresse ;
    private int cp ;
    private String ville ;
    private double coefNotoriete ;
    private LocalDate dateDerniereVisite ;
    private int dernierCoefConfiance ;
    
    public Praticien (int numero, String nom, String prenom, String adresse, int cp, String ville, double coefNotoriete, LocalDate dateDerniereVisite, int dernierCoefConfiance) {
        this.numero = numero ;
        this.nom = nom ;
        this.prenom = prenom ;
        this.adresse = adresse ;
        this.cp = cp ;
        this.ville = ville ;
        this.coefNotoriete = coefNotoriete ;
        this.dateDerniereVisite = dateDerniereVisite ;
        this.dernierCoefConfiance = dernierCoefConfiance ;
    }
    
    public int getNumero() {
        return numero ;
    }
    
    public void setNumero (int numero) {
        this.numero = numero ;
    }
    
    public String getNom() {
        return nom ;
    }
    
    public void setNom (String nom) {
        this.nom = nom ;
    }
    
    public String getPrenom() {
        return prenom ;
    }
    
    public void setPrenom (String prenom) {
        this.prenom = prenom ;
    }
    
    public String getAdresse() {
        return adresse ;
    }
    
    public void setAdresse (String adresse) {
        this.adresse = adresse ;
    }
    
    public int getCp() {
        return cp ;
    }
    
    public void setCp (int cp) {
        this.cp = cp ;
    }
    
    public String getVille() {
        return ville ;
    }
    
    public void setVille (String ville) {
        this.ville = ville ;
    }
    
    public double getCoefNotoriete() {
        return coefNotoriete ;
    }
    
    public void setCoefNotoriete (double coefNotoriete) {
        this.coefNotoriete = coefNotoriete ;
    }
    
    public LocalDate getDateDerniereVisite() {
        return dateDerniereVisite ;
    }
    
    public void setDateDerniereVisite (LocalDate dateDerniereVisite) {
        this.dateDerniereVisite = dateDerniereVisite ;
    }
    
    public int getDernierCoefConfiance() {
        return dernierCoefConfiance ;
    }
    
    public void setDernierCoefConfiance (int dernierCoefConfiance) {
        this.dernierCoefConfiance = dernierCoefConfiance ;
    }
}
