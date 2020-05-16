package ridemybike.dominio.db;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import ridemybike.dominio.Bicicleta;
import ridemybike.dominio.EstadoBicicleta;
import ridemybike.dominio.Freno;

public class BicicletaDB {

    /**
     * Metodo para almacenar una nueva bicicleta al sistema
     *
     * @param bicicleta Es la nueva bicicleta que se va a almacenar en el
     * sistama
     * @return El numero de lineas anadidas
     * @throws SQLException En caso de que exista algun fallo relacionado con la
     * base de datos
     * @throws IOException
     */
    public static int insertarBicicleta(Bicicleta bicicleta) throws SQLException, IOException {
        if (bicicleta == null) {
            throw new IllegalArgumentException("Bicicleta igual a null");
        }
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        String query = "INSERT INTO Bicicleta(descripcion, modelo, tamCuadro, imagen, marca, freno, latitud, longitud, usuarioPropietario, estado, codigoActivacion, eliminada) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, bicicleta.getDescripcion());
            ps.setString(2, bicicleta.getModelo());
            ps.setString(3, bicicleta.getTamCuadro() + "");
            ps.setBlob(4, bicicleta.getImagen().getInputStream());
            ps.setString(5, bicicleta.getMarca());
            ps.setString(6, bicicleta.getFreno() + "");
            ps.setString(7, bicicleta.getLatitud() + "");
            ps.setString(8, bicicleta.getLongitud() + "");
            ps.setString(9, bicicleta.getUsuarioPropietario());
            ps.setString(10, bicicleta.getEstado() + "");
            ps.setString(11, bicicleta.getCodigoActivacion());
            ps.setString(12, 0 + "");
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int res = 0;
            if (rs.next()) {
                res = rs.getInt(1);
            }
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Metodo para devolver una bicicleta almacenada segun su codigo de
     * bicicleta
     *
     * @param codigoBici Es el codigo asignado a la bicicleta que queremos
     * extraer
     * @return Una bicicleta almacenada en el sistema
     */
    public static Bicicleta selectBicicleta(int codigoBici) {
        if (codigoBici <= 0) {
            throw new IllegalArgumentException("El codigo de la bicicleta es <= 0");
        }
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Bicicleta WHERE codigoBici = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, codigoBici + "");
            rs = ps.executeQuery();
            Bicicleta bicicleta = null;
            if (rs.next()) {
                bicicleta = new Bicicleta();
                bicicleta.setCodigoBici(Integer.parseInt(rs.getString("codigoBici")));
                bicicleta.setDescripcion(rs.getString("descripcion"));
                bicicleta.setModelo(rs.getString("modelo"));
                bicicleta.setTamCuadro(Double.parseDouble(rs.getString("tamCuadro")));
                bicicleta.setMarca(rs.getString("marca"));
                bicicleta.setFreno(Freno.valueOf(rs.getString("freno")));
                bicicleta.setLatitud(Double.parseDouble(rs.getString("latitud")));
                bicicleta.setLongitud(Double.parseDouble(rs.getString("longitud")));
                bicicleta.setUsuarioPropietario(rs.getString("usuarioPropietario"));
                bicicleta.setEstado(EstadoBicicleta.valueOf(rs.getString("estado")));
                bicicleta.setCodigoActivacion(rs.getString("codigoActivacion"));
                bicicleta.setEliminada(Integer.parseInt(rs.getString("eliminada")));
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return bicicleta;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo que returna todas las bicicletas almacenadas en el sistema
     *
     * @return Lista de todas las bicicletas almacenadas en el sistema
     */
    public static ArrayList<Bicicleta> selectAllBicicleta() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Bicicleta ";
        ArrayList<Bicicleta> lista = new ArrayList<Bicicleta>();
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            Bicicleta bicicleta = null;
            while (rs.next()) {
                bicicleta = new Bicicleta();
                bicicleta.setCodigoBici(Integer.parseInt(rs.getString("codigoBici")));
                bicicleta.setDescripcion(rs.getString("descripcion"));
                bicicleta.setModelo(rs.getString("modelo"));
                bicicleta.setTamCuadro(Double.parseDouble(rs.getString("tamCuadro")));

//                bicicleta.setImagen((Part)rs.getBlob("imagen"));
                //bicicleta.setImagen((Part)rs.getBlob("imagen"));
                bicicleta.setMarca(rs.getString("marca"));
                bicicleta.setFreno(Freno.valueOf(rs.getString("freno")));
                bicicleta.setLatitud(Double.parseDouble(rs.getString("latitud")));
                bicicleta.setLongitud(Double.parseDouble(rs.getString("longitud")));
                bicicleta.setUsuarioPropietario(rs.getString("usuarioPropietario"));
                bicicleta.setEstado(EstadoBicicleta.valueOf(rs.getString("estado")));
                bicicleta.setCodigoActivacion(rs.getString("codigoActivacion"));
                bicicleta.setEliminada(Integer.parseInt(rs.getString("eliminada")));
                lista.add(bicicleta);
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Funcion que realiza la eliminacion de una bicicleta de cara al usuario.
     * No sera borrada de la base de datos
     *
     * @param codigoBici Es el codigo de la bici que se usa para identificarla
     * @return un entero positivo si la actualizacion ha tenido exito; 0 si ha habido algun fallo
     * @throws SQLException Excepcion relacionada con la base de datos
     */
    public static int eliminaBicicleta(int codigoBici) throws SQLException {
        if (codigoBici <= 0) {
            throw new IllegalArgumentException("El codigo de la bicicleta es menor o igual que 0");
        }

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        String query = "UPDATE Bicicleta SET eliminada = 1 WHERE codigoBici= ?";

        ps = connection.prepareStatement(query);
        ps.setString(1, codigoBici + "");
        int res = ps.executeUpdate();
        ps.close();
        pool.freeConnection(connection);
        return res;
    }

    /**
     * Funcion que devuelve las bicicletas de un usuario que tienen un estado
     * determinado
     *
     * @param nombreUsuario Es el identificador del usuario dueno de las bicis
     * @param estado Es el tipo EstadoBicicleta que hace referencia al estado de
     * las bicis a recuperar
     * @return Un ArrayList formado por todas las bicicletas de un dueno segun
     * su estado.
     */
    public static ArrayList<Bicicleta> getBicicletasEstado(String nombreUsuario, EstadoBicicleta estado) {
        ArrayList<Bicicleta> lista = new ArrayList<Bicicleta>();
        lista = getBicicletasRegistradas(nombreUsuario);
        ArrayList<Bicicleta> listaResultado = new ArrayList<Bicicleta>();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEstado() == estado) {
                listaResultado.add(lista.get(i));
            }
        }
        return listaResultado;
    }

    /**
     * Funcion que devuelve todas las bicicletas de un usuario en concreto
     *
     * @param nombreUsuario Es el string que determina que usuario es el dueno
     * de las bicicletas
     * @return un ArrayList con las bicicletas del usuario
     */
    public static ArrayList<Bicicleta> getBicicletasRegistradas(String nombreUsuario) {
        if (nombreUsuario == null || nombreUsuario.equals("")) {
            throw new IllegalArgumentException("Nombre de usuario no aceptado ");
        }
        ArrayList<Bicicleta> lista = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        try {
            String query = "SELECT * FROM Bicicleta WHERE usuarioPropietario = ? AND Bicicleta.eliminada = 0";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nombreUsuario);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Bicicleta bicicleta = new Bicicleta();
                bicicleta.setCodigoBici(Integer.parseInt(rs.getString("codigoBici")));
                bicicleta.setDescripcion(rs.getString("descripcion"));
                bicicleta.setModelo(rs.getString("modelo"));
                bicicleta.setTamCuadro(Double.parseDouble(rs.getString("tamCuadro")));
                bicicleta.setMarca(rs.getString("marca"));
                bicicleta.setFreno(Freno.valueOf(rs.getString("freno")));
                bicicleta.setLatitud(Double.parseDouble(rs.getString("latitud")));
                bicicleta.setLongitud(Double.parseDouble(rs.getString("longitud")));
                bicicleta.setUsuarioPropietario(rs.getString("usuarioPropietario"));
                bicicleta.setEstado(EstadoBicicleta.valueOf(rs.getString("estado")));
                //bicicleta.setCodigoActivacion(rs.getString("codigoAcivacion"));
                lista.add(bicicleta);
            }

            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Metodo que devuelve la imagen almacenada de una bicicleta
     *
     * @param codigoBicicleta Es el codigo asociado a la bicicleta cuya foto se
     * quiere extraer
     * @param respuesta Sera el formato sobre el que se devolvera la imagen de
     * la bicleta
     */
    public static void getImagen(int codigoBicicleta, OutputStream respuesta) {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            PreparedStatement statement = null;
            statement = connection.prepareStatement("SELECT imagen FROM Bicicleta WHERE codigoBici=? ");
            statement.setString(1, codigoBicicleta + "");
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Blob blob = result.getBlob("imagen");
                if (!result.wasNull() && blob.length() > 1) {
                    InputStream imagen = blob.getBinaryStream();
                    byte[] buffer = new byte[1000];
                    int len = imagen.read(buffer);
                    while (len != -1) {
                        respuesta.write(buffer, 0, len);
                        len = imagen.read(buffer);
                    }
                    imagen.close();
                }
            }
            pool.freeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para seleccionar las bicicletas de una lista que tengan un estado
     * en concreto
     *
     * @param lista Es la lista de bicicletas que se tienen
     * @param estado Es el estado de la bicicleta con el que se compara el
     * estado de las bicicletas de la lista para obtener las deseadas
     * @return Lista de bicicletas cuyo estado es el que se ha pasado por
     * argumento
     */
    public static ArrayList<Bicicleta> seleccionaBicicletas(ArrayList<Bicicleta> lista, EstadoBicicleta estado) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEstado() != estado) {
                lista.remove(i);
            }
        }
        return lista;
    }

    /**
     * Funcion que sirve para cambiar el estado de una bicicleta almacenada
     *
     * @param bici Es la bicicleta de la que se quiere cambiar su estado
     * @param estadoNuevo Es el nuevo estado que tomara la bicicleta
     * @throws SQLException Excepcion que se lanza si existe algun tipo de fallo
     * con la base de datos
     * @throws IOException
     */
    public static int cambiaEstadoBicicleta(Bicicleta bici, EstadoBicicleta estadoNuevo) throws SQLException, IOException {
        String estadoN = estadoNuevo.toString();
        String codigoBici = bici.getcodigoBici() + "";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement statement = null;
        String query = "UPDATE Bicicleta SET estado = '" + estadoN + "' WHERE codigoBici =" + codigoBici;
        try {
            ps = connection.prepareStatement(query);

            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Funcion que sirve para activar una bicicleta con su codigo de activacion
     *
     * @param codigoActivacion Es el codigo de activacion de la Bicicleta
     * @param codigoBicicleta Es el identificador de la bicicleta la cual se va
     * a identificar
     * @return un entero positivo si la actualizacion ha tenido exito; 0 si ha habido algun fallo
     * @throws SQLException Excepcion que se lanza si existe algun tipo de fallo
     * con la base de datos
     * @throws IOException
     */
    public static int activaBicicletaCodigo(String codigoActivacion, int codigoBicicleta) throws SQLException, IOException {
        if (codigoActivacion == null || codigoActivacion.equals("")) {
            throw new IllegalArgumentException("El codigo de activacion de la bicicleta introducida NO es valido.");
        }
        if (codigoBicicleta <= 0) {
            throw new IllegalArgumentException("El codigo de la bicicleta introducido NO es valido.");
        }
        Bicicleta b1 = BicicletaDB.selectBicicleta(codigoBicicleta);
        if(b1.getCodigoActivacion().equals(codigoActivacion)){
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            PreparedStatement statement = null;
            String query = "UPDATE Bicicleta SET estado = 'Activado' , codigoActivacion = ? WHERE codigoBici = ?";
            try {
                ps = connection.prepareStatement(query);
                ps.setString(1, codigoActivacion);
                ps.setString(2, codigoBicicleta+"");

                int res = ps.executeUpdate();
                ps.close();
                pool.freeConnection(connection);
                return res;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

}
