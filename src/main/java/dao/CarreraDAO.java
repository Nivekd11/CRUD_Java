package dao;

import com.mycompany.c15.Carrera;
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
public class CarreraDAO {

    private static final String SQL_INSERT = "insert into Carrera(nombreCarrera,duracion) values(?,?)";
    private static final String SQL_UPDATE = "update Carrera set nombreCarrera=? , duracion=? where idCarrera=?";
    private static final String SQL_DELETE = "delete from Carrera where idCarrera = ?";
    private static final String SQL_READ = "select * from Carrera where idCarrera = ?";
    private static final String SQL_READ_ALL = "select * from Carrera";

    private Connection conexion;

    private void obtenerConexion() {
        String usuario = "nivek";
        String clave = "nivek";
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

    public void create(Carrera carrera) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1, carrera.getNombreCarrera());
            ps.setInt(2, carrera.getDuracion());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                ps.close();
            }
        }

    }

    public void update(Carrera carrera) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1, carrera.getNombreCarrera());
            ps.setInt(2, carrera.getDuracion());
            ps.setInt(3, carrera.getIdCarrera());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                ps.close();
            }
        }

    }

    public void delete(Carrera carrera) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, carrera.getIdCarrera());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                ps.close();
            }
        }

    }


    public List readAll() throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps=conexion.prepareStatement(SQL_READ_ALL);
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
    
     public Carrera read(Carrera c) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conexion.prepareStatement(SQL_READ);
            ps.setInt(1, c.getIdCarrera());
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
    public static void main(String[] args) {
       Carrera c = new Carrera();
     //  c.setNombreCarrera("Ingenieria en Inteligencia Artificial");
      // c.setDuracion(4);
      //c.setIdCarrera(3);

        CarreraDAO dao = new CarreraDAO();
        try {
           //dao.create(c);
         //   if(dao.read(c)!=null){
           //     System.out.println(dao.read(c));
           // }else{
                //System.out.println("La busqueda no dio resultados");
            //}
            System.out.println(dao.readAll());
            
                       } catch (SQLException ex) {
            Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

       // System.out.println("Lista");
    }

    private List obtenerResultados(ResultSet rs) throws SQLException{
        List resultados= new ArrayList();
        while (rs.next()) {            
            Carrera c = new Carrera();
            c.setIdCarrera(rs.getInt("idCarrera"));
            c.setNombreCarrera(rs.getString("nombreCarrera"));
            c.setDuracion(rs.getInt("duracion"));
            resultados.add(c);
        }
        
        return resultados;
      }

}
