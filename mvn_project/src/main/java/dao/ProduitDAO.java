/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Produit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ProduitDAO extends AbstractDataBaseDAO {

    public ProduitDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Renvoie la liste des infos sur un producteur
     */
    public List<Produit> getListeProduits() throws DAOException, SQLException {
        List<Produit> result = new ArrayList<Produit>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "SELECT nomProduit from Produit";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                Produit produit = new Produit(rs.getString("nomProduit"));
                result.add(produit);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD 0" + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    @SuppressWarnings("finally")
	public boolean ajouterProduit(Produit prod) throws DAOException {
        String requeteSQL = "";
        Connection conn = null;
        boolean succes = true;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "INSERT INTO Produit (nomProduit) VALUES ('" + prod.getNomProduit() + "')";
            st.executeQuery(requeteSQL);
        } catch (SQLException e) {
            succes = false;
        } finally {
            closeConnection(conn);
            return succes;
        }
    }
}
