/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.alumno;

import dao.AlumnoDAO;
import dto.AlumnoDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(name = "AlumnoServlet", urlPatterns = {"/AlumnoServlet"})
public class AlumnoServlet extends HttpServlet {

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
        
        String option = request.getParameter("opcion");
        if(option.equals("listaDeAlumnos")){
            listaDeAlumnos(request, response);
        }else{
            if(option.equals("agregar")){
                agregarAlumno(request,response); 
            }else{
                if(option.equals("actualizar")){
                    actualizarAlumno(request,response);
                    }else{
                        if(option.equals("eliminar")){
                            eliminarAlumno(request,response);
                        }else{
                            if(option.equals("ver")){
                            verAlumno(request,response);
                            }else{
                                if(option.equals("guardar")){
                                    almacenarAlumno(request,response);
                                }
                                }
                                
                            }
                        
                        }
                }
        }

        /*
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AlumnoServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AlumnoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
         */
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

    private void listaDeAlumnos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Listado de Alumnos</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js'></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<nav class=\"navbar navbar-expand-lg navbar-light bg-light nav-bg\">");
            out.println("<div class=\"container-fluid\">");
            out.println("<a class=\"navbar-brand\" href=\"index.html\">Gestion de Programas Educativos</a>");
            out.println("<button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">");
            out.println("<span class=\"navbar-toggler-icon\"></span>");
            out.println("</button>");
            out.println("<div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">");
            out.println("<ul class=\"navbar-nav me-auto mb-2 mb-lg-0\">");
            out.println("<li class=\"nav-item\">");
            out.println("  <a class=\"nav-link active\" aria-current=\"page\" href=\"nuevoAlumno.html\">Nuevo Alumno</a>");
            out.println("</li>");
            out.println("<li class=\"nav-item\">");
            out.println("<a class=\"nav-link\" href=\"nuevaCarrera.html\">Agregar Carrera</a>");
            out.println("</li>");
            out.println(" <li class=\"nav-item\">");
            out.println(" <a class=\"nav-link\" href=\"MostrarCarreras\">Mostrar Carreras</a>");
            out.println("</li>");
            out.println("<li class=\"nav-item\">");
            out.println("<a class=\"nav-link\" href=\"AlumnoServlet?opcion=listaDeAlumnos\">Mostrar Alumnos</a>");
            out.println(" </li>");
            out.println("</ul>");
            out.println("</div>");
            out.println("</div>");
            out.println("</nav>");
            out.println("<div class='container'>");
            out.println("<div class='card-boder-info mb-3'>");
            out.println("<div class='card-body'>");
            out.println("<h1 class=\"display-4\" align=\"center\">Listado De Alumnos</h1>");
            out.println("<table class='table table-striped'>");
            out.println("<tr>");
            out.println("<th>ID Alumno</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Apellido Paterno</th>");
            out.println("<th>Apellido Materno</th>");
            out.println("<th>Email</th>");
            out.println("<th>Telefono</th>");
            out.println("<th>Actualizar</th>");
            out.println("<th>Borrar</th>");
            out.println("</tr>");
            
            AlumnoDAO dao = new AlumnoDAO();
            try{
                List lista = dao.readAll();
                for (int i = 0; i < lista.size(); i++) {
                    AlumnoDTO dto = (AlumnoDTO) lista.get(i);
                    out.println("<tr>");
                    out.println("<td>");
                    out.println("<a href='AlumnoServlet?opcion=ver&id="
                            +dto.getEntidad().getIdAlumno()+"' class='btn btn-success'>"
                            +dto.getEntidad().getIdAlumno()+"</a>");
                    out.println("</td>");
                    
                    out.println("<td>");
                    out.println(dto.getEntidad().getNombre());
                    out.println("</td>");
                    
                     out.println("<td>");
                    out.println(dto.getEntidad().getPaterno());
                    out.println("</td>");
                    
                     out.println("<td>");
                    out.println(dto.getEntidad().getMaterno());
                    out.println("</td>");
                    
                     out.println("<td>");
                    out.println(dto.getEntidad().getEmail());
                    out.println("</td>");
                    
                     out.println("<td>");
                    out.println(dto.getEntidad().getTelefono());
                    out.println("</td>");
                    
                    out.println("<td>");
                    out.println("<a href='AlumnoServlet?opcion=actualizar&id="
                            +dto.getEntidad().getIdAlumno()+"' class='btn btn-info'>Actualizar</a>");
                    out.println("</td>");
                    
                    
                    out.println("<td>");
                    out.println("<a href='AlumnoServlet?opcion=eliminar&id="
                            +dto.getEntidad().getIdAlumno()+"' class='btn btn-danger'>Eliminar</a>");
                    out.println("</td>");
                    
                    out.println("</tr>");
                    
                    
                }
            }catch(SQLException ex){
                Logger.getLogger(AlumnoServlet.class.getName()).log(Level.SEVERE,null,ex);
            }
            
            out.println("</table>");
            
            out.println("<div align='center'>");
            out.println("<br/>");
            out.println("<a href='alumnoForm.html' class='btn btn-primary'>Crear Alumno</a>");
            out.println("</div>");
            
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void agregarAlumno(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Actualizar Alumno</title>");            
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js'></script>");
            out.println("</head>");
            out.println("<body>");
            
            String msg = "";
            AlumnoDTO dto = new AlumnoDTO();
            dto.getEntidad().setNombre(request.getParameter("txtNombre"));
            dto.getEntidad().setPaterno(request.getParameter("txtPaterno"));
            dto.getEntidad().setMaterno(request.getParameter("txtMaterno"));
            dto.getEntidad().setEmail(request.getParameter("txtEmail"));
            dto.getEntidad().setTelefono(request.getParameter("txtTelefono"));
            dto.getEntidad().setIdCarrera(Integer.parseInt(request.getParameter("idCarrera")));
            System.out.println(request.getParameter("txtNombre"));
            AlumnoDAO dao = new AlumnoDAO();
            try {
                dao.create(dto);
                out.println("<div align='center' style='background-color:lightgreen;'>");
                msg = "El alumno fue agregado exitosamente";
            } catch (SQLException ex) {
                Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
                out.println("<div align='center' style='background-color:#FF6666;'>");
                msg = "Hubo un error al agregar el alumno";
            }
            out.println("<h5 class='card-title'>"+msg+"</h5>");
            out.println("<br/>");
            out.println("<a href='AlumnoServlet?opcion=listaDeAlumnos' class='btn btn-primary'>Lista de Alumnos<a/>");
            out.println("</div>");
            
            out.println("</body>");
            out.println("</html>");
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(AlumnoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarAlumno(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Actualizar Alumno</title>");            
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js'></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<div class='card-boder-info mb-3'>");
            out.println("<div class='card-body'>");
            out.println("<h1 class=\"display-4\" align=\"center\">Actualizar Alumno</h1>");
            
            AlumnoDTO dto = new AlumnoDTO();
            dto.getEntidad().setIdAlumno(Integer.parseInt(request.getParameter("id")));

            AlumnoDAO dao = new AlumnoDAO();
            try {
                dto = dao.read(dto);
            } catch (SQLException ex) {
                Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(dto != null){
                out.println("<form name='frmDatos' method='post' action='AlumnoServlet?opcion=guardar&id="+dto.getEntidad().getIdAlumno()+"'>");
                out.println("<table  class='table table-striped'>");
                 
                out.println("<tr>");
                out.println("<td>ID Alumno:</td><td>" + dto.getEntidad().getIdAlumno() + "</td>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<td>Nombre:</td><td><input type='text' value='"
                        + dto.getEntidad().getNombre() + "' name='txtNombre' id='txtNombre' placeholder='Nombre de la carrera' required='required'/> </td>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<td>Paterno:</td><td><input type='text' value='"
                        + dto.getEntidad().getPaterno() + "' name='txtPaterno' id='txtPaterno' placeholder='Nombre de la carrera' required='required'/> </td>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<td>Materno:</td><td><input type='text' value='"
                        + dto.getEntidad().getMaterno() + "' name='txtMaterno' id='txtMaterno' placeholder='Nombre de la carrera' required='required'/> </td>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<td>Correo:</td><td><input type='email' value='"
                        + dto.getEntidad().getEmail() + "' name='txtEmail' id='txtEmail' placeholder='Nombre de la carrera' required='required'/> </td>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<td>No° Boleta:</td><td><input type='text' value='"
                        + dto.getEntidad().getTelefono() + "' name='txtTelefono' id='txtTelefono' placeholder='Nombre de la carrera' required='required'/> </td>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<td>ID Carrera:</td><td><input type='number' value='"
                        + dto.getEntidad().getIdCarrera() + "' name='idCarrera' id='idCarrera' min='1' step='1' required='required'/> </td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<div align='center'>");
                out.println("<div align='center'><br/><input type='submit' value='Actualizar' class='btn btn-success' name='btnActualizar'></div>");
                out.println("</br>");

                out.println("<td><div align='center'><a href='AlumnoServlet?opcion=listaDeAlumnos' class='btn btn-primary'>Lista De Alumnos</a></div>");

                out.println("</div>");
                   
                
                
            }
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void verAlumno(HttpServletRequest request, HttpServletResponse response) throws IOException {
         response.setContentType("text/html;charset=UTF-8");
         request.setCharacterEncoding("UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Información del alumno</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js'></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<div class='card-boder-info mb-3'>");
            out.println("<div class='card-body'>");
            out.println("<h1 class=\"display-4\" align=\"center\">Ver Información</h1>");
            
            AlumnoDAO dao = new AlumnoDAO();
            AlumnoDTO dto = new AlumnoDTO();
            
            dto.getEntidad().setIdAlumno(Integer.parseInt(request.getParameter("id")));
            
             try {
                 dto = dao.read(dto);
             } catch (SQLException ex) {
                 Logger.getLogger(AlumnoServlet.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            if(dto != null){   
                out.println("<table class='table table-striped'>");
                out.println("<tr>");
                out.println("<th>ID Alumno</th><td>"+dto.getEntidad().getIdAlumno()+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th>Nombre</th><td>"+dto.getEntidad().getNombre()+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th>Apellido Paterno</th><td>"+dto.getEntidad().getPaterno()+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th>Apellido Materno</th><td>"+dto.getEntidad().getMaterno()+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th>Email</th><td>"+dto.getEntidad().getEmail()+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th>Telefono</th><td>"+dto.getEntidad().getTelefono()+"</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<div align='center'>");
                out.println("<a href='AlumnoServlet?opcion=eliminar&id="
                        + dto.getEntidad().getIdAlumno() + "' class='btn btn-danger'>Eliminar</a>");
                out.println("<br/>");
                out.println("<br/>");
                out.println("<a href='AlumnoServlet?opcion=listaDeAlumnos' class='btn btn-primary'>Lista De Alumnos</a>");
                out.println("</div>");
            }
            
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void almacenarAlumno(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(AlumnoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Actualizar Alumno</title>");    
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js'></script>");
           
            out.println("</head>");
            out.println("<body>");

            String msg = "";
            AlumnoDTO dto = new AlumnoDTO();
            
            dto.getEntidad().setNombre(request.getParameter("txtNombre"));
            dto.getEntidad().setPaterno(request.getParameter("txtPaterno"));
            dto.getEntidad().setMaterno(request.getParameter("txtMaterno"));
            dto.getEntidad().setEmail(request.getParameter("txtEmail"));
            dto.getEntidad().setTelefono(request.getParameter("txtTelefono"));
            dto.getEntidad().setIdCarrera(Integer.parseInt(request.getParameter("idCarrera")));
            dto.getEntidad().setIdAlumno(Integer.parseInt(request.getParameter("id")));

            AlumnoDAO dao = new AlumnoDAO();
            try {
                dao.update(dto);
                msg = "El alumno se actualizo exitosamente";
            } catch (SQLException ex) {
                Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
                msg = "Hubo un error al actualizar a el alumno";
            }
            out.println("<div align='center'>");
            out.println("<div class='card text-white bg-info mb-3' style='max-width: 40rem;'>");
            out.println("<h5 class='card-title'>"+msg+"</h5>");
            out.println("</div>");
            out.println("<br/>");
            out.println("<a href='AlumnoServlet?opcion=listaDeAlumnos' class='btn btn-primary'>Lista De Alumnos</a>");
            out.println("</div>");    
            
            out.println("</body>");
            out.println("</html>");
        } catch (IOException ex) {
            Logger.getLogger(AlumnoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } //To change body of generated methods, choose Tools | Templates.
    }

    private void eliminarAlumno(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Eliminación</title>");     
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js'></script>");
            out.println("</head>");
            out.println("<body>");     
            AlumnoDAO dao = new AlumnoDAO();
            AlumnoDTO dto = new AlumnoDTO();
            
            dto.getEntidad().setIdAlumno(Integer.parseInt(request.getParameter("id")));
            String msg = "";
             try {
                 dao.delete(dto);
                 msg = "Se ha eliminado al alumno exitosamente";
             } catch (SQLException ex) {
                 Logger.getLogger(AlumnoServlet.class.getName()).log(Level.SEVERE, null, ex);
                 msg = "Hubo un error al eliminar al alumno";
             }                
            out.println("<div align='center'>");
            out.println("<div class=\"card text-white bg-danger mb-3\" style=\"max-width: 40rem;\">");
            out.println("<h5 class='card-title'>"+msg+"</h5>");
            out.println("</div>");
            out.println("<br/>");
            out.println("<a href='AlumnoServlet?opcion=listaDeAlumnos' class='btn btn-primary'>Lista De Alumnos</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}




