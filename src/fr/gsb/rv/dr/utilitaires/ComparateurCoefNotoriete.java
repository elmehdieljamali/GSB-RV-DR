/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.gsb.rv.dr.utilitaires;

import fr.gsb.rv.dr.entites.Praticien;
import java.util.Comparator;

/**
 *
 * @author debian
 */
public class ComparateurCoefNotoriete implements Comparator<Praticien>{
    
    @Override
    public int compare( Praticien o1 , Praticien o2 ) {
        if( o1.getCoefNotoriete() == o2.getCoefNotoriete() ){
            return 0 ;
        }
        else if( o1.getCoefNotoriete() > o2.getCoefNotoriete() ) {
            return 1 ;
        }
        else {
            return -1 ;
        }
    }
}
