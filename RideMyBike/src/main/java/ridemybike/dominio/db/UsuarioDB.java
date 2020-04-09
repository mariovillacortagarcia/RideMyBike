package ridemybike.dominio.db;

import java.sql.*;
import ridemybike.dominio.Usuario;

/**
 * Implementacion del gestor de usuarios de la base de datos
 */
public class UsuarioDB {
    
    /**
     * Inserta un usuario en la base de datos
     * 
     * @param usuario el usuario a insertar
     * @return un entero positivo si la insercion ha tenido exito; 0 si ha habido algun fallo
     * @throws IllegalArgumentException si el usuario dado es igual a null
     */
    public static int insertarUsuario(Usuario usuario){
        if(usuario == null){
            throw new IllegalArgumentException("Usuario igual a null");
        }
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        String query = "INSERT INTO Usuario(nombreUsuario, nombre, apellidos, dni, email, telefono, numeroTarjeta, hashPassword) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, usuario.getNickName());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellidos());
            ps.setString(4, usuario.getDNI());
            ps.setString(5, usuario.getEmail());
            ps.setString(6, usuario.getTlf()+"");
            ps.setString(7, usuario.getTarjetaCredito());
            ps.setString(8, usuario.getHashPasswd());
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    /**
     * Devuelve el usuario con el nombre de usuario especificado
     * 
     * @param nombreUsuario el nick del usuario
     * @return un Usuario con los datos del usuario; null si no existe ningun usuario con 
     * el nick especificado
     * @throws IllegalArgumentException si el nombre de usuario dado es igual a null
     */
    public static Usuario selectUser(String nombreUsuario) {
        if(nombreUsuario == null){
            throw new IllegalArgumentException("Nombre de usuario igual a null");
        }
    
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection= pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        String query= "SELECT * FROM Usuario WHERE nombreUsuario = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            rs = ps.executeQuery();
            Usuario usuario = null;
            if  (rs.next()) {
                usuario = new Usuario();
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setEmail(rs.getString("email"));
                usuario.setDni(rs.getString("dni"));
                usuario.setTlf(Long.parseLong(rs.getString("telefono")));
                usuario.setTarjetaCredito(rs.getString("numeroTarjeta"));
                usuario.setNombreUsuario(rs.getString("nombreUsuario"));
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return usuario;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Verifica si existe algun usuario en la base de datos con el nombre 
     * de usuario especificado
     * 
     * @param nombreUsuario el nombre de usuario
     * @return true si existe; false en caso contrario
     * @throws IllegalArgumentException si el nombre dado es igual a null
     */
    public static boolean existeUsuario(String nombreUsuario){
        if(nombreUsuario == null){
            throw new IllegalArgumentException("Nombre de usuario igual a null");
        }
    
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection= pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        String query= "SELECT * FROM Usuario WHERE nombreUsuario = ?";
        boolean existe = false;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            rs = ps.executeQuery();
            existe = rs.next();
            rs.close();
            ps.close();
            pool.freeConnection(connection);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return existe;
    }
}
