package controleur;

import dao.DAOException;
import dao.ProductionDAO;
import java.io.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;


/**
 * Le contrôleur de l'application.
 */
@WebServlet(name = "ListProduction", urlPatterns = {"/listProduction"})
public class ListProduction extends HttpServlet {

    @Resource(name = "jdbc/caweb")
    private DataSource ds;

    /**
     * La méthode principale d'aiguillage.
     */
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

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
        request.setAttribute("productions", productionDAO.getListeProductionsByIdProducteur(AuthorisationManager.getIdCompte(request.getSession(true))));
        getServletContext().getRequestDispatcher("/WEB-INF/listProduction.jsp").forward(request, response);
    }

 
}
