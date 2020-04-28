/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridemybike.dominio.db;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import ridemybike.dominio.Bicicleta;
import ridemybike.dominio.Peticion;
import ridemybike.dominio.TipoAlquiler;

/**
 * Implementación del gestor de peticiones de la base de datos
 */
public class PeticionDB {
        /**
     * Inserta una nueva petición en la base de datos
     * 
     * @param peticion la petición a insertar
     * @return un entero positivo si la insercion ha tenido exito; 0 si ha habido algun fallo
     * @throws IllegalArgumentException si la petición dada es igual a null
     */
    public static int insertarPeticion(Peticion peticion){
        if(peticion == null){
            throw new IllegalArgumentException("Peticion igual a null");
        }
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        String query = "INSERT INTO Peticion(hora, tiempoLimite, codigoBici, usuarioArrendatario, tipo) VALUES (?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, peticion.getHoraInicio().toString());
            ps.setString(2, peticion.getHoraLimite().toString());
            ps.setString(3, peticion.getCodigoBici()+"");
            ps.setString(4, peticion.getNombreArrendatario());
            ps.setString(5, peticion.getTipo()+"");
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int res = 0;
            if (rs.next()) {
                res = rs.getInt(1);
            } 
            peticion.setCodigoPeticion(res);
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    /**
     * Devuelve la petición con el código de la petición especificado
     * 
     * @param codigoPeticion  el código de la petición
     * @return una Petición con los datos de la petición; null si no existe ninguna petición con 
     * el código especificado
     * @throws IllegalArgumentException si el código dado es negativo
     */
    public static Peticion selectPeticion(int codigoPeticion) throws ParseException {
        if(codigoPeticion < 0){
            throw new IllegalArgumentException("El código de petición es negativo.");
        }
    
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection= pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        String query= "SELECT * FROM Peticion WHERE codigoPetición = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, codigoPeticion+"");
            rs = ps.executeQuery();
            Peticion peticion = null;
            if  (rs.next()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                peticion = new Peticion();
                peticion.setHoraInicio(new Timestamp(dateFormat.parse(rs.getString("hora")).getTime()));
                peticion.setHoraLimite(new Timestamp(dateFormat.parse(rs.getString("tiempoLimite")).getTime()));
                peticion.setCodigoBici(Integer.parseInt(rs.getString("codigoBici")));
                peticion.setNombreArrendatario(rs.getString("nombreArrendatario"));
                peticion.setTipo(TipoAlquiler.valueOf(rs.getString("tipo")));
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
    
    public static void eliminaUnaBicicleta(Bicicleta bici) throws SQLException{
        if(bici == null){
            throw new IllegalArgumentException("La bicicleta a eliminar es nula");
        }
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection= pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        int codigoBici = bici.getcodigoBici();
        String query= "DELETE FROM Peticion“+“WHERE codigoBici= ‘”+codigoBici+ “’”;";
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        rs.close();
        ps.close();
        pool.freeConnection(connection);
    }
    
}
