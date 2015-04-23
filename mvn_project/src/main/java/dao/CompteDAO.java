package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import model.Compte;
import model.Consommateur;
import model.Producteur;
import model.ResponsablePlanning;

public class CompteDAO extends AbstractDataBaseDAO {

	public CompteDAO(DataSource ds) {
		super(ds);
	}
	
	
	public Compte getCompte(String email, String mdp) throws DAOException {
		Compte result = null;
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
        	conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "SELECT idCompte"
                    + " FROM compte c"
            		+ " WHERE c.email='" + email + "'"
            		+ " AND   c.mdp='" + mdp + "'";
            rs = st.executeQuery(requeteSQL);
           
            if(rs.next()) {
            	result = getCompte(rs.getInt("idCompte"));
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD 0" + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
	}
	
	public Compte getCompte(int compteId) throws DAOException {
		Compte result = null;
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "SELECT idProducteur, email, mdp, prenom, nom, adresse"
                    + " FROM compte c"
                    + " FULL JOIN producteur p ON c.idCompte = p.idProducteur"
                    + " FULL JOIN utilisateur u ON c.idCompte = u.idUtilisateur"
                    + " WHERE c.idCompte='" + compteId + "'";
            rs = st.executeQuery(requeteSQL);
            // On compte le nombre de résultats
            if(rs.next()) {
            	result = new Producteur(rs.getInt("idProducteur"),
                        rs.getString("email"),
                        rs.getString("mdp"),
                        rs.getString("prenom"),
                        rs.getString("nom"),
                        rs.getString("adresse")
                );
            } else {
            	requeteSQL = "SELECT idConsommateur, email, mdp, prenom, nom, adresse"
                        + " FROM compte c"
                        + " FULL JOIN consommateur p ON c.idCompte = p.idConsommateur"
                        + " FULL JOIN utilisateur u ON c.idCompte = u.idUtilisateur"
                		+ " WHERE c.idCompte='" + compteId + "'";
                rs = st.executeQuery(requeteSQL);
                if(rs.next()) {
                	result = new Consommateur(rs.getInt("idConsommateur"),
                            rs.getString("email"),
                            rs.getString("mdp"),
                            rs.getString("prenom"),
                            rs.getString("nom"),
                            rs.getString("adresse")
                    );
                } else {
                	requeteSQL = "SELECT idRespo, email, mdp"
                            + " FROM compte c"
                            + " FULL JOIN ResponsablePlanning r ON c.idCompte = r.idRespo"
                            + " WHERE c.idCompte='" + compteId + "'";
                    rs = st.executeQuery(requeteSQL);
                    if(rs.next()) {
               
                    	result = new ResponsablePlanning(rs.getInt("idRespo"),
                                rs.getString("email"),
                                rs.getString("mdp")
                        );
                    }
                }
            }
            
            
        } catch (SQLException e) {
            throw new DAOException("Erreur BD 0" + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
	}

}
