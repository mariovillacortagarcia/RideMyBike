/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridemybike.dominio.db;

import java.sql.*;
import java.util.ArrayList;
import ridemybike.dominio.Alquiler;

 /**
 * @author Mario Villacorta Garcia
 */
public class AlquilerDB {
 /**
     * Inserta un nuevo alquiler en la base de datos
     * 
     * @param alquiler el alquiler a insertar
     * @return un entero positivo si la insercion ha tenido exito; 0 si ha habido algun fallo
     * @throws IllegalArgumentException si la petición dada es igual a null
     */
    public static int insertarAlquiler(Alquiler alquiler) {
        if(alquiler == null){
            throw new IllegalArgumentException("Alquiler igual a null");
        }
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO Alquiler(precio, horaInicial, horaFinal, codigoAlquiler, peticion, archivado) VALUES (?,?,?,?,?,?,?)"; 
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, alquiler.getPrecio()+"");
            ps.setString(2, alquiler.getHoraInicial().toString());
            ps.setString(3, alquiler.getHoraFinal().toString());
            ps.setString(4, alquiler.getCodigoAlquiler());
            ps.setString(5, alquiler.getPeticion()+"");
            boolean archivado = alquiler.getArchivado();
            if(archivado){
                ps.setString(6, "1"+"");
            }else{
                ps.setString(6, "0"+"");
            }
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
     * Devuelve un alquiler con el código del alquiler especificado
     * 
     * @param codigoAlquiler  el código del alquiler 
     * @return un Alquiler con los datos del alquiler; null si no existe ningun alquiler con 
     * el código especificado
     * @throws IllegalArgumentException si el código dado es negativo
     */
    public static Alquiler selectAlquiler(String codigoAlquiler) {
        if(codigoAlquiler == null){
            throw new IllegalArgumentException("El codigo del alquiler es igual a null");
        }
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Alquiler WHERE codigoAlquiler = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, codigoAlquiler);
            rs = ps.executeQuery();
            Alquiler alquiler = null;
            if (rs.next()) {
                alquiler = new Alquiler();
                alquiler.setPrecio(Double.parseDouble(rs.getString("precio")));
                alquiler.setHoraInicial(Time.valueOf(rs.getString("horaInicial")));
                alquiler.setHoraFinal(Time.valueOf(rs.getString("horaFinal")));
                alquiler.setCodigoAlquiler(rs.getString("codigoAlquiler"));
                alquiler.setPeticion(rs.getString("peticion"));
                String archivado = rs.getString("archivado");
                if(archivado.equals("1")){
                    alquiler.setArchivado(true);
                }else{
                    alquiler.setArchivado(false);
                }
               
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
     * Obtiene todos los alquileres.
     * 
     * @return lista con todos los alquileres existentes
     */
    public static ArrayList<Alquiler> selectAllAlquiler() {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Alquiler";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            Alquiler alquiler = null;
            ArrayList<Alquiler> alquileres = new ArrayList();
            while (rs.next()) {
                alquiler = new Alquiler();
                alquiler.setPrecio(Double.parseDouble(rs.getString("precio")));
                alquiler.setHoraInicial(Time.valueOf(rs.getString("horaInicial")));
                alquiler.setHoraFinal(Time.valueOf(rs.getString("horaFinal")));
                alquiler.setCodigoAlquiler(rs.getString("codigoAlquiler"));
                alquiler.setPeticion(rs.getString("peticion"));
                String archivado = rs.getString("archivado");
                if(archivado.equals("1")){
                    alquiler.setArchivado(true);
                }else{
                    alquiler.setArchivado(false);
                }
                alquileres.add(alquiler);
               
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return alquileres;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
