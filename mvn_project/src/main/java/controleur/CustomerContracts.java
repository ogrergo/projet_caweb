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
import controleur.AuthorisationManager.AucunCompteLoggeException;
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
		if(!AuthorisationManager.getPermission(request, response, Permission.CONSOMMATEUR))
				return;

		if(request.getParameter("contrat") != null) {
			Integer idContrat = Integer.parseInt(request.getParameter("contrat"));
			if(idContrat != null) {
				ContratDAO contratDAO = new ContratDAO(ds);
				Contrat contrat;
				try {
					contrat = contratDAO.getContrat(idContrat);
					
					
					try {
						if(contrat != null && contrat.getIdConsomateur() == AuthorisationManager.getIdCompte(request.getSession())) {
							contrat.invalidate();
							contratDAO.addContrat(contrat);
						}
					} catch (AucunCompteLoggeException e) {
						response.sendRedirect("/caweb");
						return;
					}
				} catch (DAOException e) {
					e.printStackTrace();
					throw new InternalError();
				}
			}
		}

		
		int idConsomateur;
		try {
			idConsomateur = AuthorisationManager.getIdCompte(request.getSession());
		} catch (AucunCompteLoggeException e1) {
			response.sendRedirect("/caweb");
			return;
		}
		ContratDAO contratDao = new ContratDAO(ds);
		HashMap<Contrat, List<String>> nonCommences = new HashMap<Contrat, List<String>>();
		HashMap<Contrat, List<String>> enCours = new HashMap<Contrat, List<String>>();
		HashMap<Contrat, List<String>> termines = new HashMap<Contrat, List<String>>();
		HashMap<Contrat, List<String>> invalides = new HashMap<Contrat, List<String>>();
		List<Contrat> contrats = new ArrayList<Contrat>();
		List<Contrat> contratsNonCommences = new ArrayList<Contrat>();
		List<Contrat> contratsEncours = new ArrayList<Contrat>();
		List<Contrat> contratsTermines = new ArrayList<Contrat>();
		List<Contrat> contratsInvalides = new ArrayList<Contrat>();
		int current = Planning.getWeekNumber();
		
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
			data.add(product.getAdresse());
			data.add(product.getEmail());
			data.add(production.getProduit());
			String duree = production.getDuree() + "semaines";
			data.add(duree);
			
			
			if(c.getValide()){
				if (c.getDateDebut() <= current) {
					if ((c.getDateDebut() + production.getDuree()) < current) {
						contratsTermines.add(c);
		    			termines.put(c, data);
					} else {
						contratsEncours.add(c);
		    			enCours.put(c, data);
					}
				} else {
					contratsNonCommences.add(c);
	    			nonCommences.put(c, data);
				}
			} else {
    			contratsInvalides.add(c);
    			invalides.put(c, data);
			}
		
		}
		
		request.setAttribute("contratsInvalides", contratsInvalides);
		request.setAttribute("contratsNonCommences", contratsNonCommences);
		request.setAttribute("contratsEnCours", contratsEncours);
		request.setAttribute("contratsTermines", contratsTermines);
    	request.setAttribute("nonCommences", nonCommences);
    	request.setAttribute("enCours", enCours);
    	request.setAttribute("termines", termines);
    	request.setAttribute("invalides", invalides);
    	
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
