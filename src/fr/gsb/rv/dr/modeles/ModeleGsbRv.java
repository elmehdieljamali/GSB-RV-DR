package fr.gsb.rv.dr.modeles;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ModeleGsbRv {
    
    public static Visiteur seConnecter( String matricule , String mdp ) throws ConnexionException{
        
        
        Connection connexion = ConnexionBD.getConnexion() ;
        
        String requete = "SELECT vis_nom, vis_prenom "
                + "FROM Travailler as t "
                + "INNER JOIN ( SELECT tra_role, vis_matricule, MAX(jjmmaa) as jjmmaa FROM Travailler GROUP BY vis_matricule) as u "
                + "INNER JOIN Visiteur as v "
                + "ON t.vis_matricule = u.vis_matricule "
                + "AND t.jjmmaa = u.jjmmaa "
                + "AND t.vis_matricule = v.vis_matricule "
                + "WHERE t.tra_role = 'Délégué' AND v.vis_matricule = ? AND v.vis_mdp = ? " ;
        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            requetePreparee.setString( 1 , matricule );
            requetePreparee.setString( 2 , mdp );
            ResultSet resultat = requetePreparee.executeQuery() ;
            if( resultat.next() ){
                Visiteur visiteur = new Visiteur() ;
                visiteur.setMatricule( matricule );
                visiteur.setNom( resultat.getString( "vis_nom" ) ) ;
                visiteur.setPrenom( resultat.getString( "vis_prenom" ) ) ;
                
                requetePreparee.close() ;
                return visiteur ;
            }
            else {
                return null ;
            }
        }
        catch( Exception e ){
            return null ;
        } 
    }
    
    public static List getPraticiensHesitants () throws ConnexionException{
        
        Connection connexion = ConnexionBD.getConnexion() ;
        
        String requetePraticiens = "SELECT vis_matricule, pra_coefnotoriete, pra_ville, R.pra_num, pra_nom, rap_num, rap_date_visite, rap_bilan, rap_coeffconfiance, rap_date_saisie, rap_motif,pra_nom, pra_prenom, R.pra_num "
                + "FROM RapportVisite as R "
                + "INNER JOIN Praticien as P "
                + "ON R.pra_num = P.pra_num "
                + "WHERE rap_date_visite in( SELECT MAX(rap_date_visite) FROM RapportVisite GROUP BYs pra_num) "
                + "AND rap_coeffconfiance < 5 " ;
        List<Praticien> praticiens = new ArrayList<>();
        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requetePraticiens ) ;
            ResultSet resultat = requetePreparee.executeQuery() ;
            while( resultat.next() ){
                Praticien unPraticien = new Praticien (
                        resultat.getInt("pra_num"),
                        resultat.getString("pra_nom"),
                        resultat.getString("pra_prenom"),
                        resultat.getString("pra_adresse"),
                        resultat.getInt("pra_cp"),
                        resultat.getString("pra_ville"),
                        resultat.getDouble("pra_coefNotoriete"),
                        resultat.getDate("rap_date_visite").toLocalDate(),
                        resultat.getInt("rap_coeffconfiance")
                );
                praticiens.add(unPraticien);
            }
            requetePreparee.close() ;
        }
        catch( Exception e ){
            return null ;
        } 
        return praticiens ;
    }
}
