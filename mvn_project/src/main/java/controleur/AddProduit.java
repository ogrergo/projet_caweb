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

import controleur.AuthorisationManager.Permission;
import model.Produit;
import dao.DAOException;
import dao.ProduitDAO;

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
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!AuthorisationManager.getPermission(request, response, Permission.PRODUCTEUR))
        	return;
    	
    	try {
            actionAfficher(request, response);
        } catch (Exception e) {
            request.setAttribute("erreurMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        }
    }

    private void actionAfficher(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException, SQLException {
    	request.setAttribute("produitErreur", false);
        getServletContext().getRequestDispatcher("/WEB-INF/addProduit.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 if(!AuthorisationManager.getPermission(request, response, Permission.PRODUCTEUR))
         	return;
     	
        try {
            Produit prod = new Produit(request.getParameter("nomProduit"));
            //D'abord ajout de la production dans la BD
            ProduitDAO produitDAO = new ProduitDAO(ds);
            if (produitDAO.ajouterProduit(prod)) {
            	response.sendRedirect("/caweb/addProduction");
            } else {
            	request.setAttribute("produitErreur", true);
            	getServletContext().getRequestDispatcher("/WEB-INF/addProduit.jsp").forward(request, response);
            }
        } catch (DAOException ex) {
            Logger.getLogger(AddProduit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
