package controleur;

import dao.DAOException;
import dao.ProducteurDAO;
import java.io.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import oracle.jdbc.OracleDriver;

/**
 * Le contrôleur de l'application.
 */
@WebServlet(name = "TestControleur", urlPatterns = {"/testControleur"})
public class TestControleur extends HttpServlet {

    @Resource(name = "jdbc/caweb")
    private DataSource ds;

    /**
     * La méthode principale d'aiguillage.
     */
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");
        ProducteurDAO producteurDAO = new ProducteurDAO(ds);

        try {
            actionAfficher(request, response, producteurDAO);

        } catch (DAOException e) {
            request.setAttribute("erreurMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(TestControleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actionAfficher(HttpServletRequest request,
            HttpServletResponse response,
            ProducteurDAO producteurDAO) throws DAOException, ServletException, IOException, SQLException {
        request.setAttribute("producteurs", producteurDAO.getListeProducteurs());
        getServletContext().getRequestDispatcher("/WEB-INF/listProducteur.jsp").forward(request, response);
    }

 
}
