package controleur;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
		boolean havePermission = AuthorisationManager.havePermission(request.getSession(true), Permission.RESPONSABLE_PLANNING);
		request.setAttribute("semaines", Planning.getWeeksOfMonth());
		request.setAttribute("mois", Planning.getMonthName(Planning.getCurrentMonth()));
		request.setAttribute("liste_dispos", getListeDispos());
		request.setAttribute("permission", havePermission);
		
		
		getServletContext()
        .getRequestDispatcher("/WEB-INF/admin.jsp")
        .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PermanenceDAO permanenceDAO = new PermanenceDAO(ds);
		
		
		for(Integer s : Planning.getWeeksOfMonth()) {
			try {
				permanenceDAO.deleteAllPermananceByIdSemaine(s);
				String livreur1_txt = request.getParameter("livreur1_" + s);
				String livreur2_txt = request.getParameter("livreur2_" + s);
				if(livreur1_txt!=null && livreur2_txt != null) {
					int livreur1 = Integer.parseInt(request.getParameter("livreur1_" + s));
					int livreur2 = Integer.parseInt(request.getParameter("livreur1_" + s));
					
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
	protected HashMap<Integer, ArrayList<Consommateur>> getListeDispos(){
		HashMap<Integer, ArrayList<Consommateur>> hashmap = new HashMap<Integer, ArrayList<Consommateur>>();
		ConsommateurDAO consomateurDAO = new ConsommateurDAO(ds);
		for(Integer s : Planning.getWeeksOfMonth()) {
			ArrayList<Consommateur> listDisp = new ArrayList<Consommateur>();
			try {
				for(Consommateur consommateur : consomateurDAO.getListeConsommateur(s.intValue())) {
					listDisp.add(consommateur);
				}
				
			} catch (DAOException e) {
				e.printStackTrace();
			}
			hashmap.put(s, listDisp);	
		}
		return hashmap;
	}
}
