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
		response.sendRedirect("admin");
	
	}
	protected ArrayList<ArrayList<String>> getListeDispos(){
		ArrayList<String> listDisp = new ArrayList<String>();
		listDisp.add("Michel");
		listDisp.add("Jean-Mi");
		listDisp.add("Roger");
		ArrayList<String> listDisp2 = new ArrayList<String>();
		listDisp2.add("René");
		listDisp2.add("Jean-Mi");
		listDisp2.add("Roger");
		ArrayList<String> listDisp3 = new ArrayList<String>();
		listDisp3.add("René");
		listDisp3.add("Michou");
		listDisp3.add("Anne");
		ArrayList<String> listDisp4 = new ArrayList<String>();
		listDisp4.add("Mikeline");
		listDisp4.add("Robin");
		listDisp4.add("Louis van Beurden");
		ArrayList<ArrayList<String>> listList = new ArrayList<ArrayList<String>>();
		listList.add(listDisp);
		listList.add(listDisp2);
		listList.add(listDisp3);
		listList.add(listDisp4);
		return listList;
	}
}
