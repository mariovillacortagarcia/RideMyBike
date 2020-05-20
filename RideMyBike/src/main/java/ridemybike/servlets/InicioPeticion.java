package ridemybike.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ridemybike.dominio.Alquiler;
import ridemybike.dominio.Peticion;
import ridemybike.dominio.TipoAlquiler;
import ridemybike.dominio.db.AlquilerDB;
import ridemybike.dominio.db.PeticionDB;


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
        
        HttpSession session= request.getSession();
        if(session.getAttribute("usuario") == null){
        String url = "/iniciar_sesion.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
        }
        String nombreArrendatario = session.getAttribute("usuario").toString();
        String codigoBici = request.getParameter("bicicletaId");
        String horaInicio = request.getParameter("horaInicioPrestamo");
        String fechaInicio = request.getParameter("fechaInicioPrestamo");
        String horaFin = request.getParameter("horaFinPrestamo");
        String fechaFin = request.getParameter("fechaFinPrestamo");
        String llegareTarde = request.getParameter("llegareTarde");
        String seguroViaje = request.getParameter("seguroViaje");
        String alquilerEnMano = request.getParameter("alquilerEnMano");
              
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date parsedDate = dateFormat.parse(fechaInicio+" "+horaInicio+":00.000");
        Timestamp horaInicioPeticion = new java.sql.Timestamp(parsedDate.getTime());
        Timestamp horaLimite;
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(horaInicioPeticion.getTime());
        cal.add(Calendar.MINUTE, llegareTarde == null ? 15 : 15+30);
        horaLimite = new Timestamp(cal.getTime().getTime());
        
        Peticion peticion = new Peticion();
        peticion.setCodigoBici(Integer.parseInt(codigoBici));
        peticion.setNombreArrendatario(nombreArrendatario);
        peticion.setHoraInicio(horaInicioPeticion);
        peticion.setHoraLimite(horaLimite);
        peticion.setTipo(alquilerEnMano == null ? TipoAlquiler.estandar : TipoAlquiler.enMano);
        
        int codigoPeticion = PeticionDB.insertarPeticion(peticion);
        
        Alquiler alquiler = new Alquiler();
        parsedDate = dateFormat.parse(fechaFin+" "+horaFin+":00.000");
        Timestamp horaFinPeticion = new java.sql.Timestamp(parsedDate.getTime());
        double precio = ((horaFinPeticion.getTime()-horaInicioPeticion.getTime())/60000)*1.0/100;
        if(seguroViaje != null){
            precio ++;
        }
        if(llegareTarde != null){
            precio ++;
        }
        alquiler.setPrecio(precio);
        alquiler.setPeticion(codigoPeticion);
        alquiler.setArchivado(false);
        alquiler.setInicio("?");
        alquiler.setFin("?");
        
        AlquilerDB.insertarAlquiler(alquiler);
              
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(InicioPeticion.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(InicioPeticion.class.getName()).log(Level.SEVERE, null, ex);
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
