package ridemybike.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ridemybike.dominio.Alquiler;
import ridemybike.dominio.Bicicleta;
import ridemybike.dominio.EstadoBicicleta;
import ridemybike.dominio.Peticion;
import ridemybike.dominio.db.AlquilerDB;
import ridemybike.dominio.db.BicicletaDB;
import ridemybike.dominio.db.PeticionDB;

/**
 *
 * @author Mario Villacorta
 */
@WebServlet(name = "IniciarViaje", urlPatterns = {"/IniciarViaje"})
public class IniciarViaje extends HttpServlet {

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
            throws ServletException, IOException, ParseException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        int codigoAlquiler = Integer.parseInt(request.getParameter("codigoAlquiler"));
        Alquiler alquiler = AlquilerDB.selectAlquiler(codigoAlquiler);
        Peticion peticion = PeticionDB.selectPeticion(alquiler.getPeticion());
        Timestamp horaLimite = peticion.getHoraLimite();
        Bicicleta bici = AlquilerDB.getBicicletaDelAlquiler(codigoAlquiler);
        String ubicacionInicial = "{" + '"' + "lat" + '"' + ":" + bici.getLatitud() + ", " + '"' + "lng" + '"' + ":" + bici.getLongitud() + "}";
        AlquilerDB.iniciarViaje(codigoAlquiler, ubicacionInicial);
        
        if (horaLimite.compareTo(new Timestamp(System.currentTimeMillis())) < 0) {
            BicicletaDB.cambiaEstadoBicicleta(bici, EstadoBicicleta.Activado);
            String ubicacionFinal = "{"+'"'+"lat"+'"'+":"+bici.getLatitud()+", "+'"'+"lng"+'"'+":"+bici.getLongitud()+"}";
            AlquilerDB.terminarViaje(Integer.parseInt(request.getParameter("codigoAlquiler")), ubicacionFinal);
        }
        String url = "/RecuperarViajesEnProceso";
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
