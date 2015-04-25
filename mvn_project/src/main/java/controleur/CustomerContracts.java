package controleur;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.Contrat;
import controleur.AuthorisationManager.Permission;
import dao.ContratDAO;
import dao.DAOException;

/**
 * Servlet implementation class CustomerContracts
 */
@WebServlet("/CustomerContracts")
public class CustomerContracts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/caweb")
	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerContracts() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean success = AuthorisationManager.getPermission(request, response, Permission.CONSOMATEUR);

		if(request.getParameter("contrat") != null) {
			Integer idContrat = Integer.parseInt(request.getParameter("contrat"));
			if(idContrat != null) {
				ContratDAO contratDAO = new ContratDAO(ds);
				Contrat contrat;
				try {
					contrat = contratDAO.getContrat(idContrat);

					if(contrat != null && contrat.getIdConsomateur() == AuthorisationManager.getIdCompte(request.getSession())) {
						contratDAO.addContrat(contrat);
					}
				} catch (DAOException e) {
					throw new InternalError();
				}
			}
		}

		if(!success) {
			response.sendRedirect("/caweb");
			return;
		}

		ContratDAO contratDao = new ContratDAO(ds);
		List<Contrat> contrats ;
		int idConsomateur = AuthorisationManager.getIdCompte(request.getSession());

		//contrats = contratDao.getListeContrat(idConsomateur);


		getServletContext()
		.getRequestDispatcher("/WEB-INF/customerContracts.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
