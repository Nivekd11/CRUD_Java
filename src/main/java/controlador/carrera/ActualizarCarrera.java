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
@WebServlet(name = "ActualizarCarrera", urlPatterns = {"/ActualizarCarrera"})
public class ActualizarCarrera extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
             out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<h1>Hola</h1>");
            out.println("<title>Servlet Agregar Carrera </title>");
            out.println("<head>");
            out.println("</head>");
            out.println("<body>");

            String msj = "";
            Carrera c = new Carrera();
            c.setIdCarrera(Integer.parseInt(request.getParameter("nombreId")));
            c.setNombreCarrera(request.getParameter("txtNombre"));
            c.setDuracion(Integer.parseInt(request.getParameter("txtDuracion")));
            CarreraDAO dao = new CarreraDAO();
            //  c.setNombreCarrera(nombre);
//        c.setDuracion(duracion);

            try {
                dao.update(c);
                msj = "La carrera se agrago correctamente";
                //out.println(dao.readAll());
            } catch (SQLException ex) {
                 msj = "No se puedo agregar correctamente";
                Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            out.println("<div align='center'>");
            out.println(msj);
            out.println("<br/>");
            out.println("<a href='MostraCarreras'>Listado de Carreras</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

/*
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
                out.println("<form name='frmDatos' method='post' >");
                out.println("<table align=>");
                out.println("<tr>");
                out.println("<th>Nombre de la Carrera</th>");
                out.println("<td>");
                out.println("<input type='text' name='txtNombre' id='txtNombre' placeholder='Nombre de la carrera' required='required'/>");
                out.println("</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th>Duracion</th>");
                out.println("<td>");
                out.println("<input type='number' name='txtDuracion' value=\"4\" min=\"3\" max=\"6\" step=\"1\" required='required'/>");
                out.println("</td>");
                out.println("</tr>");
                out.println("<div align='center'>");
                out.println("<a href='MostraCarreras'>Mostar Carreras</a>");
               out.println("<tr>");
               out.println("<td colspan=\"2\">");
               out.println("<input type=\"submit\" value=\"Enviar\" name=\"btnEnvia\"/>");
               out.println("</td>");
               out.println("</tr>");
               out.println("</table>");
                
                out.println("</div>");
                out.println(msj);
            }
            
             out.println("<div align='center'>");
            out.println(msj);
            out.println("<br/>");
            out.println("<a href='MostraCarreras'>Listado de Carreras</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
*/
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
        processRequest(request, response);
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
        processRequest(request, response);
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