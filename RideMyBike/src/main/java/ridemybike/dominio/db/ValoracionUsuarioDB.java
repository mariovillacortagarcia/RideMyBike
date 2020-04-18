package ridemybike.dominio.db;

import java.sql.*;
import ridemybike.dominio.Valoracion;
import ridemybike.dominio.ValoracionUsuario;



public class ValoracionUsuarioDB{

  public static int insertarValoracionUsuario(ValoracionUsuario valoracion) throws SQLException{
          if(valoracion == null){
              throw new IllegalArgumentException("Valoracion igual a null");
          }
          ConnectionPool pool = ConnectionPool.getInstance();
          Connection connection = pool.getConnection();
          PreparedStatement ps;
          String query = "INSERT INTO ValoracionBicicleta(codigoAlquiler, descripcion, puntuacion, usuarioValorado) VALUES (?, ?, ?, ?)";
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
}