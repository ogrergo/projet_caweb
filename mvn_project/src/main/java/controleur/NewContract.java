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
        // TODO Auto-generated constructor stub
    }

    
    private static final String IDPRODUCTION_SESSION_VAR = "idProduction";
	
    private int getIdProduction(HttpSession session) {
    	Object idProdObj = session.getAttribute(IDPRODUCTION_SESSION_VAR);
		
		if(idProdObj == null)
			throw new InternalError();
		
		return Integer.parseInt((String) idProdObj);
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		if(request.getParameter("production") != null) 
			request.getSession(true).setAttribute(IDPRODUCTION_SESSION_VAR, request.getParameter("production"));
		
		boolean success = AuthorisationManager.getPermission(request, response, Permission.PRODUCTEUR);//TODO passé en consomateur
		if(!success)
			return;
		

		int idProd = getIdProduction(request.getSession(true));
		
		Production production;
		try {
			production = new ProductionDAO(ds).getProduction(idProd);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new InternalError("Impossible d'acceder à la production.");
		}
		
		if(production == null)
			response.sendRedirect("/caweb");
		
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
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		int date =  Integer.parseInt(request.getParameter("date"));
		
		Contrat contrat = new Contrat(0,
				getIdProduction(request.getSession(true)),
				idConsomateur,
				quantite,
				null,
				0,
				'f'); //TODO
		
		//Recupitulatif -> compte user (mes contrats)
		response.sendRedirect("/caweb");
	//	response.getWriter().println("Demande d'un contrat pour le produit PRODUIT : Quantité : " + Quantité + ", Date : " + Date + ", Durée : " + Durée);
	}

}
