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
import controleur.AuthorisationManager.Permission;
import dao.DAOException;
import dao.ProductionDAO;


/**
 * Le contrôleur de l'application.
 */
@WebServlet(name = "ListProduction", urlPatterns = {"/listProduction"})
public class ListProduction extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8790923473446419315L;
	@Resource(name = "jdbc/caweb")
    private DataSource ds;

    /**
     * La méthode principale d'aiguillage.
     */
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
    	if(!AuthorisationManager.getPermission(request, response, Permission.PRODUCTEUR))
         	return;
    	
        ProductionDAO productionDAO = new ProductionDAO(ds);

        try {
            actionAfficher(request, response, productionDAO);
        } catch (DAOException e) {
            request.setAttribute("erreurMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ListProduction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actionAfficher(HttpServletRequest request,
            HttpServletResponse response,
            ProductionDAO productionDAO) throws DAOException, ServletException, IOException, SQLException {
        try {
			request.setAttribute("productions", productionDAO.getListeProductionsByIdProducteur(AuthorisationManager.getIdCompte(request.getSession(true))));
		} catch (AucunCompteLoggeException e) {
			response.sendRedirect("/caweb");
			return;
		}
        getServletContext().getRequestDispatcher("/WEB-INF/listProduction.jsp").forward(request, response);
    }

 
}
