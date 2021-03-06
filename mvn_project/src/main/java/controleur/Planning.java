package controleur;

import java.io.IOException;

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
import dao.DAOException;
import dao.PermanenceDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Servlet implementation class Planning
 */
@WebServlet("/Planning")
public class Planning extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Calendar calendar=Calendar.getInstance();

    @Resource(name = "jdbc/caweb")
	private DataSource ds;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Planning() {
        super();
		//calendar=Calendar.getInstance();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean havePermission = AuthorisationManager.havePermission(request.getSession(true), Permission.RESPONSABLE_PLANNING);
		request.setAttribute("mois", getMonthName(getCurrentMonth()));
		request.setAttribute("permission", havePermission);
		
		PermanenceDAO permanenceDAO = new PermanenceDAO(ds);
		CompteDAO compteDAO = new CompteDAO(ds);
		
		ArrayList<Integer> weeks = getWeeksOfMonth();
		HashMap<Integer, Consommateur> livreurs1 = new HashMap<Integer, Consommateur>();
		HashMap<Integer, Consommateur> livreurs2 = new HashMap<Integer, Consommateur>();
		
		for(Integer s : weeks) {
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
		
		request.setAttribute("weeks", weeks);
		request.setAttribute("livreurs1", livreurs1);
		request.setAttribute("livreurs2", livreurs2);
		
		getServletContext()
        .getRequestDispatcher("/WEB-INF/planning.jsp")
        .forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	public static int getCurrentMonth(){
		return calendar.get(Calendar.MONTH);
	}
	
	public static ArrayList<Integer> getWeeksOfMonth(){
		
		ArrayList<Integer> listWeeks = new ArrayList<Integer>();
		int numWeekInMonth = calendar.get(Calendar.WEEK_OF_MONTH);
		int numWeek = getWeekNumber();
		for (int i=1; i< calendar.getActualMaximum(Calendar.WEEK_OF_MONTH); i++){
			listWeeks.add(numWeek-numWeekInMonth + i);
		}
		return listWeeks;
	}
	
	public static int getWeekNumber(){
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
	public static String getMonthName(int nbMonth){
		
		String monthString;
		
		switch(nbMonth){
            case 1: monthString = "Janvier";
            		break;
            case 2: monthString = "Février";
            		break;
            case 3: monthString = "Mars";
            		break;
            case 4: monthString = "Avril";
            		break;
            case 5: monthString = "Mai";
            		break;
            case 6: monthString = "Juin";
            		break;
            case 7: monthString = "Juillet";
            		break;
            case 8: monthString = "Août";
            		break;
            case 9: monthString = "Septembre";
            		break;
            case 10:monthString = "Octobre";
            		break;
            case 11:monthString = "Novembre";
            		break;
            case 12:monthString = "Decembre";
            		break;
            default:monthString = "Invalid month";
            		break;
			}
		
		 return monthString;
	}
}
