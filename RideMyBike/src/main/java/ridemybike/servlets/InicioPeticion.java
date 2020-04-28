package ridemybike.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ridemybike.dominio.Peticion;


/**
 * Servlet para crear una nueva petición.
 */
@WebServlet(name = "InicioPeticion", urlPatterns = {"/InicioPeticion"})
public class InicioPeticion extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        
        String nombreArrendatario = "juan.pperez"; 
        String codigoBici = request.getParameter("bicicletaId");
        String horaInicio = request.getParameter("horaInicioPrestamo");
        String fechaInicio = request.getParameter("fechaInicioPrestamo");
        String llegareTarde = request.getParameter("llegareTarde");
        String seguroViaje = request.getParameter("seguroViaje");
        
        throw new IllegalStateException("\n\n\n\n fechaInicio: "+fechaInicio+" horaInicio: "+horaInicio+"\n\n\n\n");
        /*Peticion peticion = new Peticion();
        peticion.setCodigoBici(Integer.parseInt(codigoBici));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date parsedDate = dateFormat.parse(fechaInicio.replace('/', '-')+" ");
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
        
        
        
        
        
        String url = "/RecuperarViajesEnProceso";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);*/
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
