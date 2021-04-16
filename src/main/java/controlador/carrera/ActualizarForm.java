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
 * @author nivek
 */
@WebServlet(name = "ActualizarForm", urlPatterns = {"/ActualizarForm"})
public class ActualizarForm extends HttpServlet {

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
            out.println("<title>Servlet ActualizarForm</title>");            
            out.println("</head>");
            out.println("<body>");
            String msj = "";
            Carrera c = new Carrera();
            c.setIdCarrera(Integer.parseInt(request.getParameter("id")));
            CarreraDAO dao = new CarreraDAO();
            try {

                c = dao.read(c);
                System.out.println(dao.read(c));
            } catch (SQLException ex) {
                Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            //  c.setNombreCarrera(nombre);
//        c.setDuracion(duracion);
          if(c!=null){  
            out.println("<form name='frmDatos' method='post' action='ActualizarCarrera?id="+c.getIdCarrera()+"'>");
            out.println("<label name='txtId'>id Carrera: "+c.getIdCarrera()+"</label><br>");
            out.println("<label>Nombre Carrera: </label><br>");
            out.println("<input type='text' id='txtNombre' name='txtNombre' value='"+c.getNombreCarrera()+"'/><br>");
            out.println("<label>Duracion Carrera: </label><br>");
            out.println("<input type='text' id='txtDuracion' name='txtDuracion' value='"+c.getDuracion()+"'/><br>");
            out.println("<input type='submit' value='ActualizarCarrera' name='btnActualizar'/><br>");
            out.println("</form>");
            out.println("<a href='MostraCarreras'>Listado de Carreras</a>");
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
