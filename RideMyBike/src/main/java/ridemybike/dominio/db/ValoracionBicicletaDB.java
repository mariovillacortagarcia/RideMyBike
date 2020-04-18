package ridemybike.dominio.db;

import java.sql.*;
import ridemybike.dominio.Valoracion;
import ridemybike.dominio.ValoracionBicicleta;



public class ValoracionBicicletaDB{

  public static int insertarValoracionBicicleta(ValoracionBicicleta valoracionBici) throws SQLException{
          if(valoracionBici == null){
              throw new IllegalArgumentException("Valoracion igual a null");
          }
          ConnectionPool pool = ConnectionPool.getInstance();
          Connection connection = pool.getConnection();
          PreparedStatement ps;
          String query = "INSERT INTO ValoracionBicicleta(codigoAlquiler, descripcion, puntuacion) VALUES (?, ?, ?)";
          try {
              ps = connection.prepareStatement(query);
              ps.setString(1, Integer.toString(valoracionBici.getCodigo()));
              ps.setString(2, valoracionBici.getDescripcion());
              ps.setString(3, Integer.toString(valoracionBici.getPuntuacion()));
              int res = ps.executeUpdate();
              ps.close();
              pool.freeConnection(connection);
              return res;
          } catch (SQLException e) {
              e.printStackTrace();
              return 0;
          }
      }
  public static ValoracionBicicleta selectValoracionBicicleta(String codigoAlquiler) {
          if(codigoAlquiler == null){
              throw new IllegalArgumentException("El codigo de la valoracion es igual a null");
          }

          ConnectionPool pool = ConnectionPool.getInstance();
          Connection connection= pool.getConnection();
          PreparedStatement ps= null;
          ResultSet rs = null;
          String query= "SELECT * FROM ValoracionBicicleta WHERE codigoAlquiler = ?";
          try {
              ps = connection.prepareStatement(query);
              ps.setString(1, codigoAlquiler);
              rs = ps.executeQuery();
              ValoracionBicicleta valoracionBici = null;
              if  (rs.next()) {
                  valoracionBici = new ValoracionBicicleta();
                  valoracionBici.setCodigo(Integer.parseInt(rs.getString("codigoAlquiler")));
                  valoracionBici.setDescripcion(rs.getString("descripcion"));
                  valoracionBici.setPuntuacion(Integer.parseInt(rs.getString("puntuacion")));
              }
              rs.close();
              ps.close();
              pool.freeConnection(connection);
              return valoracionBici;
          }catch (SQLException e) {
              e.printStackTrace();
              return null;
          }
      }
}