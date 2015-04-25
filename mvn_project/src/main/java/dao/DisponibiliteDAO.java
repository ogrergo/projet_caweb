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

import javax.sql.DataSource;

import model.Disponibilite;

public class DisponibiliteDAO extends AbstractDataBaseDAO {

    public DisponibiliteDAO(DataSource ds) {
        super(ds);
    }

    public ArrayList<Disponibilite> getListeDispoByIdConsommateur(int idConsommateur) throws DAOException, SQLException {
        ArrayList<Disponibilite> result = new ArrayList<Disponibilite>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "SELECT idSemaine, idConsommateur"
                    + " FROM Disponibilite"
                    + " WHERE idConsommateur = " + idConsommateur;
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
            	Disponibilite disponnibilite = new Disponibilite(rs.getInt("idSemaine"),
                        rs.getInt("idConsommateur"));
                result.add(disponnibilite);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD 0" + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
    
    public void deleteAllDispoByIdConsommateur(int idConsommateur) throws DAOException, SQLException {
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "DELETE FROM Disponibilite"
                    + " WHERE idConsommateur = " + idConsommateur;
            st.executeQuery(requeteSQL);
        } catch (SQLException e) {
            throw new DAOException("Erreur BD 0" + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }
        
    public void ajouterDisponnibilite(Disponibilite d) throws DAOException, SQLException {
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "INSERT INTO Disponibilite (idSemaine, idConsommateur) VALUES (" + d.getIdSemaine() + "," + d.getIdConsommateur() + ")";
            st.executeQuery(requeteSQL);
        } catch (SQLException e) {
            throw new DAOException("Erreur BD 0" + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }    
}