package ridemybike.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ridemybike.dominio.MensajeUsuario;
import ridemybike.dominio.db.MensajeUsuarioDB;
import ridemybike.dominio.db.UtilitiesDB;

/**
 * Implementacion de un servlet que registra en la base de datos el problema o
 * sugerencia especificado en la peticion
 */
@WebServlet(name = "RegistrarProblemaSugerencia", urlPatterns = {"/RegistrarProblemaSugerencia"})
@MultipartConfig
public class RegistrarProblemaSugerencia extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String usuario = request.getSession().getAttribute("usuario").toString();
        String asunto = request.getParameter("asunto");
        String descripcion = request.getParameter("mensaje");

        boolean todoCorrecto = true;

        if (asunto.isBlank() || UtilitiesDB.posibleInyeccionSQL(asunto)) {
            request.setAttribute("errorAsunto", "Este asunto no es válido.");
            todoCorrecto = false;
        }
        if (descripcion.isBlank() || UtilitiesDB.posibleInyeccionSQL(descripcion)) {
            request.setAttribute("errorMensaje", "El contenido del mensaje es válido.");
            todoCorrecto = false;
        }

        MensajeUsuario mensaje = new MensajeUsuario();
        mensaje.setNombreUsuario(usuario);
        mensaje.setAsunto(asunto);
        mensaje.setDescripcion(descripcion);
        mensaje.setFechaCreacion(new Timestamp(System.currentTimeMillis()));

        String url;
        if (todoCorrecto) {
            String tipoMensaje = request.getParameter("categoria");
            if (tipoMensaje.equals("duda")) {
                MensajeUsuarioDB.insertDudaSugerencia(mensaje);
            }
            if (tipoMensaje.equals("problema")) {
                MensajeUsuarioDB.insertProblema(mensaje);
            }
            url = "/RecuperarPerfil";
        } else {
            url = "/contactanos.jsp";
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
            Logger.getLogger(RegistrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
