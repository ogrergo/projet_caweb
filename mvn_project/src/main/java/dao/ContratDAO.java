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
					+   " FROM contrat"
					+ 	" WHERE idConsommateur='" + consommateur.getId() + "'";
			rs = st.executeQuery(requeteSQL);
			while (rs.next()) {
				Contrat contrat = new Contrat(rs.getInt("idContrat"),
						rs.getInt("idProduction"),
						rs.getInt("idConsomateur"),
						rs.getInt("quantite"),
						rs.getInt("dateDebut"),
						rs.getInt("duree"),
						rs.getString("valide").equals("1"));
				result.add(contrat);
			}
		} catch (SQLException e) {
			throw new DAOException("Erreur BD " + e.getMessage(), e);
		} finally {
			closeConnection(conn);
		}
		return result;
	}
	
	public void addContrat(Contrat contrat) throws DAOException {
		String requeteSQL = "";
		Connection conn = null;
		try {
			conn = getConnection();
			Statement st = conn.createStatement();
			String valide = contrat.getValide() ? "1" : "0";
			requeteSQL = "INSERT INTO Contrat (idProduction, " 
					+ "idConsommateur, quantite, dateDebut, "
					+ "valide) VALUES ('"
					+ contrat.getIdProduction() + "','"
					+ contrat.getIdConsomateur() + "','"
					+ contrat.getQuantite() + "','"
					+ contrat.getDateDebut() + "','"
					+ valide + "')";
			st.executeQuery(requeteSQL);
		} catch (SQLException e) {
			throw new DAOException("Erreur BD 0" + e.getMessage(), e);
		} finally {
			closeConnection(conn);
		}
	}
	
	public void updateContrat(Contrat contrat) throws DAOException {
		String requeteSQL = "";
		Connection conn = null;
		try {
			conn = getConnection();
			Statement st = conn.createStatement();
			String valide = contrat.getValide() ? "1" : "0";
			
			requeteSQL = "UPDATE contrat" 
					+ " SET idProduction='" + contrat.getIdProduction() + "',"
					+ " idConsommateur ='" + contrat.getIdConsomateur() + "'," 
					+ " quantite='" + contrat.getQuantite() + "',"
					+ " dateDebut='" + contrat.getDateDebut() + "',"
					+ " valide='" + valide + "'"
					+ " WHERE idContrat='" + contrat.getIdContrat() + "'";
			st.executeQuery(requeteSQL);
		} catch (SQLException e) {
			throw new DAOException("Erreur BD 0" + e.getMessage(), e);
		} finally {
			closeConnection(conn);
		}
	}

}
