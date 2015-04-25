/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Producteur;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class ProducteurDAO extends AbstractDataBaseDAO {

    public ProducteurDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Renvoie la liste des infos sur un producteur
     */
    public List<Producteur> getListeProducteurs() throws DAOException, SQLException {
        List<Producteur> result = new ArrayList<Producteur>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "SELECT idProducteur, email, mdp, prenom, nom, adresse"
                    + " FROM compte c"
                    + " FULL JOIN producteur p ON c.idCompte = p.idProducteur"
                    + " FULL JOIN utilisateur u ON c.idCompte = u.idUtilisateur";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                Producteur producteur = new Producteur(rs.getInt("idProducteur"),
                        rs.getString("email"),
                        rs.getString("mdp"),
                        rs.getString("prenom"),
                        rs.getString("nom"),
                        rs.getString("adresse")
                );
                System.err.println(producteur);
                result.add(producteur);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD 0" + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

	public Producteur getProducteur(int idProducteur) throws DAOException {
		Producteur producteur=null;
		ResultSet rs = null;
		String requeteSQL = "";
        Connection conn = null;
        try {
        	conn = getConnection();
        
        	Statement st = conn.createStatement();
        	requeteSQL = "SELECT idProducteur, email, mdp, prenom, nom, adresse"
                    + " FROM compte c"
                    + " FULL JOIN producteur p ON c.idCompte = p.idProducteur"
                    + " FULL JOIN utilisateur u ON c.idCompte = u.idUtilisateur"
                    + " WHERE p.idProducteur=" + idProducteur;
        	rs = st.executeQuery(requeteSQL);
        	
        	while (rs.next()) {
                producteur = new Producteur(rs.getInt("idProducteur"),
                        rs.getString("email"),
                        rs.getString("mdp"),
                        rs.getString("prenom"),
                        rs.getString("nom"),
                        rs.getString("adresse")
                );
        	}
        	
        } catch (SQLException e) {
            throw new DAOException("Erreur BD 0" + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
		return producteur;
	}
}