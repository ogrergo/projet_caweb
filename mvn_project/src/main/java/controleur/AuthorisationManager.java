package controleur;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Compte;
import model.Consommateur;
import model.Producteur;
import model.ResponsablePlanning;

public class AuthorisationManager {

	public static final String CREDENTIAL_SESSION_VAR = "credential";
	public static final String RETURN_SESSION_VAR = "return_page"; 
	
	public static boolean getPermission(HttpServletRequest request, HttpServletResponse response, Permission level) throws IOException {
		HttpSession session = request.getSession(true);
		
		Credential credential = (Credential) session.getAttribute(CREDENTIAL_SESSION_VAR);
		
		if(credential == null || !credential.isAllowed(level)) {
			session.setAttribute(RETURN_SESSION_VAR, request.getRequestURI());
			response.sendRedirect("/caweb/authentification");
			return false;
		}
		
		return true;
	}
	
	private static Permission log(String email, String password) {
		return Permission.PRODUCTEUR;
	}
	
	public static boolean logSession(HttpSession session, String email, String password) {
		Permission permission = log(email, password);
		if(permission == null) {
			return false;
		}
		Compte compte = null;//TODO
		session.setAttribute(CREDENTIAL_SESSION_VAR, new Credential(compte));
		return true;
	}
	
	public static void unlogSession(HttpSession session) {
		session.setAttribute(CREDENTIAL_SESSION_VAR, null);
	}

	public enum Permission {
		CONSOMATEUR(0),
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
				return Permission.CONSOMATEUR;
			
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
		
		public int getId() {
			return idCompte;
		}
		
		public Permission getAuth() {
			return authorisation;
		}

		public boolean isAllowed(Permission level) {//TODO Inclure le fait qu'un responsable plannig ne peut acceder au meme endroit
			return authorisation.level == level.level;
		}
	}
}
