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

import controleur.AuthorisationManager.AucunCompteLoggeException;
import controleur.AuthorisationManager.Permission;
import model.Consommateur;
import model.Contrat;
import model.Producteur;
import model.Production;
import dao.ConsommateurDAO;
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
		 if(!AuthorisationManager.getPermission(request, response, Permission.PRODUCTEUR))
	        	return;
	    	
		
		ContratDAO contratDAO = new ContratDAO(ds);
		ProductionDAO productionDAO = new ProductionDAO(ds);
		
		if(		AuthorisationManager.havePermission(request.getSession(), Permission.PRODUCTEUR) &&
				request.getParameter("validate") != null && 
				request.getParameter("contract") != null) {
			
			boolean validate = Boolean.parseBoolean((String) request.getParameter("validate"));
			int idContrat = Integer.parseInt((String) request.getParameter("contract"));
			
			
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
			
			try {
				if(production.getIdProducteur() != AuthorisationManager.getIdCompte(request.getSession())) {
					response.sendRedirect("/caweb");
					return;
				}
			} catch (AucunCompteLoggeException e1) {
				response.sendRedirect("/caweb");
				return;
			}
			
			if(validate) {
				contrat.validate();
				
				Integer date = Integer.parseInt(request.getParameter("dateDebut"));
				
				if(date == null)
					throw new InternalError();
				
				contrat.setDateDebut(date);
				
				try {
					contratDAO.updateContrat(contrat);
				} catch (DAOException e) {
					e.printStackTrace();
					throw new InternalError("Impossible de valider le contrat.");
				}
			} else {
				try {
					contratDAO.deleteContrat(contrat.getIdContrat());
				} catch (DAOException e) {
					throw new InternalError();
				}
			}
		}
		
		Producteur producteur;
		try {
			producteur = new Producteur(AuthorisationManager.getIdCompte(request.getSession()));
		} catch (AucunCompteLoggeException e1) {
			response.sendRedirect("/caweb");
			return;
		}
    	
		ContratDAO contratDao = new ContratDAO(ds);
		HashMap<Contrat, List<String>> valides = new HashMap<Contrat, List<String>>();
		HashMap<Contrat, List<String>> invalides = new HashMap<Contrat, List<String>>();
		List<Contrat> contrats = new ArrayList<Contrat>();
		List<Contrat> contratsValides = new ArrayList<Contrat>();
		List<Contrat> contratsInvalides = new ArrayList<Contrat>();
		
    	try {
			contrats = contratDao.getListeContrat(producteur);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new InternalError();
		}
		ConsommateurDAO consommateur = new ConsommateurDAO(ds);
		ProductionDAO prod = new ProductionDAO(ds);
		Production production = null;
		Consommateur conso = null;
		for(Contrat c : contrats){
			List<String> data = new ArrayList<String>();
			try {
				production = prod.getProduction(c.getIdProduction());
				conso = consommateur.getConsommateur(c.getIdConsomateur());
			} catch (DAOException e) {
				e.printStackTrace();
			}
			data.add(conso.getNom());
			data.add(conso.getAdresse());
			data.add(conso.getEmail());
			data.add(production.getProduit());
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
