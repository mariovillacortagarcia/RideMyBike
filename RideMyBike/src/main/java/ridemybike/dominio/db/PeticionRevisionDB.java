/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridemybike.dominio.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.servlet.http.Part;
import ridemybike.dominio.Bicicleta;
import ridemybike.dominio.EstadoBicicleta;
import ridemybike.dominio.Freno;
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
          String query = "INSERT INTO PeticionRevision(nombreUsuario, ciudad, fecha, hora, ,codigoBicicleta) VALUES (?, ?, ?, ?, ?)";
          try {
              ps = connection.prepareStatement(query);
              ps.setString(1, peticion.getNombreUsuario());
              ps.setString(2, peticion.getCiudad());
              ps.setString(3, peticion.getFecha().toString());
              ps.setString(4, peticion.getHora().toString());
              ps.setString(5, peticion.getCodigoBicicleta());
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
    public static PeticionRevision selectPeticionRevision(String codigoPeticion) {
        if(codigoPeticion == null){
            throw new IllegalArgumentException("El codigo de la peticion es null");
        }
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection= pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        String query= "SELECT * FROM PeticionRevision WHERE codigo = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, codigoPeticion);
            rs = ps.executeQuery();
            PeticionRevision peticion = null;
            if  (rs.next()) {
                peticion = new PeticionRevision();
                peticion.setCodigoUsuario(rs.getString("nombreUsuario"));
                peticion.setFecha(LocalDate.parse(rs.getString("fecha")));
                peticion.setHora(LocalDateTime.parse(rs.getString("hora")));
                peticion.setCiudad(rs.getString("ciudad"));
                peticion.setCodigoBicicleta(rs.getString("codigoBicicleta"));

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
