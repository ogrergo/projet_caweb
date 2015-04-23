package controleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import dao.ContratDAO;
import dao.DAOException;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setAttribute("semaines", getSemaines(request));
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getServletContext()
        .getRequestDispatcher("/WEB-INF/available.jsp")
        .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Boolean> dispo = new ArrayList<Boolean>();
		
		try {
			for (int s : getSemaines(request)){
				dispo.add(request.getParameter("semaine"+s)!=null);
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("available");
		
	}
	
	public ArrayList<Integer> getSemaines(HttpServletRequest request) throws DAOException{
		ArrayList<Integer> semaines = new ArrayList<Integer>();
		int current = Planning.getWeekNumber();
		for (int i=0; i<getMaxContrats(request); i++){
		semaines.add(current + i);
		}
		return semaines;
	}
	
	public int getMaxContrats(HttpServletRequest request) throws DAOException{
		ContratDAO ContractDAO = new ContratDAO(ds);
		return ContractDAO.getSemaineContratMaxByIdConsommateur(AuthorisationManager.getIdCompte(request.getSession(true)));
		}
	
}
