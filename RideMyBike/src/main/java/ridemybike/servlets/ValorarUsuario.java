package ridemybike.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ridemybike.dominio.ValoracionUsuario;
import ridemybike.dominio.db.UtilitiesDB;
import ridemybike.dominio.db.ValoracionUsuarioDB;

/**
 *
 * @author Mario Villacorta
 */
@WebServlet(name = "ValorarUsuario", urlPatterns = {"/ValorarUsuario"})
public class ValorarUsuario extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ValoracionUsuario val = new ValoracionUsuario();
        String descripcionValoracionUsuario = (String) request.getParameter("descripcion");
        if (descripcionValoracionUsuario.isBlank() || UtilitiesDB.posibleInyeccionSQL(descripcionValoracionUsuario)) {
            request.setAttribute("errorDescripcion", "La descripcion de la valoración no es correcta.");
        }
        val.setCodigo(Integer.parseInt(request.getParameter("codigoAlquiler")));
        val.setDescripcion(request.getParameter("descripcion"));
        val.setPuntuacion(Integer.parseInt((String) request.getParameter("valoracion")));
        val.setUsuarioValorado(request.getParameter("usuarioValorado"));
        try {
            ValoracionUsuarioDB.insertarValoracionUsuario(val);
        } catch (SQLException ex) {
            Logger.getLogger(ValorarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        String url = "/RecuperarViajes";
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
        processRequest(request, response);
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
        processRequest(request, response);
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
