package controleur;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.Consommateur;
import model.Permanence;
import model.Production;
import model.Unite;
import controleur.AuthorisationManager.AucunCompteLoggeException;
import controleur.AuthorisationManager.Permission;
import dao.CompteDAO;
import dao.DAOException;
import dao.PermanenceDAO;
import dao.ProductionDAO;
import dao.UniteDAO;

/**
 * Servlet implementation class Accueil
 */
@WebServlet(name = "Accueil", urlPatterns = {"/Accueil"})
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Resource(name = "jdbc/caweb")
    private DataSource ds;

    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accueil() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    private void controleurAcceuilVisiteur(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
    	ProductionDAO productionDAO = new ProductionDAO(ds);
    	UniteDAO uniteDAO = new UniteDAO(ds);
    	    	
		List<Production> productions = null;
		
		try {
			productions = productionDAO.getListeProduction();
		} catch (DAOException e) {
			e.printStackTrace();
			throw new InternalError();
		}
		
		HashMap<Production, List<Unite>> unites = new HashMap<Production, List<Unite>>();
		
		for(Production production : productions) {
			try {
				unites.put(production, uniteDAO.getListeUnites(production));
			} catch (DAOException e) {
				e.printStackTrace();
				throw new InternalError();
			}
		}
		
		request.setAttribute("production", productions);
		request.setAttribute("unites", unites);
		
		// Liste des permanences
		
		boolean isConsommateur = AuthorisationManager.havePermission(request.getSession(true), Permission.CONSOMMATEUR);
		request.setAttribute("isConsommateur", isConsommateur);
		
		if(isConsommateur) {
			PermanenceDAO permanenceDAO = new PermanenceDAO(ds);
			
			try {
				int currentUserId = AuthorisationManager.getIdCompte(request.getSession(true));
				List<Permanence> listPermanence = permanenceDAO.getPermanenceByUserId(currentUserId);
				HashMap<Permanence, Consommateur> hashmap = new HashMap<Permanence, Consommateur>();
				request.setAttribute("listPermanence", listPermanence);
				
				for(Permanence p : listPermanence) {
					Consommateur binome = null;
					CompteDAO compteDAO = new CompteDAO(ds);
					if(currentUserId!=p.getIdConsommateur1()) {
						binome = (Consommateur) compteDAO.getCompte(p.getIdConsommateur1());
					} else {
						binome = (Consommateur) compteDAO.getCompte(p.getIdConsommateur2());
					}
					hashmap.put(p, binome);
				}
				request.setAttribute("binomes", hashmap);
			
			} catch (DAOException e) {
				e.printStackTrace();
			} catch (AucunCompteLoggeException e) {
				e.printStackTrace();
			}
		}
		
		getServletContext()
        .getRequestDispatcher("/WEB-INF/accueil.jsp")
        .forward(request, response);
    }
    
    private void controleurAcceuilProducteur(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
    	response.sendRedirect("/caweb/productorContracts");
    }
    
    private void controleurAcceuilResponsablePlanning(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
    	response.sendRedirect("/caweb/admin");
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		if(!AuthorisationManager.haveCredential(session) || AuthorisationManager.havePermission(session, Permission.CONSOMMATEUR)) {
			controleurAcceuilVisiteur(request, response);
		} else if(AuthorisationManager.havePermission(session, Permission.PRODUCTEUR)) {
			controleurAcceuilProducteur(request, response);
		} else if(AuthorisationManager.havePermission(session, Permission.RESPONSABLE_PLANNING)) {
			controleurAcceuilResponsablePlanning(request, response);
		} else
			throw new InternalError("Impossible d'afficher une page d'acceuil pour ce type de compte.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
