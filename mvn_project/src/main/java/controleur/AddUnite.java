package controleur;

import dao.UniteDAO;
import dao.DAOException;
import dao.UniteDAO;
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
import model.Unite;

/**
 * Servlet implementation class NewUnite
 */
@WebServlet("/AddUnite")
public class AddUnite extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Resource(name = "jdbc/caweb")
    private DataSource ds;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUnite() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            actionAfficher(request, response);
        } catch (Exception e) {
            request.setAttribute("erreurMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        }
    }

    private void actionAfficher(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException, SQLException {
        getServletContext().getRequestDispatcher("/WEB-INF/addUnite.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Unite prod = new Unite(request.getParameter("nomUnite"));
            //D'abord ajout de la production dans la BD
            UniteDAO uniteDAO = new UniteDAO(ds);
            uniteDAO.ajouterUnite(prod);
            response.sendRedirect("/caweb/addProduction");
        } catch (DAOException ex) {
            Logger.getLogger(AddUnite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddUnite.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
