package controleur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.Contrat;
import model.Producteur;
import model.Production;
import controleur.AuthorisationManager.Permission;
import dao.ContratDAO;
import dao.DAOException;
import dao.ProducteurDAO;
import dao.ProductionDAO;

/**
 * Servlet implementation class CustomerContracts
 */
@WebServlet("/CustomerContracts")
public class CustomerContracts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "jdbc/caweb")
    private DataSource ds;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerContracts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean success = AuthorisationManager.getPermission(request, response, Permission.CONSOMATEUR);
		
		if(!success) {
			response.sendRedirect("/caweb");
			return;
		}
		
		int idConsomateur = AuthorisationManager.getIdCompte(request.getSession());
		ContratDAO contratDao = new ContratDAO(ds);
		HashMap<Contrat, List<String>> valides = new HashMap<Contrat, List<String>>();
		HashMap<Contrat, List<String>> invalides = new HashMap<Contrat, List<String>>();
		List<Contrat> contrats = new ArrayList<Contrat>();
		List<Contrat> contratsValides = new ArrayList<Contrat>();
		List<Contrat> contratsInvalides = new ArrayList<Contrat>();
		
		
		try {
			contrats = contratDao.getListeContrat(idConsomateur);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new InternalError();
		}
		ProducteurDAO producteur = new ProducteurDAO(ds);
		ProductionDAO prod = new ProductionDAO(ds);
		Production production = null;
		Producteur product = null;
		
		for(Contrat c : contrats){
			List<String> data = new ArrayList<String>();			
			try {
				production = prod.getProduction(c.getIdProduction());
				product = producteur.getProducteur(production.getIdProducteur());
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
			data.add(product.getNom());
			System.out.println(product.getNom());
			data.add(product.getAdresse());
			System.out.println(product.getAdresse());
			data.add(product.getEmail());
			System.out.println(product.getEmail());
			data.add(production.getProduit());
			System.out.println(production.getProduit());
			String duree = production.getDuree() + "semaines";
			data.add(duree);
			
			
			if(c.getValide()){
				contratsValides.add(c);
    			valides.put(c, data);
			} else {
    			contratsInvalides.add(c);
    			invalides.put(c, data);
			}
		
		}
		request.setAttribute("contratsInvalides", contratsInvalides);
		request.setAttribute("contratsValides", contratsValides);
    	request.setAttribute("valide", valides);
    	request.setAttribute("invalide", invalides);
    	
		getServletContext()
        .getRequestDispatcher("/WEB-INF/customerContracts.jsp")
        .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
