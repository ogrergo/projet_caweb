package controleur;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.Consommateur;
import model.Permanence;
import controleur.AuthorisationManager.Permission;
import dao.CompteDAO;
import dao.ConsommateurDAO;
import dao.DAOException;
import dao.PermanenceDAO;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @Resource(name = "jdbc/caweb")
    private DataSource ds;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthorisationManager.getPermission(request, response, Permission.RESPONSABLE_PLANNING))
			return;
		
		request.setAttribute("semaines", Planning.getWeeksOfMonth());
		request.setAttribute("mois", Planning.getMonthName(Planning.getCurrentMonth()));
		request.setAttribute("liste_dispos", getListeDispos());
		request.setAttribute("is_inactif", getIsInactif());
		
		
		HashMap<Integer, Consommateur> livreurs1 = new HashMap<Integer, Consommateur>();
		HashMap<Integer, Consommateur> livreurs2 = new HashMap<Integer, Consommateur>();
		
		
		PermanenceDAO permanenceDAO = new PermanenceDAO(ds);
		CompteDAO compteDAO = new CompteDAO(ds);
		for(Integer s : Planning.getWeeksOfMonth()) {
			try {
				Permanence permanence = permanenceDAO.getPermanence(s);
				if(permanence!=null) {
					
					livreurs1.put(s, (Consommateur) compteDAO.getCompte(permanence.getIdConsommateur1()));
					livreurs2.put(s, (Consommateur) compteDAO.getCompte(permanence.getIdConsommateur2()));
				}
			} catch (DAOException e) {
				e.printStackTrace();
			}			
		}
		
		request.setAttribute("livreurs1", livreurs1);
		request.setAttribute("livreurs2", livreurs2);
		
		
		actionStatistique(request);
		
		
		
		getServletContext()
        .getRequestDispatcher("/WEB-INF/admin.jsp")
        .forward(request, response);
	}
	
	private void actionStatistique(HttpServletRequest request) {
		ConsommateurDAO consommateurDAO = new ConsommateurDAO(ds);
		PermanenceDAO permanenceDAO = new PermanenceDAO(ds);
		
		try {
			List<Consommateur> listConsommateur = consommateurDAO.getListeConsommateur();
			HashMap<Consommateur, Integer> hashmap = new HashMap<Consommateur, Integer>();
			HashMap<Consommateur, Integer> percentages = new HashMap<Consommateur, Integer>();
			
			for(Consommateur consommateur : listConsommateur) {
				hashmap.put(consommateur, permanenceDAO.getNbPermanence(consommateur));
			}
			
			int maxPermanence = Collections.max(hashmap.values());
			
			for(Consommateur consommateur : listConsommateur) {
				percentages.put(consommateur, permanenceDAO.getNbPermanence(consommateur)*100 / maxPermanence);
			}
			
			
			
			request.setAttribute("liste_consommateur", listConsommateur);
			request.setAttribute("nb_permanences", hashmap);
			request.setAttribute("percentages", percentages);
			
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthorisationManager.getPermission(request, response, Permission.RESPONSABLE_PLANNING))
			return;
		
		
		PermanenceDAO permanenceDAO = new PermanenceDAO(ds);
		
		
		for(Integer s : Planning.getWeeksOfMonth()) {
			try {
				permanenceDAO.deleteAllPermananceByIdSemaine(s);
				String livreur1_txt = request.getParameter("livreur1_" + s);
				String livreur2_txt = request.getParameter("livreur2_" + s);
				if(livreur1_txt!=null && livreur2_txt != null) {
					int livreur1 = Integer.parseInt(request.getParameter("livreur1_" + s));
					int livreur2 = Integer.parseInt(request.getParameter("livreur2_" + s));
					
					Permanence permanence = new Permanence(s, livreur1, livreur2);
					permanenceDAO.addPermanence(permanence);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (DAOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		response.sendRedirect("admin");
	}
	
	private HashMap<Integer, Boolean> getIsInactif() {
		HashMap<Integer, Boolean> hashmap = new HashMap<Integer, Boolean>();
		ConsommateurDAO consomateurDAO = new ConsommateurDAO(ds);
		for(Integer s : Planning.getWeeksOfMonth()) {
			List<Consommateur> listDisp = null;
			try {
				listDisp = consomateurDAO.getListeConsommateur(s.intValue());
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
			
			hashmap.put(s, listDisp!=null&&listDisp.isEmpty());	
		}
		return hashmap;
	}
	
	protected HashMap<Integer, List<Consommateur>> getListeDispos(){
		HashMap<Integer, List<Consommateur>> hashmap = new HashMap<Integer, List<Consommateur>>();
		ConsommateurDAO consomateurDAO = new ConsommateurDAO(ds);
		for(Integer s : Planning.getWeeksOfMonth()) {
			List<Consommateur> listDisp = null;
			try {
				listDisp = consomateurDAO.getListeConsommateur(s.intValue());
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
			if(listDisp!=null&&listDisp.isEmpty()) {
				try {
					listDisp = consomateurDAO.getListeConsommateurInactif(s);
				} catch (DAOException e) {
					e.printStackTrace();
				}
				System.out.println(listDisp.isEmpty());
			}
			hashmap.put(s, listDisp);	
		}
		return hashmap;
	}
}
