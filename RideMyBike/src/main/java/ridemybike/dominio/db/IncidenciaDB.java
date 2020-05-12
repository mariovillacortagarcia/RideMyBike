package ridemybike.dominio.db;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
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
        String query = "INSERT INTO Incidencia(codigoPeticion, descripcion, grado) VALUES (?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, incidencia.getCodigoPeticion()+"");
            ps.setString(2, incidencia.getDescripcion());
            ps.setString(3, incidencia.getGravedad()+"");
            ps.setString(4, 0+"");
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
    
    /**
     * Devuelve las incidencias no resueltas del alquiler especificado
     * 
     * @param codigoAlquiler el codigo del alquiler
     * @return un ArrayList con las incidencias
     * @throws IllegalArgumentException si el codigo dado es negativo
     */
    public static ArrayList<Incidencia> selectIncidenciasSinSolucionar(int codigoAlquiler) {
        if(codigoAlquiler < 0){
            throw new IllegalArgumentException("Codigo de incidencia negativo");
        }
    
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection= pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        String query= "SELECT * FROM Incidencia, Alquiler WHERE Alquiler.codigoAlquiler = ? AND Alquiler.codigoPeticion = Incidencia.codigoPeticion AND solucionada = 0";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, codigoAlquiler+"");
            rs = ps.executeQuery();
            ArrayList<Incidencia> incidenciasSinSolucionar = new ArrayList<>();
            Incidencia incidencia = null;
            while (rs.next()) {
                incidencia = new Incidencia();
                incidencia.setDescripcion(rs.getString("descripcion"));
                incidencia.setCodigoIncidencia(Integer.parseInt(rs.getString("codigoIncidencia")));
                incidencia.setCodigoPeticion(Integer.parseInt(rs.getString("codigoPeticion")));
                incidencia.setGravedad(GradoIncidencia.valueOf(rs.getString("grado")));
                incidenciasSinSolucionar.add(incidencia);
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return incidenciasSinSolucionar;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Funcion que realiza la eliminacion de una incidencia
     * 
     *
     * @param codigoIncidencia Es el codigo de la bici que se usa para identificarla
     * @return un entero positivo si la actualizacion ha tenido exito; 0 si ha habido algun fallo
     * @throws SQLException Excepcion relacionada con la base de datos
     */
    public static int eliminaIncidencia(int codigoIncidencia) throws SQLException{
        if(codigoIncidencia <= 0){
            throw new IllegalArgumentException("La incidencia a eliminar es nula");
        }
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection= pool.getConnection();
        PreparedStatement ps;
        String query = "DELETE Incidencia SET WHERE codigoIncidencia= ?";

        ps = connection.prepareStatement(query);
        ps.setString(1, codigoIncidencia + "");
        int res = ps.executeUpdate();
        ps.close();
        pool.freeConnection(connection);
        return res;
        
    }
    
    /**
     * Marca la incidencia especificada como solucionada
     * 
     * @param codigoIncidencia el codigo de la incidencia
     * @return un int positivo si la actualizacion ha tenido exito; negativo en caso contrario
     * @throws IllegalArgumentException si el codigo es negativo o nulo 
     */
    public static int solucionarIncidencia(int codigoIncidencia) throws IOException{
        if(codigoIncidencia <= 0){
            throw new IllegalArgumentException("Codigo de incidencia negativo o nulo");
        }
    
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection= pool.getConnection();
        PreparedStatement ps= null;
        String query;
        query = "UPDATE Incidencia SET solucionada = 1 WHERE codigoIncidencia = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, codigoIncidencia+"");
            
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static ArrayList<Incidencia> selectIncidenciasSinSolucionarPorBici(int codigoBici) {
        if(codigoBici <= 0){
            throw new IllegalArgumentException("Codigo de bicicleta negativo o nulo");
        }
    
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection= pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        String query= "SELECT * FROM Incidencia, Peticion WHERE Peticion.codigoBici = ? AND Peticion.codigoPeticion = Incidencia.codigoPeticion AND solucionada = 0";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, codigoBici+"");
            rs = ps.executeQuery();
            ArrayList<Incidencia> incidenciasSinSolucionar = new ArrayList<>();
            Incidencia incidencia = null;
            while (rs.next()) {
                incidencia = new Incidencia();
                incidencia.setDescripcion(rs.getString("descripcion"));
                incidencia.setCodigoIncidencia(Integer.parseInt(rs.getString("codigoIncidencia")));
                incidencia.setCodigoPeticion(Integer.parseInt(rs.getString("codigoPeticion")));
                incidencia.setGravedad(GradoIncidencia.valueOf(rs.getString("grado")));
                incidenciasSinSolucionar.add(incidencia);
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return incidenciasSinSolucionar;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
