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
import ridemybike.dominio.Bicicleta;
import ridemybike.dominio.EstadoBicicleta;
import ridemybike.dominio.db.BicicletaDB;

/**
 *
 * @author Alberto
 */
@WebServlet(name = "ActivacionBicicletasDesactivadas", urlPatterns = {"/ActivacionBicicletasDesactivadas"})
public class ActivacionBicicletasDesactivadas extends HttpServlet {

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
       

            int codigoBicicleta = Integer.parseInt(request.getParameter("codigoBicicleta"));
            Bicicleta bici = BicicletaDB.selectBicicleta(codigoBicicleta);
            EstadoBicicleta nuevoEstado = bici.getEstado();
            if(bici.getEstado().equals(EstadoBicicleta.Activado)){
                nuevoEstado = EstadoBicicleta.Desactivado;
            }else{
                nuevoEstado = EstadoBicicleta.Activado;
            }
            BicicletaDB.cambiaEstadoBicicleta(bici, nuevoEstado); 
            String url = "/RecuperarBicicletasDesactivadas";
            RequestDispatcher dispacher = getServletContext().getRequestDispatcher(url);
            dispacher.forward(request, response);


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
            Logger.getLogger(ActivacionBicicletasDesactivadas.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ActivacionBicicletasDesactivadas.class.getName()).log(Level.SEVERE, null, ex);
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
