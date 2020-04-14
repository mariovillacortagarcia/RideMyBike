package ridemybike.dominio.db;

import java.sql.*;
import ridemybike.dominio.GradoIncidencia;
import ridemybike.dominio.Incidencia;

/**
 * Implementacion del gestor de incidencias de la base de datos
 */
public class IncidenciaDB {
     
    /**
     * Inserta una incidencia en la base de datos
     * 
     * @param incidencia el usuario a insertar
     * @return un entero positivo si la insercion ha tenido exito; 0 si ha habido algun fallo
     * @throws IllegalArgumentException si la incidencia dado es igual a null
     */
    public static int insertarIncidencia(Incidencia incidencia){
        if(incidencia == null){
            throw new IllegalArgumentException("Incidencia igual a null");
        }
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        String query = "INSERT INTO Incidencia(codigoPeticion, descripcion, grado) VALUES (?, ?, ?)";
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, incidencia.getCodigoPeticion()+"");
            ps.setString(2, incidencia.getDescripcion());
            ps.setString(3, incidencia.getGravedad()+"");
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int res = 0;
            if (rs.next()) {
                res = rs.getInt(1);
            } 
            incidencia.setCodigoIncidencia(res);
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    /**
     * Devuelve la incidencia con el codigo especificado
     * 
     * @param codigoIncidencia el codigo de la incidencia
     * @return una Incidencia con los datos de la incidencia; null si no existe ninguna incidencia con 
     * el codigo especificado
     * @throws IllegalArgumentException si el codigo dado es negativo
     */
    public static Incidencia selectIncidencia(int codigoIncidencia) {
        if(codigoIncidencia < 0){
            throw new IllegalArgumentException("Codigo de incidencia negativo");
        }
    
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection= pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        String query= "SELECT * FROM Incidencia WHERE codigoIncidencia = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, codigoIncidencia+"");
            rs = ps.executeQuery();
            Incidencia incidencia = null;
            if  (rs.next()) {
                incidencia = new Incidencia();
                incidencia.setDescripcion(rs.getString("descripcion"));
                incidencia.setCodigoIncidencia(Integer.parseInt(rs.getString("codigoIncidencia")));
                incidencia.setCodigoPeticion(Integer.parseInt(rs.getString("codigoPeticion")));
                incidencia.setGravedad(GradoIncidencia.valueOf(rs.getString("grado")));
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return incidencia;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
