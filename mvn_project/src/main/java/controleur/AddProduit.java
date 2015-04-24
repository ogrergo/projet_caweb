package controleur;

import dao.ProductionDAO;
import dao.ProduitDAO;
import dao.DAOException;

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

import controleur.AuthorisationManager.Permission;
import model.Produit;

/**
 * Servlet implementation class NewProduit
 */
@WebServlet("/AddProduit")
public class AddProduit extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Resource(name = "jdbc/caweb")
    private DataSource ds;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProduit() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(!AuthorisationManager.havePermission(request.getSession(), Permission.PRODUCTEUR)) {
    		response.sendRedirect("/caweb");
    		return;
    	}
    	
        getServletContext().getRequestDispatcher("/WEB-INF/addProduit.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String nomProduit = request.getParameter("nomProduit");
    	String unites = request.getParameter("unites");
    	Integer duree = Integer.parseInt(request.getParameter("duree"));
    	
    	if(nomProduit == null || unites == null || 
    			nomProduit == null || 
    			!AuthorisationManager.havePermission(request.getSession(true), Permission.PRODUCTEUR)) {
            request.setAttribute("erreur", "Données invalides.");
    		getServletContext().getRequestDispatcher("/WEB-INF/addProduit.jsp").forward(request, response);
            return;
    	}
    	
    	String[] unitesTab = unites.split(";");
    	
        try {
        	ProductionDAO productionDAO = new ProductionDAO(ds);
        	productionDAO.ajouterProduction(nomProduit, unitesTab, duree, 
        			AuthorisationManager.getIdCompte(request.getSession()));
           
            response.sendRedirect("/caweb");
        } catch (DAOException ex) {
        	throw new InternalError();
        }

    }
}
