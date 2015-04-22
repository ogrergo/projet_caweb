package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Production;
import model.Produit;

public class ProductionDAO extends AbstractDataBaseDAO {

    public ProductionDAO(DataSource ds) {
        super(ds);
    }
    
    public Production getProduction(int idProduction) throws DAOException {
    	Production result = null;
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "SELECT idProduction, produit, nom, prenom, duree"
                    + " FROM production p, producteur q"
                    + " FULL JOIN utilisateur u ON u.idUtilisateur = q.idProducteur"
                    + " WHERE p.idProducteur = q.idProducteur"
                    + " AND p.idProduction='" + idProduction + "'";
            rs = st.executeQuery(requeteSQL);
            if (rs.next()) {
            	result = new Production(rs.getInt("idProduction"),
                        rs.getString("produit"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getInt("duree")
                );
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    public List<Production> getListeProduction() throws DAOException {
        List<Production> result = new ArrayList<Production>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "SELECT idProduction, produit, nom, prenom, duree"
                    + " FROM production p, producteur q"
                    + " FULL JOIN utilisateur u ON u.idUtilisateur = q.idProducteur"
                    + " WHERE p.idProducteur = q.idProducteur";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                Production producteur = new Production(rs.getInt("idProduction"),
                        rs.getString("produit"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getInt("duree")
                );
                System.err.println(producteur);
                result.add(producteur);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD" + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    public Production ajouterProduction() throws DAOException {
     /*   Production result = null;
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "SELECT idProduction, produit, nom, prenom, duree"
                    + " FROM production p, producteur q"
                    + " FULL JOIN utilisateur u ON u.idUtilisateur = q.idProducteur"
                    + " WHERE p.idProducteur = q.idProducteur";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                Production producteur = new Production(rs.getInt("idProduction"),
                        rs.getString("produit"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getInt("duree")
                );
                System.err.println(producteur);
              //AJOUT
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD" + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }*/
        return null;
    }

}
