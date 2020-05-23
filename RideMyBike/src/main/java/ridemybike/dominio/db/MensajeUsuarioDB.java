package ridemybike.dominio.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}
