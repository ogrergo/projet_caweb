package controleur;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
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

import model.Contrat;
import model.Disponibilite;
import dao.ContratDAO;
import dao.DAOException;
import dao.DisponibiliteDAO;
import dao.ProductionDAO;

/**
 * Servlet implementation class Available
 */
@WebServlet("/Available")
public class Available extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/caweb")
	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Available() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			DisponibiliteDAO disponibiliteDAO = new DisponibiliteDAO(ds);
			List<Integer> listKey = new ArrayList<Integer>();
			HashMap<Integer, Boolean> mapDispo = new HashMap<Integer, Boolean>();
			ArrayList<Disponibilite> listDispo = disponibiliteDAO.getListeDispoByIdConsommateur(AuthorisationManager
					.getIdCompte(request.getSession(true)));
			//Liste des semaines dispo
			ArrayList<Integer> semaineDispo = new ArrayList<Integer>();
			for (Disponibilite d : listDispo) {
				semaineDispo.add(d.getIdSemaine());
			}
			for (int s : getSemaines(request)) {
				mapDispo.put(s, semaineDispo.contains(s));
				listKey.add(new Integer(s));
			}
			request.setAttribute("listKey", listKey);
			
			request.setAttribute("mapDispo", mapDispo);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		getServletContext().getRequestDispatcher("/WEB-INF/available.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Integer> dispo = new ArrayList<Integer>();
		try {
			for (int s : getSemaines(request)) {
				if (request.getParameter("semaine" + s) != null) {
					dispo.add(s);
				}
			}
			DisponibiliteDAO disponnibiliteDAO = new DisponibiliteDAO(ds);
			// On efface toutes les anciennes dispo
			disponnibiliteDAO
					.deleteAllDispoByIdConsommateur(AuthorisationManager
							.getIdCompte(request.getSession(true)));
			// On met les nouvelles
			for (int s : dispo) {
				disponnibiliteDAO.ajouterDisponnibilite(new Disponibilite(s, AuthorisationManager.getIdCompte(request.getSession(true))));
			}
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.sendRedirect("available");

	}

	public ArrayList<Integer> getSemaines(HttpServletRequest request)
			throws DAOException {
		ArrayList<Integer> semaines = new ArrayList<Integer>();
		int current = Planning.getWeekNumber();
		ProductionDAO productionDAO = new ProductionDAO(ds);
		ContratDAO contratDAO = new ContratDAO(ds);
		ArrayList<Contrat> listcontrats = contratDAO.getListeContrat(AuthorisationManager.getIdCompte(request.getSession(true)));
		for (Contrat c : listcontrats) {
			int duree = productionDAO.getProduction(c.getIdProduction()).getDuree();
			for (int i = c.getDateDebut(); i <= duree + c.getDateDebut(); i++) {
				if (!semaines.contains(i)) {
					semaines.add(i);
				}
			}
		}
		Collections.sort(semaines);
		return semaines;
	}

	public int getMaxContrats(HttpServletRequest request) throws DAOException {
		ContratDAO ContractDAO = new ContratDAO(ds);
		return ContractDAO
				.getSemaineContratMaxByIdConsommateur(AuthorisationManager
						.getIdCompte(request.getSession(true)));
	}

}
