package controleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Available
 */
@WebServlet("/Available")
public class Available extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		request.setAttribute("semaines", getSemaines());
		
		getServletContext()
        .getRequestDispatcher("/WEB-INF/available.jsp")
        .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	public ArrayList<Integer> getSemaines(){
		ArrayList<Integer> semaines = new ArrayList<Integer>();
		int current = Planning.getWeekNumber();
		for (int i=0; i<getMaxContrats(); i++){
		semaines.add(current + i);
		}
		return semaines;
	}
	
	public int getMaxContrats(){
		return 12;
	}
	
}
