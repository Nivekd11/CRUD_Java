/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.carrera;

import com.mycompany.c15.Carrera;
import dao.CarreraDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adan
 */
@WebServlet(name = "verCarrera", urlPatterns = {"/verCarrera"})
public class verCarrera extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Ver Carrera</title>");
            out.println("</head>");
            out.println("<body>");

            Carrera c = new Carrera();
            // c.setNombreCarrera("Ingeniería en Inteligencia Artificial");
            c.setIdCarrera(Integer.parseInt(request.getParameter("id")));
            CarreraDAO dao = new CarreraDAO();
            try {

                c = dao.read(c);
                //System.out.println(dao.read(c));
            } catch (SQLException ex) {
                Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (c != null) {
                out.println("<table align='center'> ");
                out.println("<tr>");
                out.println("<td> Id carrera </td><td>" + c.getIdCarrera() + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td> Nombre Carrera </td><td>" + c.getNombreCarrera() + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td> Duracion carrera </td><td>" + c.getDuracion() + "</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<br/>");
                out.println("<div align='center'>");
                out.println("<a href='EliminarCarrera?id=" + c.getIdCarrera() + "'>Eliminar carrera</a>");
                out.println("<br/>");
                out.println("<a href='MostraCarreras'>Mostar Carreras</a>");
                out.println("</div>");

            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(verCarrera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(verCarrera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

 

}