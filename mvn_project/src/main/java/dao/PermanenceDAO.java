package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Consommateur;
import model.Permanence;

public class PermanenceDAO extends AbstractDataBaseDAO {

	public PermanenceDAO(DataSource ds) {
		super(ds);
	}
	
	public List<Permanence> getPermanenceByUserId(int userId) throws DAOException {
		ArrayList<Permanence> result = new ArrayList<Permanence>();
		ResultSet rs = null;
		String requeteSQL = "";
		Connection conn = null;
		try {
			conn = getConnection();
			Statement st = conn.createStatement();
			requeteSQL = "SELECT *"
					+   " FROM permanence c "
					+ 	" WHERE c.idConsommateur1='" + userId + "'"
			        + 	" OR c.idConsommateur2='" + userId + "'"
			        +   " ORDER BY c.idSemaine DESC";
			rs = st.executeQuery(requeteSQL);
			while(rs.next()) {
				Permanence tmp = new Permanence(rs.getInt("idSemaine"),
						rs.getInt("idConsommateur1"),
						rs.getInt("idConsommateur2"));
				result.add(tmp);
			}
		} catch (SQLException e) {
			throw new DAOException("Erreur BD " + e.getMessage(), e);
		} finally {
			closeConnection(conn);
		}
		return result;
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
	
	public int getNbPermanence(Consommateur consommateur) throws DAOException {
		int result = 0;
		ResultSet rs = null;
		String requeteSQL = "";
		Connection conn = null;
		try {
			conn = getConnection();
			Statement st = conn.createStatement();
			requeteSQL = "SELECT COUNT(*)"
					+   " FROM permanence"
					+ 	" WHERE idConsommateur1='" + consommateur.getId() + "'"
					+ 	" OR idConsommateur2='" + consommateur.getId() + "'";
			rs = st.executeQuery(requeteSQL);
			if(rs.next()) {
				result = rs.getInt("COUNT(*)");
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
