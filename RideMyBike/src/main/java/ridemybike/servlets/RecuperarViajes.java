package ridemybike.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ridemybike.dominio.Alquiler;
import ridemybike.dominio.Bicicleta;
import ridemybike.dominio.Peticion;
import ridemybike.dominio.Usuario;
import ridemybike.dominio.db.AlquilerDB;
import ridemybike.dominio.db.BicicletaDB;
import ridemybike.dominio.db.PeticionDB;

@WebServlet(name = "RecuperarViajes", urlPatterns = {"/RecuperarViajes"})
public class RecuperarViajes extends HttpServlet {

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
        if (request.getSession().getAttribute("usuario") == null) {
            String url = "/iniciar_sesion.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
        String nombreUsuario = request.getSession().getAttribute("usuario").toString();
        ArrayList<Alquiler> alquileres = AlquilerDB.selectAlquileresRealizados(nombreUsuario);
        for (int i = 0; i < alquileres.size(); i++) {
            if (AlquilerDB.isUsuarioPropietarioValorado(alquileres.get(i).getCodigoAlquiler() + "")) {
                alquileres.get(i).marcarUsuarioValorado();
            }
            if (AlquilerDB.isBicicletaValorada(alquileres.get(i).getCodigoAlquiler() + "")) {
                alquileres.get(i).marcarBiciValorada();
            }
        }
        ArrayList<Peticion> peticiones = PeticionDB.selectPeticionesAlquileres(alquileres, nombreUsuario);
        ArrayList<Bicicleta> bicicletas = BicicletaDB.selectBicicletasPeticiones(peticiones);
        request.setAttribute("alquileres", alquileres);
        request.setAttribute("peticiones", peticiones);
        request.setAttribute("bicicletas", bicicletas);

        String url = "/viajes.jsp";
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
