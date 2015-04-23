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
import model.Producteur;

public class ConsommateurDAO extends AbstractDataBaseDAO {

	public ConsommateurDAO(DataSource ds) {
		super(ds);
	}
	
	public List<Consommateur> getListeConsommateur(int semaine) throws DAOException {
		List<Consommateur> result = new ArrayList<Consommateur>();
		ResultSet rs = null;
		String requeteSQL = "";
		Connection conn = null;
		try {
			conn = getConnection();
			Statement st = conn.createStatement();
			requeteSQL = "SELECT p.idConsommateur, email, mdp, prenom, nom, adresse"
                    + " FROM compte c"
                    + " INNER JOIN consommateur p ON c.idCompte = p.idConsommateur"
                    + " INNER JOIN utilisateur u ON c.idCompte = u.idUtilisateur"
                    + " INNER JOIN disponibilite d ON p.idConsommateur = d.idConsommateur"
					+ " WHERE d.idSemaine='" + semaine + "'";
			rs = st.executeQuery(requeteSQL);
			while (rs.next()) {
				Consommateur contrat = new Consommateur(rs.getInt("idConsommateur"),
                        rs.getString("email"),
                        rs.getString("mdp"),
                        rs.getString("prenom"),
                        rs.getString("nom"),
                        rs.getString("adresse"));
				result.add(contrat);
			}
		} catch (SQLException e) {
			throw new DAOException("Erreur BD " + e.getMessage(), e);
		} finally {
			closeConnection(conn);
		}
		return result;
	}
	
	

}
