package controleur;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AuthentificationCtrl
 */
@WebServlet("/AuthentificationCtrl")
public class AuthentificationCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthentificationCtrl() {
        super();
    }
    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		boolean success = AuthorisationManager.logSession(request.getSession(), email, password);
		
		if(!success) {
			response.sendRedirect("/caweb/echecAuthentification");
			return;
		} else {
			String redirect = (String) request.getAttribute(AuthorisationManager.RETURN_SESSION_VAR);
			if(redirect == null)
				redirect = "/caweb/home";
			
			response.sendRedirect(redirect);
			return;
		}

	}

}
