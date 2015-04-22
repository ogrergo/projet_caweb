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

	protected ProductionDAO(DataSource ds) {
		super(ds);
	}

	public List<Production> getListeProduction() throws DAOException {
		List<Production> result = new ArrayList<Production>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "SELECT idProduction, produit, idProducteur, duree"
                    + " FROM production";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
            	Production producteur = new Production(rs.getInt("idProduction"),
                        rs.getString("produit"),
                        rs.getInt("idProducteur"),
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
	
	

}