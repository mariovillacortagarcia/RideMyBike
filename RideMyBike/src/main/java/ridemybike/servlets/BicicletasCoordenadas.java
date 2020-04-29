package ridemybike.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ridemybike.dominio.Bicicleta;
import ridemybike.dominio.db.BicicletaDB;


/**
 *
 * @author Mario Villacorta
 */
@WebServlet(name = "BicicletasCoordenadas", urlPatterns = {"/BicicletasCoordenadas"})
public class BicicletasCoordenadas extends HttpServlet {

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
        ArrayList<Bicicleta> bicicletas = (ArrayList<Bicicleta>)BicicletaDB.selectAllBicicleta();
        String ubicaciones ="[";
        String ubicacion = null;
        String lat;
        String lon;
        String id;
        if(bicicletas != null)
        for(Bicicleta bicicleta : bicicletas){
           id = Integer.toString(bicicleta.getcodigoBici());
           lat = Double.toString(bicicleta.getLatitud());
           lon = Double.toString(bicicleta.getLongitud());
           ubicacion = "{"+'"'+"id"+'"'+":"+id+','+'"'+"lat"+'"'+":"+lat+','+'"'+"lon"+'"'+":"+ lon+"},";
           ubicaciones += ubicacion;
        }
        if(ubicacion != null)
            ubicaciones = ubicaciones.substring(0, ubicaciones.length()-1);
        ubicaciones += "]";
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.write(ubicaciones);
            out.flush();
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
