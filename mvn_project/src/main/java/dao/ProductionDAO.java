package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Production;

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

    public void ajouterProduction(String produit, String[] unites, int duree, int idProducteur) throws DAOException {
        String requeteSQL = "";
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            //Insertion dans Production
            requeteSQL = "INSERT INTO Production (produit, idProducteur, duree)"
                    + " VALUES ('" + produit + "','" + idProducteur + "','" + duree + "')";
            st.executeQuery(requeteSQL);
            //Recuperation de l'idProduction utilisé
            requeteSQL = "SELECT Max(IdProduction) AS MaxIdProd From Production";
            rs = st.executeQuery(requeteSQL);
            rs.next();
            int idProduction = rs.getInt("MaxIdProd");
            //Insertion dans ProductionUnite
            for (String unite : unites) {
                requeteSQL = "INSERT INTO ProductionUnites (idProduction, nomUnite)"
                        + " VALUES (" + idProduction + ",'" + unite + "')";
                st.executeQuery(requeteSQL);
            }

        } catch (SQLException e) {
            throw new DAOException("Erreur BD 0" + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }

}