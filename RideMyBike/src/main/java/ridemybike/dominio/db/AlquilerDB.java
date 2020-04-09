/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridemybike.dominio.db;

import java.sql.*;
import dominio.Alquiler;

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
            ps.setString(3, alquiler.getPrecio().toString());
            ps.setString(4, alquiler.getCodigoAlquiler());
            ps.setString(5, alquiler.getPeticion().getCodigoPeticion());
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
                alquiler.setHoraInicial(rs.getString("horaInicial"));
                alquiler.setHoraFinal(rs.getString("horaFinal"));
                alquiler.setPrecio(rs.getString("precio"));
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
}
