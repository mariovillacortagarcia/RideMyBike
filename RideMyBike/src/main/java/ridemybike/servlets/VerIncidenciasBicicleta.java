package ridemybike.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ridemybike.dominio.Incidencia;
import ridemybike.dominio.db.IncidenciaDB;

/**
 * Servlet que recupera las incidencias de la bicicleta especificada en la 
 * peticion y redirige a una interfaz que las muestra
 */
@WebServlet(name = "VerIncidenciasBicicleta", urlPatterns = {"/VerIncidenciasBicicleta"})
public class VerIncidenciasBicicleta extends HttpServlet {
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        ArrayList<Incidencia> incidencias = IncidenciaDB.selectIncidenciasSinSolucionarPorBici(Integer.parseInt(request.getParameter("codigoBicicleta")));
        request.setAttribute("incidencias", incidencias);
        request.setAttribute("codigoBicicleta", request.getParameter("codigoBicicleta"));
        
        String url = "/incidencias_de_bici.jsp";
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
    
}
