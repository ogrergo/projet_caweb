package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Consommateur;
import model.Contrat;
import model.Unite;

public class ContratDAO extends AbstractDataBaseDAO {

	protected ContratDAO(DataSource ds) {
		super(ds);
	}
	
	public List<Contrat> getListeContrat(Consommateur consommateur) throws DAOException {
		List<Contrat> result = new ArrayList<Contrat>();
		ResultSet rs = null;
		String requeteSQL = "";
		Connection conn = null;
		try {
			conn = getConnection();
			Statement st = conn.createStatement();
			requeteSQL = "SELECT *"
					+   " FROM contrat";
			rs = st.executeQuery(requeteSQL);
			while (rs.next()) {
				Contrat contrat = new Contrat(rs.getInt("idContrat"),
						rs.getInt("idProduction"),
						rs.getInt("idConsomateur"),
						rs.getInt("quantite"),
						rs.getInt("dateDebut"),
						rs.getInt("duree"),
						rs.getBoolean("valide"));
				result.add(contrat);
			}
		} catch (SQLException e) {
			throw new DAOException("Erreur BD 0" + e.getMessage(), e);
		} finally {
			closeConnection(conn);
		}
		return result;
	}

}
