package ridemybike.dominio.db;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    public static int insertarUsuario(Usuario usuario) throws SQLException, IOException  {
        if(usuario == null){
            throw new IllegalArgumentException("Usuario igual a null");
        }
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        String query;
        query = "INSERT INTO Usuario(nombreUsuario, nombre, apellidos, dni, email, telefono, numeroTarjeta, hashPassword, fotoPerfil, direccion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            ps.setBlob(9, usuario.getFotoPerfil().getInputStream());
            ps.setString(10, usuario.getDireccion());
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int res = 0;
            if (rs.next()) {
                res = rs.getInt(1);
            }
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
                usuario.setTlf(rs.getString("telefono"));
                usuario.setTarjetaCredito(rs.getString("numeroTarjeta"));
                usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setHashPasswd(rs.getString("hashPassword"));
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
     * Devuelve el usuario con el email especificado
     * 
     * @param email el email del usuario
     * @return un Usuario con los datos del usuario; null si no existe ningun usuario con 
     * el email especificado
     * @throws IllegalArgumentException si el email de usuario dado es igual a null
     */
    public static Usuario selectUserByEmail(String email) {
        if(email == null){
            throw new IllegalArgumentException("Email de usuario igual a null");
        }
    
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection= pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        String query= "SELECT * FROM Usuario WHERE email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            Usuario usuario = null;
            if  (rs.next()) {
                usuario = new Usuario();
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setEmail(rs.getString("email"));
                usuario.setDni(rs.getString("dni"));
                usuario.setTlf(rs.getString("telefono"));
                usuario.setTarjetaCredito(rs.getString("numeroTarjeta"));
                usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setHashPasswd(rs.getString("hashPassword"));
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
    
    /**
     * Verifica si existe algun usuario en la base de datos con el email especificado
     * 
     * @param email el email
     * @return true si existe; false en caso contrario
     * @throws IllegalArgumentException si el email dado es igual a null
     */
    public static boolean existeEmail(String email){
        if(email == null){
            throw new IllegalArgumentException("Email igual a null");
        }
    
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection= pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        String query= "SELECT * FROM Usuario WHERE email = ?";
        boolean existe = false;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
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
    
    /**
     * Actualiza la informacion de un usuario existente, en concreto:
     * nombre y apellidos, email, telefono, numero de tarjeta, hash de contraseña y direccion.
     * El nombre de usuario, el DNI y la foto de perfil no se veran modificados. 
     * Si el usuario dado no existe en la base de datos no se producira ningun cambio ni insercion en la misma
     * 
     * @param user el usuario actualizado
     * @return un entero positivo si la actualizacion ha tenido exito; 0 si ha habido algun fallo
     * @throws IllegalArgumentException si el usuario dado es igual a null
     */
    public static int actualizarUsuario(Usuario user) throws IOException{
        if(user == null){
            throw new IllegalArgumentException("Usuario igual a null");
        }
    
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection= pool.getConnection();
        PreparedStatement ps= null;
        String query;
        query = "UPDATE Usuario SET nombre = ?, apellidos = ?, email = ?, telefono = ?, numeroTarjeta = ?, hashPassword = ?, direccion = ? WHERE nombreUsuario = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getApellidos());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getTlf()+"");
            ps.setString(5, user.getTarjetaCredito());
            ps.setString(6, user.getHashPasswd());
            ps.setString(7, user.getDireccion());
            ps.setString(8, user.getNickName());
            
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
     * Actualiza la imagen de perfil del usuario dado
     * 
     * @param user el usuario especificado
     * @return un entero positivo si la actualizacion ha tenido exito; 0 si ha habido algun fallo
     * @throws IllegalArgumentException si el usuario dado es igual a null
     */
    public static int setImagen(Usuario user) throws IOException{
        if(user == null){
            throw new IllegalArgumentException("Usuario igual a null");
        }
    
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection= pool.getConnection();
        PreparedStatement ps= null;
        String query;
        query = "UPDATE Usuario SET fotoPerfil = ? WHERE nombreUsuario = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setBlob(1, user.getFotoPerfil().getInputStream());
            ps.setString(2, user.getNickName());
            
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
     * Devuelve la imagen de perfil del usuario especificado
     * 
     * @param nombreUsuario el nombre de usuario del usuario
     * @param respuesta el stream donde se recibira la imagen
     * @throws IllegalArgumentException si algun argumento es igual a null
     */
    public static void getImagen(String nombreUsuario, OutputStream respuesta){
        if(nombreUsuario == null){
            throw new IllegalArgumentException("Nombre de usuario igual a null");
        }
        if(respuesta == null){
            throw new IllegalArgumentException("Stream de respuesta para la foto de perfil igual a null");
        }
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection= pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query= "SELECT fotoPerfil FROM Usuario WHERE nombreUsuario = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                Blob blob = rs.getBlob("fotoPerfil");
                if (!rs.wasNull() && blob.length() > 1) {
                    InputStream imagen = blob.getBinaryStream();
                    byte[] buffer = new byte[1000];
                    int len = imagen.read(buffer);
                    while (len != -1) {
                        respuesta.write(buffer, 0, len);
                        len = imagen.read(buffer);
                    }
                    imagen.close();
                } 
            }
            
            rs.close();
            ps.close();
            pool.freeConnection(connection);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
