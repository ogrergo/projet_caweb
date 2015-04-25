package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Contrat;
import model.Producteur;

public class ContratDAO extends AbstractDataBaseDAO {

	public ContratDAO(DataSource ds) {
		super(ds);
	}
	
	
	public List<Contrat> getListeContrat(Producteur producteur) throws DAOException {
		List<Contrat> result = new ArrayList<Contrat>();
		ResultSet rs = null;
		String requeteSQL = "";
		Connection conn = null;
		try {
			conn = getConnection();
			Statement st = conn.createStatement();
			requeteSQL = "SELECT nomUnite, idContrat, c.idProduction, idConsommateur, quantite, dateDebut, duree, valide"
					+ " FROM contrat c"
					+ " INNER JOIN production p ON p.idProduction = c.idProduction"
					+ 	" WHERE p.idProducteur='" + producteur.getId() + "'";
			rs = st.executeQuery(requeteSQL);
			while (rs.next()) {
				Contrat contrat = new Contrat(rs.getInt("idContrat"),
						rs.getInt("idProduction"),
						rs.getInt("idConsommateur"),
						rs.getInt("quantite"),
						rs.getInt("dateDebut"),
						rs.getInt("duree"),
						rs.getString("nomUnite"),
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
	
	
	
	public ArrayList<Contrat> getListeContrat(int idConsommateur) throws DAOException {
		ArrayList<Contrat> result = new ArrayList<Contrat>();
		ResultSet rs = null;
		String requeteSQL = "";
		Connection conn = null;
		try {
			conn = getConnection();
			Statement st = conn.createStatement();
			requeteSQL = "SELECT *"
					+   " FROM contrat c join production p on c.idProduction=p.idProduction"
					+ 	" WHERE c.idConsommateur=" + idConsommateur;
			rs = st.executeQuery(requeteSQL);
			while (rs.next()) {
				Contrat contrat = new Contrat(rs.getInt("idContrat"),
						rs.getInt("idProduction"),
						rs.getInt("idConsommateur"),
						rs.getInt("quantite"),
						rs.getInt("dateDebut"),
						rs.getInt("duree"),
						rs.getString("nomUnite"),
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
					+ "valide, nomUnite) VALUES ('"
					+ contrat.getIdProduction() + "','"
					+ contrat.getIdConsomateur() + "','"
					+ contrat.getQuantite() + "','"
					+ contrat.getDateDebut() + "','"
					+ valide + "','"
					+ contrat.getNomUnite() + "')";
			
			System.out.println(requeteSQL);
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

	public int getSemaineContratMaxByIdConsommateur(int idConsommateur) throws DAOException {
		ResultSet rs = null;
		String requeteSQL = "";
		Connection conn = null;
		int max = 0;
		try {
			conn = getConnection();
			Statement st = conn.createStatement();
			requeteSQL = "SELECT MAX(dateDebut + duree) AS MaxSem"
					+   " FROM contrat c"
					+   " RIGHT JOIN production p ON c.idProduction=p.idProduction"
					+ 	" WHERE idConsommateur=" + idConsommateur;
			rs = st.executeQuery(requeteSQL);
			rs.next();
			max = rs.getInt("MaxSem");
		} catch (SQLException e) {
			throw new DAOException("Erreur BD " + e.getMessage(), e);
		} finally {
			closeConnection(conn);
		}
		return max;
	}


	public Contrat getContrat(int idContrat) throws DAOException {
		Contrat result = null;
		ResultSet rs = null;
		String requeteSQL = "";
		Connection conn = null;
		try {
			conn = getConnection();
			Statement st = conn.createStatement();
			requeteSQL = "SELECT *"
					+   " FROM contrat c "
					+ 	" INNER JOIN production p ON c.idProduction = p.idProduction"
					+ 	" WHERE c.idContrat='" + idContrat + "'";
			rs = st.executeQuery(requeteSQL);
			if(rs.next()) {
				result = new Contrat(rs.getInt("idContrat"),
						rs.getInt("idProduction"),
						rs.getInt("idConsommateur"),
						rs.getInt("quantite"),
						rs.getInt("dateDebut"),
						rs.getInt("duree"),
						rs.getString("nomUnite"),
						rs.getString("valide").equals("1"));
			}
		} catch (SQLException e) {
			throw new DAOException("Erreur BD " + e.getMessage(), e);
		} finally {
			closeConnection(conn);
		}
		return result;
	}
	
	public void deleteContrat(int idContrat) throws DAOException {
		String requeteSQL = "";
		Connection conn = null;
		try {
			conn = getConnection();
			Statement st = conn.createStatement();
			requeteSQL = "DELETE FROM Contrat WHERE idContrat = " + idContrat;
			st.executeQuery(requeteSQL);
			
		} catch (SQLException e) {
			throw new DAOException("Erreur BD " + e.getMessage(), e);
		} finally {
			closeConnection(conn);
		}
	}
}
