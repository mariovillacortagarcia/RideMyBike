package ridemybike.dominio.db;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import ridemybike.dominio.Alquiler;
import ridemybike.dominio.Bicicleta;

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
        String query = "INSERT INTO Alquiler(precio,inicio,fin ,horaInicial, horaFinal, codigoPeticion, archivado) VALUES (?,?,?,?,?,?,?)"; 
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, alquiler.getPrecio()+"");
            ps.setString(2, alquiler.getInicio()+"");
            ps.setString(3, alquiler.getFin()+"");
            if(alquiler.getHoraFinal() == null){
                ps.setString(4, null);
                ps.setString(5, null);
            } else{
                ps.setString(4, alquiler.getHoraInicial().toString());
                ps.setString(5, alquiler.getHoraFinal().toString());
            }
            
            ps.setString(6, alquiler.getPeticion()+"");
            boolean archivado = alquiler.getArchivado();
            if(archivado){
                ps.setString(7, "1");
            }else{
                ps.setString(7, "0");
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
    public static Alquiler selectAlquiler(int codigoAlquiler) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Alquiler WHERE codigoAlquiler = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, codigoAlquiler+"");
            rs = ps.executeQuery();
            Alquiler alquiler = null;
            if (rs.next()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                alquiler = new Alquiler();
                alquiler.setPrecio(Double.parseDouble(rs.getString("precio")));
                alquiler.setInicio(rs.getString("inicio"));
                alquiler.setFin(rs.getString("fin"));
                alquiler.setHoraInicial(new Timestamp(dateFormat.parse(rs.getString("horaInicial")).getTime()));
                alquiler.setHoraFinal(new Timestamp(dateFormat.parse(rs.getString("horaFinal")).getTime()));
                alquiler.setCodigoAlquiler(Integer.parseInt(rs.getString("codigoAlquiler")));
                alquiler.setPeticion(Integer.parseInt(rs.getString("peticion")));
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Obtiene todos los alquileres de un usuario.
     * 
     * @param nombreUsuario el nombre de usuario
     * @return lista con todos los alquileres del usuario
     */
    public static ArrayList<Alquiler> selectAllAlquiler(String nombreUsuario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Alquiler, Peticion WHERE Alquiler.codigoPeticion = Peticion.codigoPeticion AND Peticion.usuarioArrendatario = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            rs = ps.executeQuery();
            Alquiler alquiler = null;
            ArrayList<Alquiler> alquileres = new ArrayList();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            while (rs.next()) {
                alquiler = new Alquiler();
                alquiler.setPrecio(Double.parseDouble(rs.getString("precio")));
                alquiler.setInicio(rs.getString("inicio"));
                alquiler.setFin(rs.getString("fin"));
                alquiler.setHoraInicial(new Timestamp(dateFormat.parse(rs.getString("horaInicial")).getTime()));
                Timestamp horaFinal = rs.getString("horaFinal") == null ? null : new Timestamp(dateFormat.parse(rs.getString("horaFinal")).getTime());
                alquiler.setHoraFinal(horaFinal);
                alquiler.setCodigoAlquiler(Integer.parseInt(rs.getString("codigoAlquiler")));
                alquiler.setPeticion(Integer.parseInt(rs.getString("codigoPeticion")));
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Obtiene todos los alquileres realizados de un usuario. 
     * En esta lista no se incluiran los viajes en proceso ni los alquileres 
     * archivados.
     * 
     * @param nombreUsuario el nombre de usuario
     * @return lista con todos los alquileres del usuario
     */
    public static ArrayList<Alquiler> selectAlquileresRealizados(String nombreUsuario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Alquiler, Peticion WHERE Alquiler.codigoPeticion = Peticion.codigoPeticion AND Peticion.usuarioArrendatario = ? AND Alquiler.horaFinal IS NOT NULL AND Alquiler.archivado = 0";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            rs = ps.executeQuery();
            Alquiler alquiler = null;
            ArrayList<Alquiler> alquileres = new ArrayList();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            while (rs.next()) {
                alquiler = new Alquiler();
                alquiler.setPrecio(Double.parseDouble(rs.getString("precio")));
                alquiler.setInicio(rs.getString("inicio"));
                alquiler.setFin(rs.getString("fin"));
                alquiler.setHoraInicial(new Timestamp(dateFormat.parse(rs.getString("horaInicial")).getTime()));
                Timestamp horaFinal = rs.getString("horaFinal") == null ? null : new Timestamp(dateFormat.parse(rs.getString("horaFinal")).getTime());
                alquiler.setHoraFinal(horaFinal);
                alquiler.setCodigoAlquiler(Integer.parseInt(rs.getString("codigoAlquiler")));
                alquiler.setPeticion(Integer.parseInt(rs.getString("codigoPeticion")));
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Obtiene todos los alquileres en proceso de un usuario.
     * 
     * @param nombreUsuario el nombre de usuario
     * @return lista con todos los alquileres del usuario
     */
    public static ArrayList<Alquiler> selectAlquileresEnProceso(String nombreUsuario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Alquiler, Peticion WHERE Alquiler.codigoPeticion = Peticion.codigoPeticion AND Peticion.usuarioArrendatario = ? AND Alquiler.horaFinal IS NULL";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            rs = ps.executeQuery();
            Alquiler alquiler = null;
            ArrayList<Alquiler> alquileres = new ArrayList();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            while (rs.next()) {
                alquiler = new Alquiler();
                alquiler.setPrecio(Double.parseDouble(rs.getString("precio")));
                alquiler.setInicio(rs.getString("inicio"));
                alquiler.setFin(rs.getString("fin"));
                Timestamp horaInicial = rs.getString("horaInicial") == null ? null : new Timestamp(dateFormat.parse(rs.getString("horaInicial")).getTime());
                alquiler.setHoraInicial(horaInicial);
                Timestamp horaFinal = rs.getString("horaFinal") == null ? null : new Timestamp(dateFormat.parse(rs.getString("horaFinal")).getTime());
                alquiler.setHoraFinal(horaFinal);
                alquiler.setCodigoAlquiler(Integer.parseInt(rs.getString("codigoAlquiler")));
                alquiler.setPeticion(Integer.parseInt(rs.getString("codigoPeticion")));
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Obtiene todos los alquileres archivados de un usuario.
     * 
     * @param nombreUsuario el nombre de usuario
     * @return lista con todos los alquileres del usuario
     */
    public static ArrayList<Alquiler> selectAlquileresArchivados(String nombreUsuario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Alquiler, Peticion WHERE Alquiler.codigoPeticion = Peticion.codigoPeticion AND Peticion.usuarioArrendatario = ? AND Alquiler.archivado = 1";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            rs = ps.executeQuery();
            Alquiler alquiler = null;
            ArrayList<Alquiler> alquileres = new ArrayList();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            while (rs.next()) {
                alquiler = new Alquiler();
                alquiler.setPrecio(Double.parseDouble(rs.getString("precio")));
                alquiler.setInicio(rs.getString("inicio"));
                alquiler.setFin(rs.getString("fin"));
                alquiler.setHoraInicial(new Timestamp(dateFormat.parse(rs.getString("horaInicial")).getTime()));
                Timestamp horaFinal = rs.getString("horaFinal") == null ? null : new Timestamp(dateFormat.parse(rs.getString("horaFinal")).getTime());
                alquiler.setHoraFinal(horaFinal);
                alquiler.setCodigoAlquiler(Integer.parseInt(rs.getString("codigoAlquiler")));
                alquiler.setPeticion(Integer.parseInt(rs.getString("codigoPeticion")));
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Devuelve la bicicleta usada en el alquiler
     * 
     * @param codigoAlquiler el codigo del alquiler
     * @return una Bicicleta con la bicicleta
     */
    public static Bicicleta getBicicletaDelAlquiler(int codigoAlquiler) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT codigoBici FROM Alquiler, Peticion WHERE Alquiler.codigoPeticion = Peticion.codigoPeticion AND Alquiler.codigoAlquiler = codigoAlquiler";
        try {
            // Obtenemos el codigo de la bici
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            String codigoBici = "";
            if(rs.next()){
                codigoBici = rs.getString("codigoBici");
            }
            
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            
            return BicicletaDB.selectBicicleta(Integer.parseInt(codigoBici));
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Archiva el viaje especificado
     * 
     * @param codigoAlquiler el codigo del alquiler
     * @return un entero positivo si la actualizacion ha tenido exito; 0 si ha habido algun fallo
     */
    public static int archivarViaje(int codigoAlquiler) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection= pool.getConnection();
        PreparedStatement ps= null;
        String query;
        query = "UPDATE Alquiler SET archivado = 1 WHERE codigoAlquiler = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, codigoAlquiler+"");
            
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    /**
     * Desarchiva el viaje especificado
     * 
     * @param codigoAlquiler el codigo del alquiler
     * @return un entero positivo si la actualizacion ha tenido exito; 0 si ha habido algun fallo
     */
    public static int desarchivarViaje(int codigoAlquiler) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection= pool.getConnection();
        PreparedStatement ps= null;
        String query;
        query = "UPDATE Alquiler SET archivado = 0 WHERE codigoAlquiler = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, codigoAlquiler+"");
            
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    /**
     * Elimina el viaje especificado, ocultandolo a nivel de usuario pero sin 
     * borrarlo de la base de datos
     * 
     * @param codigoAlquiler el codigo del alquiler
     * @return un entero positivo si la actualizacion ha tenido exito; 0 si ha habido algun fallo
     */
    public static int eliminarViaje(int codigoAlquiler) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection= pool.getConnection();
        PreparedStatement ps= null;
        String query;
        query = "UPDATE Alquiler SET archivado = 2 WHERE codigoAlquiler = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, codigoAlquiler+"");
            
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    } 
    
    /**
     * Termina el viaje especificado
     * 
     * @param codigoAlquiler el codigo del alquiler
     * @ubicacionFinal la ubicacion de fin del viaje
     * @return un entero positivo si la actualizacion ha tenido exito; 0 si ha habido algun fallo
     */
    public static int terminarViaje(int codigoAlquiler, String ubicacionFinal) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection= pool.getConnection();
        PreparedStatement ps= null;
        String query;
        query = "UPDATE Alquiler SET horaFinal = ?, fin = ? WHERE codigoAlquiler = ?";

        try {
            ps = connection.prepareStatement(query);
            Timestamp horaFinal = new Timestamp(System.currentTimeMillis());
            ps.setString(1, horaFinal.toString());
            ps.setString(2, ubicacionFinal);
            ps.setString(3, codigoAlquiler+"");
            
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    } 
}
