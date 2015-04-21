package controleur;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.Produit;
import dao.DAOException;
import dao.ProduitDAO;

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
		ProduitDAO produitDAO = new ProduitDAO(ds);
		List<Produit> produits = null;
		try {
			produits = produitDAO.getListeProduits();
		} catch (DAOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Produit p : produits)
			System.out.println(p.getNomProduit());
		
		request.setAttribute("produits", produits);
		getServletContext()
        .getRequestDispatcher("/WEB-INF/accueil.jsp")
        .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
