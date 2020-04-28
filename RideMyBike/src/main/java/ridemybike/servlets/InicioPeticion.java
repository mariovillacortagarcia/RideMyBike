/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridemybike.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.time.LocalTime;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLEditorKit;
import ridemybike.dominio.Peticion;
import ridemybike.dominio.TipoAlquiler;
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String codigoBici = request.getParameter("bicicletaId");
        String hora = request.getParameter("fechaInicioPrestamo");
        String tiempoLimite = request.getParameter("fechaFinPrestamo");
        String nombreArrendatario = "juan.pperez";            //---------------> Usuario de ejemplo
        
        Peticion peticion = new Peticion();
        peticion.setCodigoBici(Integer.parseInt(codigoBici));
        peticion.setHora(Time.valueOf(hora));
        peticion.setTiempoLimite(Time.valueOf(tiempoLimite));
        peticion.setNombreArrendatario(nombreArrendatario);
        
        boolean seguro = Boolean.parseBoolean(request.getParameter("opSeguro"));
        boolean tarde = Boolean.parseBoolean(request.getParameter("opTarde"));
        boolean enMano = Boolean.parseBoolean(request.getParameter("opEnMano"));
        if (enMano){
            peticion.setTipo(TipoAlquiler.enMano);
        }else{
            peticion.setTipo(TipoAlquiler.estandar);
        }
        PeticionDB.insertarPeticion(peticion);
        
        int minutosEnAlquiler =(int) ((Time.valueOf(hora).getTime()-Time.valueOf(tiempoLimite).getTime())/60000);
        double precio = 0.1*minutosEnAlquiler;
        if (seguro){
            precio = precio+1;        // suplemento de 1 euro por contrato de seguro.
        }
        if (tarde){
            precio = precio+1;        // suplemento de 1 euro por ampliar plazo de alquiler al llegar tarde.
        }
        
        request.setAttribute("precio", precio);
        String url = "/index.jsp";
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
