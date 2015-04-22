package controleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
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
		
		request.setAttribute("semaines", Planning.getWeeksOfMonth());
		request.setAttribute("mois", Planning.getMonthName(Planning.getCurrentMonth()));
		request.setAttribute("liste_dispos", getListeDispos());
		
		getServletContext()
        .getRequestDispatcher("/WEB-INF/admin.jsp")
        .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	protected ArrayList<String> getListeDispos(){
		ArrayList<String> listDisp = new ArrayList<String>();
		listDisp.add("Michel");
		listDisp.add("Jean-Mi");
		listDisp.add("Roger");
		return listDisp;
	}
}
