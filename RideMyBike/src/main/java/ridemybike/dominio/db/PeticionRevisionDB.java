package ridemybike.dominio.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import ridemybike.dominio.PeticionRevision;

/**
 *
 * @author Alberto
 */
public class PeticionRevisionDB {
    
    /**
     * Funcion que almacena en la BD una nueva peticion para la revision de una bicicleta
     * @param peticion Es la peticion que se quiere almacenar
     * @return
     * @throws SQLException Se lanza si surge algun problema con la BD
     * @throws IOException 
     */
    public static int insertarPeticionRevision(PeticionRevision peticion) throws SQLException, IOException{
          if(peticion == null){
              throw new IllegalArgumentException("Peticion igual a null");
          }
          ConnectionPool pool = ConnectionPool.getInstance();
          Connection connection = pool.getConnection();
          PreparedStatement ps;
          String query = "INSERT INTO PeticionRevision(nombreUsuario, ciudad, fecha,codigoBici) VALUES (?, ?, ?, ?)";
          try {
              ps = connection.prepareStatement(query);
              ps.setString(1, peticion.getNombreUsuario());
              ps.setString(2, peticion.getCiudad());
              ps.setString(3, peticion.getFecha().toString());
              ps.setString(4, peticion.getCodigoBicicleta()+"");
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
     * Metodo para devolver una bicicleta almacenada segun su codigo de bicicleta
     * @param codigoPeticion Es el codigo asignado a la peticion que queremos extraer
     * @return Una peticion almacenada en el sistema
     */
    public static PeticionRevision selectPeticionRevision(String codigoPeticion) throws ParseException {
        if(codigoPeticion == null){
            throw new IllegalArgumentException("El codigo de la peticion es null");
        }
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection= pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        String query= "SELECT * FROM PeticionRevision WHERE codigoBici = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, codigoPeticion);
            rs = ps.executeQuery();
            PeticionRevision peticion = null;
            if  (rs.next()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                peticion = new PeticionRevision();
                peticion.setCodigoUsuario(rs.getString("nombreUsuario"));
                peticion.setFecha(new Timestamp(dateFormat.parse(rs.getString("fecha")).getTime()));
                peticion.setCiudad(rs.getString("ciudad"));
                peticion.setCodigoBicicleta(Integer.parseInt(rs.getString("codigoBicicleta")));

            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return peticion;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
