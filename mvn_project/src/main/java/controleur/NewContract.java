package controleur;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.Contrat;
import model.Production;
import controleur.AuthorisationManager.AucunCompteLoggeException;
import controleur.AuthorisationManager.Permission;
import dao.ContratDAO;
import dao.DAOException;
import dao.ProductionDAO;

/**
 * Servlet implementation class NewContract
 */
@WebServlet("/NewContract")
public class NewContract extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Resource(name = "jdbc/caweb")
    private DataSource ds;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewContract() {
        super();
    }

    
    private static final String IDPRODUCTION_SESSION_VAR = "idProduction";
	private static final String UNITE_SESSION_VAR = "unite";
	
    private class AucuneProductionException extends Exception {
		private static final long serialVersionUID = 1L;
	}
    
    private int getIdProduction(HttpSession session) throws AucuneProductionException {
    	Object idProdObj = session.getAttribute(IDPRODUCTION_SESSION_VAR);
		
    	
		if(idProdObj == null) {
			System.out.println("impossible de recuperer la var get");
			throw new AucuneProductionException();
		}
		
		return Integer.parseInt((String) idProdObj);
    }
    
    private String getUnite(HttpSession session) throws AucuneProductionException {
    	Object unite = session.getAttribute(UNITE_SESSION_VAR);
		
    	
		if(unite == null) {
			System.out.println("impossible de recuperer la var get");
			throw new AucuneProductionException();
		}
		
		return (String)unite;
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		if(request.getParameter("production") != null && request.getParameter("unittype") != null) {
			session.setAttribute(IDPRODUCTION_SESSION_VAR, request.getParameter("production"));
			session.setAttribute(UNITE_SESSION_VAR, request.getParameter("unittype"));
		}
		
		
		boolean success = AuthorisationManager.getPermission(request, response, Permission.CONSOMMATEUR);
		if(!success) {
			return;
		}
		

		int idProd = 0;
		String unite = null;
		try {
			unite = getUnite(request.getSession(true));
			idProd = getIdProduction(request.getSession(true));
		} catch (AucuneProductionException e1) {
			response.sendRedirect("/caweb");
			return;
		}
		
		Production production = null;
		try {
			production = new ProductionDAO(ds).getProduction(idProd);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new InternalError("Impossible d'acceder à la production.");
		}
		
		if(production == null) {
			response.sendRedirect("/caweb");
			return;
		}
		
		request.setAttribute("unite", unite);
		request.setAttribute("production", production);
				
		getServletContext()
        .getRequestDispatcher("/WEB-INF/newContract.jsp")
        .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idConsomateur;
		try {
			idConsomateur = AuthorisationManager.getIdCompte(request.getSession());
		} catch (AucunCompteLoggeException e2) {
			response.sendRedirect("/caweb");
			return;
		}
		int quantite = 0;
		int date = 0;
		try {
			quantite = Integer.parseInt(request.getParameter("quantite"));
			date =  Integer.parseInt(request.getParameter("date"));
		} catch(NumberFormatException e) {
			try {
				response.sendRedirect("/caweb/newContract?production=" + getIdProduction(request.getSession(true)) + "&unittype=" + getUnite(request.getSession(true)));
			} catch (AucuneProductionException e1) {
				response.sendRedirect("/caweb");			
			}
			return;
		}
		Contrat contrat = null;
		try {
			contrat = new Contrat(
					getIdProduction(request.getSession(true)),
					idConsomateur,
					quantite,
					date,
					false,
					getUnite(request.getSession(true)));
		} catch (AucuneProductionException e) {
			response.sendRedirect("/caweb");
		}
		
		ContratDAO contratDAO = new ContratDAO(ds);
		try {
			contratDAO.addContrat(contrat);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new InternalError("Impossible d'ajouter un contrat.");
		}
		
		response.sendRedirect("/caweb");
	}

}
