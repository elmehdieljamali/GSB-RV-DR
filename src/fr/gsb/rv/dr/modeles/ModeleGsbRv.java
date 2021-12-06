package fr.gsb.rv.dr.modeles;

import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
