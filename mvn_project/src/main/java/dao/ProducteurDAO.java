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
     * Renvoie la liste des idProducteur, email et mdp
     */
    public List<Producteur> getListeProducteurs() throws DAOException, SQLException {
        List<Producteur> result = new ArrayList<Producteur>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "SELECT idProducteur, email, mdp"
                    + "FROM compte c"
                    + "FULL JOIN producteur p  ON c.idCompte = p.idProducteur";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                Producteur producteur = new Producteur(rs.getInt("idProducteur"),
                        rs.getString("email"),
                        rs.getString("mdp"));
                System.err.println(producteur);
                result.add(producteur);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
}
