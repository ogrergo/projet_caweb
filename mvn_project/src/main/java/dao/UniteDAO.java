/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Production;
import model.Unite;

public class UniteDAO extends AbstractDataBaseDAO {

    public UniteDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Renvoie la liste des infos sur un producteur
     */
    public List<Unite> getListeUnites() throws DAOException, SQLException {
        List<Unite> result = new ArrayList<Unite>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "SELECT * from Unite";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                Unite unite = new Unite(rs.getString("nomUnite"));
             //   System.err.println(unite);
                result.add(unite);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD 0" + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    public List<Unite> getListeUnites(Production production) throws DAOException {
        List<Unite> result = new ArrayList<Unite>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "SELECT nomUnite"
                    + " FROM ProductionUnites"
                    + " WHERE idProduction='" + production.getIdProduction() + "'";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                Unite unite = new Unite(rs.getString("nomUnite"));
               // System.err.println(unite);
                result.add(unite);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD 0" + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    @SuppressWarnings("finally")
	public boolean ajouterUnite(Unite uni) throws DAOException {
        String requeteSQL = "";
        Connection conn = null;
        boolean succes = true;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "INSERT INTO Unite (nomUnite) VALUES ('" + uni.getNomUnite() + "')";
            st.executeQuery(requeteSQL);
        } catch (SQLException e) {
        	succes = false;
        } finally {
            closeConnection(conn);
            return succes;
        }
    }
}
