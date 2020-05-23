package ridemybike.dominio.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import ridemybike.dominio.MensajeUsuario;

/**
 * Implementacion del gestor de mensajes de usuario de la base de datos
 */
public class MensajeUsuarioDB {
    
    /**
     * Inserta en la base de datos un nuevo mensaje de tipo duda o sugerencia
     * 
     * @param mensaje el mensaje del usuario
     */
    public static int insertDudaSugerencia(MensajeUsuario mensaje){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        String query = "INSERT INTO DudaSugerencia(nombreUsuario, asunto, descripcion, fechaInsercion) VALUES (?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, mensaje.getNombreUsuario());
            ps.setString(2, mensaje.getAsunto());
            ps.setString(3, mensaje.getDescripcion());
            ps.setString(4, mensaje.getFechaCreacion().toString());
            ps.executeUpdate();

            ps.close();
            pool.freeConnection(connection);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    /**
     * Inserta en la base de datos un nuevo mensaje de tipo problema
     * 
     * @param mensaje el mensaje del usuario
     */
    public static int insertProblema(MensajeUsuario mensaje){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        String query = "INSERT INTO Problema(nombreUsuario, asunto, descripcion, fechaInsercion) VALUES (?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, mensaje.getNombreUsuario());
            ps.setString(2, mensaje.getAsunto());
            ps.setString(3, mensaje.getDescripcion());
            ps.setString(4, mensaje.getFechaCreacion().toString());
            ps.executeUpdate();

            ps.close();
            pool.freeConnection(connection);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Devuelve todos los problemas que no hayan sido atendidos todavia
     * 
     * @return un ArrayList con los mensajes que describen los problemas
     */
    public static ArrayList<MensajeUsuario> selectProblemasPendientes() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT * FROM Problema WHERE fechaAtencion is NULL";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            MensajeUsuario mensajeProblema = null;
            ArrayList<MensajeUsuario> problemas = new ArrayList<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            while (rs.next()) {
                mensajeProblema = new MensajeUsuario();
                mensajeProblema.setCodigo(rs.getString("codigo"));
                mensajeProblema.setDescripcion(rs.getString("descripcion"));
                mensajeProblema.setAsunto(rs.getString("asunto"));
                mensajeProblema.setNombreUsuario(rs.getString("nombreUsuario"));
                Timestamp fechaCreacion = new Timestamp(dateFormat.parse(rs.getString("fechaInsercion")).getTime());
                mensajeProblema.setFechaCreacion(fechaCreacion);
                problemas.add(mensajeProblema);
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return problemas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Devuelve todas las dudas o sugerencias que no hayan sido atendidas todavia
     * 
     * @return un ArrayList con los mensajes que describen las dudas o sugerencias
     */
    public static ArrayList<MensajeUsuario> selectDudasSugerenciasPendientes() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT * FROM DudaSugerencia WHERE fechaAtencion is NULL";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            MensajeUsuario mensajeProblema = null;
            ArrayList<MensajeUsuario> problemas = new ArrayList<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            while (rs.next()) {
                mensajeProblema = new MensajeUsuario();
                mensajeProblema.setCodigo(rs.getString("codigo"));
                mensajeProblema.setDescripcion(rs.getString("descripcion"));
                mensajeProblema.setAsunto(rs.getString("asunto"));
                mensajeProblema.setNombreUsuario(rs.getString("nombreUsuario"));
                Timestamp fechaCreacion = new Timestamp(dateFormat.parse(rs.getString("fechaInsercion")).getTime());
                mensajeProblema.setFechaCreacion(fechaCreacion);
                problemas.add(mensajeProblema);
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return problemas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Marca el problema especificado como solucionado
     * 
     * @param codigo el codigo del mensaje que describe el problema 
     */
    public static int solucionarProblema(String codigo) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection= pool.getConnection();
        PreparedStatement ps= null;
        String query;
        query = "UPDATE Problema SET fechaAtencion = ? WHERE codigo = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, new Timestamp(System.currentTimeMillis()).toString());
            ps.setString(2, codigo);
            
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Marca la duda o sugerencia especificada como solucionada
     * 
     * @param codigo el codigo del mensaje que describe la duda o sugerencia 
     */
    public static int solucionarDudaSugerencia(String codigo) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection= pool.getConnection();
        PreparedStatement ps= null;
        String query;
        query = "UPDATE DudaSugerencia SET fechaAtencion = ? WHERE codigo = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, new Timestamp(System.currentTimeMillis()).toString());
            ps.setString(2, codigo);
            
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
