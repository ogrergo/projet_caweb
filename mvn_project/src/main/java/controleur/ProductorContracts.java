package controleur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import controleur.AuthorisationManager.Permission;
import model.Contrat;
import model.Producteur;
import model.Production;
import dao.ContratDAO;
import dao.DAOException;
import dao.ProductionDAO;

/**
 * Servlet implementation class ProductorContracts
 */
@WebServlet("/ProductorContracts")
public class ProductorContracts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    @Resource(name = "jdbc/caweb")
    private DataSource ds;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductorContracts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContratDAO contratDAO = new ContratDAO(ds);
		ProductionDAO productionDAO = new ProductionDAO(ds);
		System.out.println((String) request.getParameter("validate"));
		System.out.println((String) request.getParameter("contract"));
		System.out.println(AuthorisationManager.havePermission(request.getSession(), Permission.PRODUCTEUR));
		
		if(		AuthorisationManager.havePermission(request.getSession(), Permission.PRODUCTEUR) &&
				request.getParameter("validate") != null && 
				request.getParameter("contract") != null) {
			
			boolean validate = Boolean.parseBoolean((String) request.getParameter("validate"));
			int idContrat = Integer.parseInt((String) request.getParameter("contract"));
			System.out.println(validate + "  " + idContrat);
			
			
			Contrat contrat;
			try {
				contrat = contratDAO.getContrat(idContrat);
			} catch (DAOException e) {
				e.printStackTrace();
				throw new InternalError("Impossible de récupérer u n contrat.");
			}
			Production production;
			try {
				production = productionDAO.getProduction(contrat.getIdProduction());
			} catch (DAOException e) {
				e.printStackTrace();
				throw new InternalError("Impossible de récupérer la production.");
			}
			
			System.out.println(production.getIdProducteur() + "  " + AuthorisationManager.getIdCompte(request.getSession()));
			if(production.getIdProducteur() != AuthorisationManager.getIdCompte(request.getSession())) {
				System.out.println("Essaie de validation de contrat par le mauvais producteur.");
				response.sendRedirect("/caweb");
				return;
			}
			
			if(validate) {
				contrat.validate();
				System.out.println("fdfdfsdfdsfdsdsf");
				try {
					contratDAO.updateContrat(contrat);
				} catch (DAOException e) {
					e.printStackTrace();
					throw new InternalError("Impossible de valider le contrat.");
				}
			}
		} else {
			
		}
		
		Producteur producteur = new Producteur(AuthorisationManager.getIdCompte(request.getSession()));
    	
    	
    	List<Contrat> contrats = null;
    	try {
			contrats = contratDAO.getListeContrat(producteur);
		} catch (DAOException e) {
			e.printStackTrace();
		}
    	
    	List<Contrat> valide = new ArrayList<Contrat>();
    	List<Contrat> invalide = new ArrayList<Contrat>();
    	for(Contrat c : contrats)
    		if(c.getValide())
    			valide.add(c);
    		else
    			invalide.add(c);
    	
    	request.setAttribute("contratsValide", valide);
    	request.setAttribute("contratsInvalide", invalide);
    	System.out.println("" + valide.size() + "j" + invalide.size());
    	
		getServletContext()
        .getRequestDispatcher("/WEB-INF/productorContracts.jsp")
        .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
