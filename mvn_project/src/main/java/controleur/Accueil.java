package controleur;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.Production;
import controleur.AuthorisationManager.Permission;
import dao.DAOException;
import dao.ProductionDAO;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		if(!AuthorisationManager.haveCredential(session) || AuthorisationManager.havePermission(session, Permission.CONSOMATEUR)) {
			ProductionDAO productionDAO = new ProductionDAO(ds);
			List<Production> production = null;
			
			try {
				production = productionDAO.getListeProduction();
			} catch (DAOException e) {
				e.printStackTrace();
				throw new InternalError();
			}
			
			request.setAttribute("production", production);
			
			getServletContext()
	        .getRequestDispatcher("/WEB-INF/accueil.jsp")
	        .forward(request, response);
			
		} else if(AuthorisationManager.havePermission(session, Permission.PRODUCTEUR)) {
			getServletContext()
	        .getRequestDispatcher("/WEB-INF/home-producteur.jsp")
	        .forward(request, response);
		} else if(AuthorisationManager.havePermission(session, Permission.RESPONSABLE_PLANNING)) {
			getServletContext()
	        .getRequestDispatcher("/WEB-INF/home-responsablePlanning.jsp")
	        .forward(request, response);
		} else
			throw new InternalError("Impossible d'afficher une page d'acceuil pour ce type de compte.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
