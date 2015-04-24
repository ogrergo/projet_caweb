package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import model.Contrat;
import model.Permanence;

public class PermanenceDAO extends AbstractDataBaseDAO {

	public PermanenceDAO(DataSource ds) {
		super(ds);
	}
	
	public Permanence getPermanence(int idSemaine) throws DAOException {
		Permanence result = null;
		ResultSet rs = null;
		String requeteSQL = "";
		Connection conn = null;
		try {
			conn = getConnection();
			Statement st = conn.createStatement();
			requeteSQL = "SELECT *"
					+   " FROM permanence c "
					+ 	" WHERE c.idSemaine='" + idSemaine + "'";
			rs = st.executeQuery(requeteSQL);
			if(rs.next()) {
				result = new Permanence(rs.getInt("idSemaine"),
						rs.getInt("idConsommateur1"),
						rs.getInt("idConsommateur2"));
			}
		} catch (SQLException e) {
			throw new DAOException("Erreur BD " + e.getMessage(), e);
		} finally {
			closeConnection(conn);
		}
		return result;
	}
	
	public void deleteAllPermananceByIdSemaine(int idSemaine) throws DAOException, SQLException {
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "DELETE FROM Permanence"
                    + " WHERE idSemaine = " + idSemaine;
            st.executeQuery(requeteSQL);
        } catch (SQLException e) {
            throw new DAOException("Erreur BD 0" + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }
	
	public void addPermanence(Permanence permanence) throws DAOException {
		String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "INSERT INTO Permanence (idSemaine, idConsommateur1, idConsommateur2) VALUES ('" 
            		+ permanence.getIdSemaine() + "', '" 
            		+ permanence.getIdConsommateur1() + "', '" 
            		+ permanence.getIdConsommateur2() + "')";
            st.executeQuery(requeteSQL);
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
	}

}
