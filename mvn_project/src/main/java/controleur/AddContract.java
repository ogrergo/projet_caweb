package controleur;

import dao.ProduitDAO;
import dao.DAOException;
import dao.UniteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class NewContract
 */
@WebServlet("/AddContract")
public class AddContract extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Resource(name = "jdbc/caweb")
    private DataSource ds;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddContract() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");
        ProduitDAO produitDAO = new ProduitDAO(ds);
        UniteDAO uniteDAO = new UniteDAO(ds);

        try {
            if (action == null) {
                actionAfficher(request, response, produitDAO, uniteDAO);
            } else if (action.equals("addContract")) {
                // actionAjouter(request, response, ouvrageDAO);
            } else if (action.equals("addProduit")) {
                //actionSupprimer(request, response, produitDAO);
            } else if (action.equals("addUnite")) {
                //actionModifier(request, response, uniteDAO);
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/controleurErreur.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("erreurMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Quantité = request.getParameter("quantite");
        String Date = request.getParameter("date");
        String Durée = request.getParameter("duree");
        response.getWriter().println("Demande d'un contrat pour le produit PRODUIT : Quantité : " + Quantité + ", Date : " + Date + ", Durée : " + Durée);
    }

    private void actionAfficher(HttpServletRequest request, HttpServletResponse response,
            ProduitDAO produitDAO, UniteDAO uniteDAO) throws DAOException, ServletException, IOException, SQLException {
        request.setAttribute("produits", produitDAO.getListeProduits());
        request.setAttribute("unites", uniteDAO.getListeUnites());
        getServletContext().getRequestDispatcher("/WEB-INF/addContract.jsp").forward(request, response);
    }

}
