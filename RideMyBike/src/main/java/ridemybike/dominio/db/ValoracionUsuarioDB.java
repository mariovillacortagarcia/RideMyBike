package ridemybike.dominio.db;

import java.sql.*;
import java.util.ArrayList;
import ridemybike.dominio.ValoracionUsuario;



public class ValoracionUsuarioDB{

  public static int insertarValoracionUsuario(ValoracionUsuario valoracion) throws SQLException{
          if(valoracion == null){
              throw new IllegalArgumentException("Valoracion igual a null");
          }
          ConnectionPool pool = ConnectionPool.getInstance();
          Connection connection = pool.getConnection();
          PreparedStatement ps;
          String query = "INSERT INTO ValoracionUsuario(codigoAlquiler, descripcion, puntuacion, usuarioValorado) VALUES (?, ?, ?, ?)";
          try {
              ps = connection.prepareStatement(query);
              ps.setString(1, Integer.toString(valoracion.getCodigo()));
              ps.setString(2, valoracion.getDescripcion());
              ps.setString(3, Integer.toString(valoracion.getPuntuacion()));
              ps.setString(4, valoracion.getCodigoUsuario());
              int res = ps.executeUpdate();
              ps.close();
              pool.freeConnection(connection);
              return res;
          } catch (SQLException e) {
              e.printStackTrace();
              return 0;
          }
      }
  public static ValoracionUsuario selectValoracionUsuario(String codigoAlquiler) {
          if(codigoAlquiler == null){
              throw new IllegalArgumentException("El codigo de la valoracion es igual a null");
          }

          ConnectionPool pool = ConnectionPool.getInstance();
          Connection connection= pool.getConnection();
          PreparedStatement ps= null;
          ResultSet rs = null;
          String query= "SELECT * FROM ValoracionUsuario WHERE codigoAlquiler = ?";
          try {
              ps = connection.prepareStatement(query);
              ps.setString(1, codigoAlquiler);
              rs = ps.executeQuery();
              ValoracionUsuario valoracionUsuario = null;
              if  (rs.next()) {
                  valoracionUsuario = new ValoracionUsuario();
                  valoracionUsuario.setCodigo(Integer.parseInt(rs.getString("codigoAlquiler")));
                  valoracionUsuario.setDescripcion(rs.getString("descripcion"));
                  valoracionUsuario.setPuntuacion(Integer.parseInt(rs.getString("puntuacion")));
                  valoracionUsuario.setUsuarioValorado(rs.getString("usuarioValorado"));
              }
              rs.close();
              ps.close();
              pool.freeConnection(connection);
              return valoracionUsuario;
          }catch (SQLException e) {
              e.printStackTrace();
              return null;
          }
      }
  
  /**
   * Devuelve la valoracion media del usuario especificado
   * 
   * @param nombreUsuario el numbre de usuario
   * @return un int en el intervalo [0,5] con la valoracion; -1 si ha habido algun error
   * @throws IllegalArgumentException si el nombre de usuario es igual a null
   */
  public static int selectValoracionMedia(String nombreUsuario){
      if(nombreUsuario == null){
              throw new IllegalArgumentException("Nombre de usuario igual a null");
          }

          ConnectionPool pool = ConnectionPool.getInstance();
          Connection connection= pool.getConnection();
          PreparedStatement ps= null;
          ResultSet rs = null;
          String query= "SELECT AVG(puntuacion) as media FROM ValoracionUsuario WHERE usuarioValorado = ?";
          try {
              ps = connection.prepareStatement(query);
              ps.setString(1, nombreUsuario);
              rs = ps.executeQuery();
              rs.next();
              int valoracionMedia = rs.getInt("media");
              
              rs.close();
              ps.close();
              pool.freeConnection(connection);
              return valoracionMedia;
          }catch (SQLException e) {
              e.printStackTrace();
              return -1;
          }
  }
  public static ArrayList<ValoracionUsuario> getValoraciones(String usuarioValorado) {
        if (usuarioValorado == null || usuarioValorado.equals("")) {
            throw new IllegalArgumentException("El usuario no es no valida");
        }
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM ValoracionUsuario WHERE usuarioValorado = ?";
        ArrayList<ValoracionUsuario> lista = new ArrayList<ValoracionUsuario>();
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, usuarioValorado);
            rs = ps.executeQuery();
            ValoracionUsuario valoracion = null;
            while (rs.next()) {
                valoracion = new ValoracionUsuario();
                valoracion.setCodigo(Integer.parseInt(rs.getString("codigoAlquiler")));
                valoracion.setDescripcion(rs.getString("descripcion"));
                valoracion.setPuntuacion(Integer.parseInt(rs.getString("puntuacion")));
                valoracion.setUsuarioValorado(rs.getString("usuarioValorado"));
                lista.add(valoracion);
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}