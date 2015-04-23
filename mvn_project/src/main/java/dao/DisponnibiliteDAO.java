/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Disponnibilite;
import model.Producteur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class DisponnibiliteDAO extends AbstractDataBaseDAO {

    public DisponnibiliteDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Renvoie la liste des infos sur un producteur
     */
    public List<Disponnibilite> getListeDispoByIdConsommateur(int idConsommateur) throws DAOException, SQLException {
        List<Disponnibilite> result = new ArrayList<Disponnibilite>();
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
            	Disponnibilite disponnibilite = new Disponnibilite(rs.getInt("idSemaine"),
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
}