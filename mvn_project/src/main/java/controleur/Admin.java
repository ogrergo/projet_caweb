package controleur;

import java.io.IOException;
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
import controleur.AuthorisationManager.Permission;
import dao.ConsommateurDAO;
import dao.DAOException;

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
		response.sendRedirect("admin");
	
	}
	protected HashMap<Integer, ArrayList<String>> getListeDispos(){
		HashMap<Integer, ArrayList<String>> hashmap = new HashMap<Integer, ArrayList<String>>();
		ConsommateurDAO consomateurDAO = new ConsommateurDAO(ds);
		for(Integer s : Planning.getWeeksOfMonth()) {
			ArrayList<String> listDisp = new ArrayList<String>();
			try {
				for(Consommateur consommateur : consomateurDAO.getListeConsommateur(s.intValue())) {
					listDisp.add(consommateur.getPrenom() + " " + consommateur.getNom());
				}
				
			} catch (DAOException e) {
				e.printStackTrace();
			}
			hashmap.put(s, listDisp);	
		}
		return hashmap;
	}
}
