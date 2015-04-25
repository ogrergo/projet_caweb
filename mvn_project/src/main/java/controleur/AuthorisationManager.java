package controleur;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.Compte;
import model.Consommateur;
import model.Producteur;
import model.ResponsablePlanning;
import dao.CompteDAO;
import dao.DAOException;

public class AuthorisationManager {

	public static final String CREDENTIAL_SESSION_VAR = "credential";
	public static final String RETURN_SESSION_VAR = "return_page"; 
	
	public static boolean getPermission(HttpServletRequest request, HttpServletResponse response, Permission level) throws IOException {
		HttpSession session = request.getSession(true);
		
		Credential credential = (Credential) session.getAttribute(CREDENTIAL_SESSION_VAR);
		
		if(credential == null) {
			session.setAttribute(RETURN_SESSION_VAR, request.getRequestURI());
			
			response.sendRedirect("/caweb/authentification");
			return false;
		}
		
		if(!credential.isAllowed(level)) {
			response.sendRedirect("/caweb");//TODO ajouter l'erreur
			return false;
		}
		
		return true;
	}
	
	public static boolean haveCredential(HttpSession session) {
		return session.getAttribute(CREDENTIAL_SESSION_VAR) != null;
	}
	
	public static boolean havePermission(HttpSession session, Permission permission) {
		Credential cred = (Credential) session.getAttribute(CREDENTIAL_SESSION_VAR);
		return cred != null && cred.isAllowed(permission);
	}
	
	/*private static Permission log(String email, String password) {
		Compte compte = 
		return ;
	}*/
	
	public static boolean logSession(DataSource ds, HttpSession session, String email, String password) {
	//	Permission permission = log(email, password);
		
	/*	if(permission == null) {
			return false;
		}
		*/
		Compte compte = null;
		
		try {
			compte = new CompteDAO(ds).getCompte(email, password);
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
		
		if(compte == null) {
			return false;
		}
		
		if(compte instanceof Producteur) {
			Producteur p = (Producteur) compte;
		}
		
		session.setAttribute(CREDENTIAL_SESSION_VAR, new Credential(compte));
		return true;
	}
	
	public static void unlogSession(HttpSession session) {
		session.setAttribute(CREDENTIAL_SESSION_VAR, null);
	}

	public static int getIdCompte(HttpSession session) {
		Credential credential = (Credential) session.getAttribute(CREDENTIAL_SESSION_VAR);
		if(credential == null)
			throw new InternalError("Aucun compte loggé");
		
		return credential.idCompte;
	}
	
	public enum Permission {
		CONSOMMATEUR(0),
		PRODUCTEUR(1),
		RESPONSABLE_PLANNING(2);
		
		public int level;
		
		private Permission(int level) {
			this.level = level;
		}
	}
	
	static public class Credential {

		private Permission authorisation;
		private int idCompte;
		
		private Permission getPermission(Compte compte) {
			if(compte instanceof Consommateur)
				return Permission.CONSOMMATEUR;
			
			if(compte instanceof Producteur)
				return Permission.PRODUCTEUR;
			
			if(compte instanceof ResponsablePlanning)
				return Permission.RESPONSABLE_PLANNING;
			
			throw new InternalError();
		}
		
		private Credential(Compte compte) {
			this.authorisation = getPermission(compte);
			idCompte = compte.getId();
		}
		
		public int getIdCompte() {
			return idCompte;
		}
		
		public Permission getAuthorisation() {
			return authorisation;
		}

		public boolean isAllowed(Permission level) {//TODO Inclure le fait qu'un responsable plannig ne peut acceder au meme endroit
			return authorisation.level == level.level;
		}
	}
}
