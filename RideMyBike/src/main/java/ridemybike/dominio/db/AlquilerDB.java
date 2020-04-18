/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridemybike.dominio.db;

import java.sql.*;
import ridemybike.dominio.Alquiler;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 *
 * @author Mario Villacorta Garcia
 */
public class AlquilerDB {

    public static int insert(Alquiler alquiler) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        //TODO valoracion
        String query = "INSERT INTO Alquiler(horaInicial, horaFinal, precio"
                + "codigoAlquiler, codigoPeticion) VALUES (?,?,?,?,?)"; 
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, alquiler.getHoraInicial().toString());
            ps.setString(2, alquiler.getHoraFinal().toString());
            ps.setString(3, Double.toString(alquiler.getPrecio()));
            ps.setString(4, alquiler.getCodigoAlquiler());
            ps.setString(5, Integer.toString(alquiler.getPeticion().getCodigoPeticion()));
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Alquiler selectAlquiler(String codigo) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Alquiler WHERE codigoAlquiler = ?";
        Alquiler alquiler = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            if (rs.next()) {
                alquiler = new Alquiler();
                alquiler.setCodigoAlquiler(rs.getString("codigoAlquiler"));
                alquiler.setHoraInicial(ParseFecha(rs.getString("horaInicial")));
                alquiler.setHoraFinal(ParseFecha(rs.getString("horaFinal")));
                alquiler.setPrecio(Double.parseDouble(rs.getString("precio")));
                alquiler.setPeticion(PeticionDB.selectPeticion(rs.getString("codigoPeticion")));
                //TODO Valoracion
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return alquiler;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
      /**
     * Permite convertir un String en fecha (Date).
     * @param fecha Cadena de fecha dd/MM/yyyy
     * @return Objeto Date
     */
    public static Date ParseFecha(String fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = (Date) formato.parse(fecha);
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }
        return fechaDate;
    }
}
