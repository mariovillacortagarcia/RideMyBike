package ridemybike.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ridemybike.dominio.GradoIncidencia;
import ridemybike.dominio.Incidencia;
import ridemybike.dominio.db.IncidenciaDB;

/**
 *
 * @author Mario Villacorta
 */
@WebServlet(name = "RegistroIncidencia", urlPatterns = {"/RegistroIncidencia"})
public class RegistroIncidencia extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            Incidencia incidencia = new Incidencia();
            String descripcionIncidencia =  request.getParameter("descripcionIncidencia");
            
            if (descripcionIncidencia.isBlank()) {
                request.setAttribute("errorDescripcion", "Este descripcion no es válido.");
          
        }
            String grado = (String) request.getParameter("grado");
            switch (grado) {
                case "leve":
                    incidencia.setGravedad(GradoIncidencia.Leve);
                    break;
                case "moderada":
                    incidencia.setGravedad(GradoIncidencia.Moderado);
                    break;
                default:
                    incidencia.setGravedad(GradoIncidencia.Grave);
            }
            int idAlquiler = Integer.parseInt((String) request.getParameter("idAlquiler"));
            incidencia.setCodigoPeticion(idAlquiler);
            incidencia.setDescripcion(descripcionIncidencia);
            
            int codigoIncidencia= IncidenciaDB.insertarIncidencia(incidencia);
            incidencia.setCodigoIncidencia(codigoIncidencia);
            String url = "/incidenciaRegistroCorrecto.jsp";
            HttpSession session= request.getSession();
            session.getAttribute("usuario").toString();
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            String url = "/incidenciaRegistroIncorrecto.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
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
