package controleur;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controleur.AuthorisationManager.Permission;

/**
 * Servlet implementation class NewContract
 */
@WebServlet("/NewContract")
public class NewContract extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewContract() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    private static final String IDPRODUCTION_SESSION_VAR = "idProduction";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession(true).setAttribute(IDPRODUCTION_SESSION_VAR, request.getAttribute("production"));
		
		boolean success = AuthorisationManager.getPermission(request, response, Permission.PRODUCTEUR);//TODO passé en consomateur
		if(!success)
			return;
		
				
		getServletContext()
        .getRequestDispatcher("/WEB-INF/newContract.jsp")
        .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idConsomateur = AuthorisationManager.getIdCompte(request.getSession());
		String Quantité = request.getParameter("quantite");
		String Date = request.getParameter("date");
		String Durée = request.getParameter("duree");
		
		response.getWriter().println("Demande d'un contrat pour le produit PRODUIT : Quantité : " + Quantité + ", Date : " + Date + ", Durée : " + Durée);
	}

}
