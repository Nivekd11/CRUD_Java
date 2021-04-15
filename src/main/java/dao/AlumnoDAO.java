/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mycompany.c15.Carrera;
import dto.AlumnoDTO;
import entidades.Alumno;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adan
 */
public class AlumnoDAO {

    private static final String SQL_INSERT = "INSERT INTO Alumno (nombre,paterno,materno,email,noBoleta,idCarrera) values(?,?,?,?,?,?) idAlumno=?";

    private static final String SQL_UPDATE = "UPDATE Alumno set nombre=?,paterno=?,materno=?,email=?,noBoleta=?,idCarrera=? where idAlumno=?";

    private static final String SQL_DELETE = "DELETE FROM Alumno where idAlumno=?";

    private static final String SQL_READ = "SELECT idAlumno,nombre,paterno,materno,email,noBoleta,idCarrera FROM Alumno where idAlumno=?";

    private static final String SQL_READALL = "SELECT idAlumno,nombre,paterno,materno,email,noBoleta,idCarrera FROM Alumno";

    private Connection conexion;

    private void obtenerConexion() {
          String usuario = "root";
        String clave = "n0m3l0";
        //Checar puerto por si hay error
         //String url="jdbc:mysql://localhost:3306/usuario";
        String url = "jdbc:mysql://localhost:3306/usuario?serverTimezone=America/Mexico_City&amp;allowPublicKeyRetrieval=true&amp;useUnicode=true&amp;useJDBCCompliantTimezoneShift=true&amp;useLegacyDatetimeCode=false&amp;useSSL=false";
        String driverMysql = "com.mysql.jdbc.Driver";
        try {
            // String driverMysql="com.mysql.cj.jdbc.Driver";
            Class.forName(driverMysql);
            try {
                conexion = DriverManager.getConnection(url, usuario, clave);
            } catch (SQLException ex) {
                Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         }

    public void create(AlumnoDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1, dto.getEntidad().getNombre());
            ps.setString(2, dto.getEntidad().getPaterno());
            ps.setString(3, dto.getEntidad().getMaterno());
            ps.setString(4, dto.getEntidad().getEmail());
            ps.setString(5, dto.getEntidad().getNoBoleta());
            ps.setInt(6, dto.getEntidad().getIdCarrera());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }

    }

    public void update(AlumnoDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1, dto.getEntidad().getNombre());
            ps.setString(2, dto.getEntidad().getPaterno());
            ps.setString(3, dto.getEntidad().getMaterno());
            ps.setString(4, dto.getEntidad().getEmail());
            ps.setString(5, dto.getEntidad().getNoBoleta());
            ps.setInt(6, dto.getEntidad().getIdCarrera());
            ps.setInt(7, dto.getEntidad().getIdAlumno());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }

    }

    public void delete(AlumnoDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getEntidad().getIdAlumno());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }

    }
    //METODOS READ Y READALL 
    
     public List readAll() throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps=conexion.prepareStatement(SQL_READALL);
            rs=ps.executeQuery();
            List resultados = obtenerResultados(rs);
            if(resultados.size()>0){
            return resultados;
            }else {
            return null;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }

    }
    
     public Carrera read(AlumnoDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conexion.prepareStatement(SQL_READ);
            ps.setInt(1, dto.getEntidad().getIdAlumno());
            rs = ps.executeQuery();
            List resultados = obtenerResultados(rs);
            if (resultados.size() > 0) {
                return (Carrera) resultados.get(0);
            } else {
                return null;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }
     
      private List obtenerResultados(ResultSet rs) throws SQLException{
        List resultados= new ArrayList();
        PreparedStatement ps = null;
        while (rs.next()) {            
            ps = conexion.prepareStatement(SQL_READ);
            AlumnoDTO dt = new AlumnoDTO();
            ps.setString(1,dt.getEntidad().getNombre());
            ps.setString(2, dt.getEntidad().getPaterno());
            ps.setString(3, dt.getEntidad().getMaterno());
            ps.setString(4, dt.getEntidad().getEmail());
            ps.setString(5, dt.getEntidad().getNoBoleta());
            
            resultados.add(dt);

        }
        
        return resultados;
      }
    

}
