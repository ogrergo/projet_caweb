/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.DAOException;
import dao.ProducteurDAO;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author jeanr
 */
public class Test1 {

    public static void main(String[] arg) throws DAOException, SQLException, NamingException {
    
        Context ctx = new InitialContext();
        DataSource ds = (DataSource)ctx.lookup("jdbc/jeanr");;
        ProducteurDAO producteurDAO = new ProducteurDAO(ds);

        System.out.println("Dnas afficher");
        System.out.println("Dnas afficher" + producteurDAO.getListeProducteurs().get(0));
    }     
}
