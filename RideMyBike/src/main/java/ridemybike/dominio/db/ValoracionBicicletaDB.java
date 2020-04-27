package ridemybike.dominio.db;

import java.sql.*;
import java.util.ArrayList;
import ridemybike.dominio.Bicicleta;
import ridemybike.dominio.ValoracionBicicleta;



public class ValoracionBicicletaDB{

    public static int insertarValoracionBicicleta(ValoracionBicicleta valoracionBici) throws SQLException{
        if(valoracionBici == null){
            throw new IllegalArgumentException("Valoracion igual a null");
        }
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        String query = "INSERT INTO ValoracionBicicleta(codigoAlquiler, descripcion, puntuacion, codigoBicicleta) VALUES (?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, Integer.toString(valoracionBici.getCodigo()));
            ps.setString(2, valoracionBici.getDescripcion());
            ps.setString(3, Integer.toString(valoracionBici.getPuntuacion()));
            ps.setString(4, valoracionBici.getCodigoBicicleta()+"");
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
                valoracionBici.setCodigoBicicleta(Integer.parseInt(rs.getString("codigoBicicleta")));
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
  
    
    
    public static ArrayList<ValoracionBicicleta> getValoraciones(String codigoBicicleta){
        if(codigoBicicleta == null || codigoBicicleta.equals("")){
            throw new IllegalArgumentException("El codigo de la bicicleta no puede ser no valida");
        }
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection= pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        String query= "SELECT * FROM ValoracionBicicleta WHERE codigoBicicleta = ?";
        ArrayList<ValoracionBicicleta> lista = new ArrayList<ValoracionBicicleta>();
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, codigoBicicleta);
            rs = ps.executeQuery();
            ValoracionBicicleta valoracion = null;
            while (rs.next()) {
                valoracion = new ValoracionBicicleta();
                valoracion.setCodigo(Integer.parseInt(rs.getString("codigoAlquiler")));
                valoracion.setDescripcion(rs.getString("descripcion"));
                valoracion.setPuntuacion(Integer.parseInt(rs.getString("puntuacion")));
                valoracion.setCodigoBicicleta(Integer.parseInt(rs.getString("codigoBicicleta")));
                lista.add(valoracion);
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return lista;
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
        String query= "DELETE FROM ValoracionBicicleta“+“WHERE codigoBici= ‘”+codigoBici+ “’”;";
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        rs.close();
        ps.close();
        pool.freeConnection(connection);
    }
}