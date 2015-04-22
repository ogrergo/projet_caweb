package controleur;

import dao.ProduitDAO;
import dao.DAOException;
import dao.UniteDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import model.Production;

/**
 * Servlet implementation class NewProduction
 */
@WebServlet("/AddProduction")
public class AddProduction extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Resource(name = "jdbc/caweb")
    private DataSource ds;
    private String[] tabUnites;

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

        //D'abord ajout de la production dans la BD
     /*   ProductionDAO productionDAO = new ProductionDAO(ds);
         Production production = productionDAO.ajouterProducion(request.getParameter("produitSelect"),
         request.getParameter("unitesSelect"),
         request.getParameter("duree")); */
        String Date = request.getParameter("produitSelect");
        String Durée = request.getParameter("unitesSelect");
        String[] Durée2 = request.getParameterValues("unitesSelect");

        //response.getWriter().println("Demande d'un production pour le produit PRODUIT : Quantité : " + ", Date : " + Date + ", Durée : " + Durée + Durée2);

    }
}
