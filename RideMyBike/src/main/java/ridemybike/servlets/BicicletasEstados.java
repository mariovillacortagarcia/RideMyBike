/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import ridemybike.dominio.db.UsuarioDB;
import ridemybike.dominio.EstadoBicicleta;
import ridemybike.dominio.db.BicicletaDB;
import static ridemybike.dominio.db.BicicletaDB.*;

/**
 *
 * @author Alberto
 */
@WebServlet(name = "MisBicisServlet", urlPatterns = {"/MisBicisServlet"})
public class BicicletasEstados extends HttpServlet {

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
        String botonSelector = request.getParameter("selector1");
        ArrayList<Bicicleta> v1 = new ArrayList<Bicicleta>();
        String nomUsuario = request.getParameter("nombreUsuario");
        switch(botonSelector){
            case("Bicicletas actuales"):
                v1 = getBicicletasRegistradas(nomUsuario);
                break;
            case("Bicicletas Activas"):
                EstadoBicicleta e1 = EstadoBicicleta.Activado;
                v1 = getBicicletasEstado(nomUsuario, e1);
                break;
            case("Bicicletas Desactivadas"):
                EstadoBicicleta e2 = EstadoBicicleta.Desactivado;
                v1 = getBicicletasEstado(nomUsuario, e2);
                break;
        }
        
        
        
        Bicicleta b1 = new Bicicleta();
        b1.setEstado(estadoBusqueda);
        
        v1 = BicicletaDB.getBicicletaEstado(nomUsuario,estadoBusqueda);
        
        try ( PrintWriter out = response.getWriter()) {

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
