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
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		if(request.getParameter("production") != null)
			request.getSession(true).setAttribute(IDPRODUCTION_SESSION_VAR, request.getParameter("production"));
		
		boolean success = AuthorisationManager.getPermission(request, response, Permission.CONSOMATEUR);
		if(!success) {
			return;
		}
		

		int idProd = 0;
		try {
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
		
		request.setAttribute("production", production);
				
		getServletContext()
        .getRequestDispatcher("/WEB-INF/newContract.jsp")
        .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idConsomateur = AuthorisationManager.getIdCompte(request.getSession());
		int quantite = 0;
		int date = 0;
		try {
			quantite = Integer.parseInt(request.getParameter("quantite"));
			date =  Integer.parseInt(request.getParameter("date"));
		} catch(NumberFormatException e) {
			try {
				response.sendRedirect("/caweb/newContract?production=" + getIdProduction(request.getSession(true)));
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
					false);
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
