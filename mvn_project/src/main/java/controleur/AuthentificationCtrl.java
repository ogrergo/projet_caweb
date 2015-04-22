package controleur;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class AuthentificationCtrl
 */
@WebServlet("/AuthentificationCtrl")
public class AuthentificationCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/caweb")
	private DataSource ds;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthentificationCtrl() {
		super();
	}



	private void sendRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String redirect = (String) request.getAttribute(AuthorisationManager.RETURN_SESSION_VAR);
		System.out.println(redirect);
		if(redirect == null)
			redirect = "/caweb";

		response.sendRedirect(redirect);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(AuthorisationManager.haveCredential(request.getSession())) {
			sendRedirect(request, response);
			return;
		}
			
		getServletContext()
		.getRequestDispatcher("/WEB-INF/views/authentification.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		boolean success = AuthorisationManager.logSession(ds, request.getSession(), email, password);

		if(!success) {
			response.sendRedirect("/caweb/echecAuthentification");
			return;
		} else {
			sendRedirect(request, response);
			return;
		}

	}

}
