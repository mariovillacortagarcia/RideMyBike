/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridemybike.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ActivacionBicicleta", urlPatterns = {"/ActivacionBicicleta"})
public class ActivacionBicicleta extends HttpServlet {

    /**
     * Metodo para cambiar el estado de la bicicleta. (Activado -> Desactivado)
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
                nuevoEstado = nuevoEstado.Desactivado;
            }else{
                nuevoEstado = nuevoEstado.Activado;
            }
            BicicletaDB.cambiaEstadoBicicleta(bici, nuevoEstado); 
            String url = "/RecuperarBicicletas";
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
            Logger.getLogger(ActivacionBicicleta.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ActivacionBicicleta.class.getName()).log(Level.SEVERE, null, ex);
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
