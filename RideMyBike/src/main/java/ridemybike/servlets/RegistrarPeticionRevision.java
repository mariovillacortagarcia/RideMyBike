package ridemybike.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import ridemybike.dominio.Bicicleta;
import ridemybike.dominio.EstadoBicicleta;
import ridemybike.dominio.Freno;
import ridemybike.dominio.PeticionRevision;
import ridemybike.dominio.db.BicicletaDB;
import ridemybike.dominio.db.PeticionRevisionDB;
import ridemybike.dominio.db.UtilitiesDB;

/**
 * Insertar una bicicleta en la BD pendiente de aprovacion
 *
 * @author Alberto
 */
@WebServlet(name = "RegistrarPeticionRevision", urlPatterns = {"/RegistrarPeticionRevision"})
@MultipartConfig
public class RegistrarPeticionRevision extends HttpServlet {

    private final String TAMANO_CUADRO_INCORRECTO = "Este campo debe ser una cifra de 3 números (cm)";
    private final String TAMANO_DESCRIPCION_NOVALIDO = "Este campo es demasiado pequeño o contiene caracteres ilegales(\\,\",\',\\x00,\\x1,-,_), recuerda que tu descripción de la bici debe ser la mejor posible para que los usuarios quieran alquilarla";
    private final String ERROR_MARCA = "Esta marca no es válida";
    private final String ERROR_MODELO = "Este modelo no es válido";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");

        String modelo = request.getParameter("modelo");
        String marca = request.getParameter("marca");
        String tamanoCuadro = request.getParameter("tamanoCuadro");
        String descripcion = request.getParameter("descripcion");
        String tipoFreno = request.getParameter("tipoFreno");
        String ciudad = request.getParameter("ciudad");
        String fecha1 = request.getParameter("fecha1");
        String hora1 = request.getParameter("hora1");
        HttpSession session = request.getSession();
        String nombreUsuario = session.getAttribute("usuario").toString();
        Part foto = request.getPart("foto");
        boolean hayFallos = false;
        String url;
        
        request.setAttribute("marca", marca);
        request.setAttribute("modelo", modelo);
        request.setAttribute("descripcion", descripcion);
        request.setAttribute("tamCuadro", tamanoCuadro);
        
        if (marca.isBlank() || UtilitiesDB.posibleInyeccionSQL(marca)) {
            hayFallos = true;
            request.setAttribute("errorMarca", ERROR_MARCA);
        }
        if (modelo.isBlank() || UtilitiesDB.posibleInyeccionSQL(modelo)) {
            hayFallos = true;
            request.setAttribute("errorModelo", ERROR_MODELO);
        }
        if (descripcion.isBlank() || UtilitiesDB.posibleInyeccionSQL(descripcion)) {
            hayFallos = true;
            request.setAttribute("errorDescripcion", TAMANO_DESCRIPCION_NOVALIDO);
        }
        try {
            Double variable = Double.parseDouble(tamanoCuadro);
        } catch (Exception e) {
            hayFallos = true;
            request.setAttribute("errorTamano", TAMANO_CUADRO_INCORRECTO);
        }
        if (!hayFallos) {
            Bicicleta bici = new Bicicleta();
            bici.setDescripcion(descripcion);
            bici.setMarca(marca);
            bici.setModelo(modelo);
            bici.setTamCuadro(Double.parseDouble(tamanoCuadro));
            Freno freno = Freno.valueOf(tipoFreno);
            bici.setFreno(freno);
            EstadoBicicleta estado = EstadoBicicleta.Pendiente;
            bici.setEstado(estado);
            bici.setUsuarioPropietario(nombreUsuario);
            UUID idUno = UUID.randomUUID();
            bici.setCodigoActivacion(idUno.toString());
            bici.setImagen(foto);
            int codigoBici = BicicletaDB.insertarBicicleta(bici);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(fecha1 + " " + hora1 + ":00.000");
            Timestamp fechaPeticion = new java.sql.Timestamp(parsedDate.getTime());

            PeticionRevision peticion = new PeticionRevision();
            peticion.setCiudad(ciudad);
            peticion.setCodigoUsuario(nombreUsuario);

            peticion.setFecha(fechaPeticion);

            peticion.setCodigoBicicleta(codigoBici);
            PeticionRevisionDB.insertarPeticionRevision(peticion);

            url = "/direccionRegistroCorrecto.jsp";

        } else {
            url = "/registrar_bicicletaFallo.jsp";
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarPeticionRevision.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(RegistrarPeticionRevision.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarPeticionRevision.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(RegistrarPeticionRevision.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
