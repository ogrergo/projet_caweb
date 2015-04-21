package controleur;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		session.setAttribute(CREDENTIAL_SESSION_VAR, new Credential(permission));
		return true;
	}
	
	public static void unlogSession(HttpSession session) {
		session.setAttribute(CREDENTIAL_SESSION_VAR, null);
	}

	public enum Permission {
		CONSOMATEUR(0),
		PRODUCTEUR(1);
		
		public int level;
		private Permission(int level) {
			this.level = level;
		}
	}
	
	static public class Credential {

		private Permission authorisation;

		private Credential(Permission authorisation) {
			this.authorisation = authorisation;
		}
		
		public Permission getAuth() {
			return authorisation;
		}

		public boolean isAllowed(Permission level) {
			return authorisation.level >= level.level;
		}
	}
}
