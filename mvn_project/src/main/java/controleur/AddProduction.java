package controleur;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import controleur.AuthorisationManager.AucunCompteLoggeException;
import dao.DAOException;
import dao.ProductionDAO;
import dao.ProduitDAO;
import dao.UniteDAO;

/**
 * Servlet implementation class NewProduction
 */
@WebServlet("/AddProduction")
public class AddProduction extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Resource(name = "jdbc/caweb")
    private DataSource ds;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProduction() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProduitDAO produitDAO = new ProduitDAO(ds);
        UniteDAO uniteDAO = new UniteDAO(ds);

        try {
            actionAfficher(request, response, produitDAO, uniteDAO);
        } catch (Exception e) {
            request.setAttribute("erreurMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        }
    }

    private void actionAfficher(HttpServletRequest request, HttpServletResponse response,
            ProduitDAO produitDAO, UniteDAO uniteDAO) throws DAOException, ServletException, IOException, SQLException {
        request.setAttribute("produits", produitDAO.getListeProduits());
        request.setAttribute("unites", uniteDAO.getListeUnites());
        getServletContext().getRequestDispatcher("/WEB-INF/addProduction.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        try {
            String produit = request.getParameter("produitSelect");
            String[] unites = request.getParameterValues("unitesSelect");
            int duree = Integer.parseInt(request.getParameter("duree"));
            ProductionDAO productionDAO = new ProductionDAO(ds);
            try {
				productionDAO.ajouterProduction(produit, unites, duree, AuthorisationManager.getIdCompte(request.getSession(true)));
			} catch (AucunCompteLoggeException e) {
				response.sendRedirect("/caweb");
				return;
			}
            response.sendRedirect("/caweb");
        } catch (DAOException ex) {
            Logger.getLogger(AddProduit.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
