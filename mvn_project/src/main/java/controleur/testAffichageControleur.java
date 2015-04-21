/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.DAOException;
import dao.ProducteurDAO;
import java.io.IOException;
import java.io.PrintWriter;
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

/**
 *
 * @author robin
 */
@WebServlet(name = "testAffichageControleur", urlPatterns = {"/testAffichageControleur"})
public class testAffichageControleur extends HttpServlet {

//    @Resource(name = "jdbc/ensioracle1")
    private DataSource ds;

    /**
     * La méthode principale d'aiguillage.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.getWriter().println("COUCOU");
       /* PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        ProducteurDAO ProducteurDAO = new ProducteurDAO(ds);
        try {
            if (action == null) {
                actionAfficher(request, response, ProducteurDAO);
            } else {
                //... // renvoi vers une page d'erreur controleurErreur.jsp
            }
        } catch (DAOException e) {
            //... // renvoi vers une page d'erreur bdErreur.jsp
        } catch (SQLException ex) {
            Logger.getLogger(testAffichageControleur.class.getName()).log(Level.SEVERE, null, ex);
        } */
    }

    private void actionAfficher(HttpServletRequest request,
            HttpServletResponse response,
            ProducteurDAO producteurDAO)
            throws DAOException, ServletException, IOException, SQLException {
        request.setAttribute("producteurs", producteurDAO.getListeProducteurs());
        getServletContext()
                .getRequestDispatcher("/WEB-INF/listProducteur.jsp")
                .forward(request, response);
    }

}
