package ridemybike.dominio.db;

import java.sql.*;
import ridemybike.dominio.Bicicleta;
import ridemybike.dominio.EstadoBicicleta;
import ridemybike.dominio.Freno;



public class BicicletaDB{

  public static int insertarBicicleta(Bicicleta bicicleta) throws SQLException{
          if(bicicleta == null){
              throw new IllegalArgumentException("Bicicleta igual a null");
          }
          ConnectionPool pool = ConnectionPool.getInstance();
          Connection connection = pool.getConnection();
          PreparedStatement ps;
          String query = "INSERT INTO Bicicleta(codigoBici, descripcion, tamCuadro, imagen, marca, freno, latitud, longitud, usuarioPropietario, estado, codigoActivacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
          try {
              ps = connection.prepareStatement(query);
              ps.setString(1, bicicleta.getcodigoBici());
              ps.setString(2, bicicleta.getDescripcion());
              ps.setString(3, bicicleta.getTamCuadro() +"");
              ps.setString(4, bicicleta.getImagen());
              ps.setString(5, bicicleta.getMarca());
              ps.setString(6, bicicleta.getFreno()+"");
              ps.setString(7, bicicleta.getLatitud()+"");
              ps.setString(8, bicicleta.getLongitud()+"");
              ps.setString(9, bicicleta.getUsuarioPropietario());
              ps.setString(10, bicicleta.getEstado()+"");
              ps.setString(11, bicicleta.getCodigoActivacion());
              int res = ps.executeUpdate();
              ps.close();
              pool.freeConnection(connection);
              return res;
          } catch (SQLException e) {
              e.printStackTrace();
              return 0;
          }
      }
  public static Bicicleta selectBicicleta(String codigoBici) {
          if(codigoBici == null){
              throw new IllegalArgumentException("El codigo de la bicicleta es igual a null");
          }

          ConnectionPool pool = ConnectionPool.getInstance();
          Connection connection= pool.getConnection();
          PreparedStatement ps= null;
          ResultSet rs = null;
          String query= "SELECT * FROM Bicicleta WHERE codigoBici = ?";
          try {
              ps = connection.prepareStatement(query);
              ps.setString(1, codigoBici);
              rs = ps.executeQuery();
              Bicicleta bicicleta = null;
              if  (rs.next()) {
                  bicicleta = new Bicicleta();
                  bicicleta.setCodigoBici(rs.getString("codigoBici"));
                  bicicleta.setDescripcion(rs.getString("descripcion"));
                  bicicleta.setTamCuadro(Double.parseDouble(rs.getString("tamCuadro")));
                  bicicleta.setImagen(rs.getString("imagen"));
                  bicicleta.setMarca(rs.getString("marca"));
                  bicicleta.setFreno(Freno.valueOf(rs.getString("freno")));
                  bicicleta.setLatitud(Double.parseDouble(rs.getString("latitud")));
                  bicicleta.setLongitud(Double.parseDouble(rs.getString("longitud")));
                  bicicleta.setUsuarioPropietario(rs.getString("usuarioPropietario"));
                  bicicleta.setEstado(EstadoBicicleta.valueOf(rs.getString("estado")));
                  bicicleta.setCodigoActivacion(rs.getString("codigoAcivacion"));
              }
              rs.close();
              ps.close();
              pool.freeConnection(connection);
              return bicicleta;
          }catch (SQLException e) {
              e.printStackTrace();
              return null;
          }
      }
}
