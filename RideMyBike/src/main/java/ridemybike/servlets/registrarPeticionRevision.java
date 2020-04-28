/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridemybike.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Double.parseDouble;
import static java.lang.System.out;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import ridemybike.dominio.Bicicleta;
import ridemybike.dominio.EstadoBicicleta;
import ridemybike.dominio.Freno;
import ridemybike.dominio.PeticionRevision;
import ridemybike.dominio.db.BicicletaDB;
import ridemybike.dominio.db.PeticionRevisionDB;

/**
 * Insertar una bicicleta en la BD pendiente de aprovacion
 *
 * @author Alberto
 */
@WebServlet(name = "registrarPeticionRevision", urlPatterns = {"/registrarPeticionRevision"})
public class registrarPeticionRevision extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String Marca = request.getParameter("marca");
            String Modelo = request.getParameter("modelo");
            String TamanoCuadro = request.getParameter("tamanoCuadro");
            String Descripcion = request.getParameter("descripcion");
            String TipoFreno = request.getParameter("tipoFreno");
            String Ciudad = request.getParameter("ciudad");
            String fecha1 = request.getParameter("fecha");
            String hora1 = request.getParameter("hora");
            String nombreUsuario = "juan.pperez";
            Part foto = request.getPart("foto");

            Bicicleta bici = new Bicicleta();
            bici.setMarca(Marca);
            bici.setDescripcion(Descripcion);
            bici.setModelo(Modelo);
            bici.setTamCuadro(Double.parseDouble(TamanoCuadro));
            Freno freno = Freno.valueOf(TipoFreno);
            bici.setFreno(freno);
            EstadoBicicleta estado = EstadoBicicleta.Pendiente;
            bici.setEstado(estado);
            bici.setUsuarioPropietario(nombreUsuario);
            UUID idUno = UUID.randomUUID();
            bici.setCodigoActivacion(idUno.toString());
            bici.setImagen(foto);
            BicicletaDB.insertarBicicleta(bici);

            PeticionRevision peticion = new PeticionRevision();
            peticion.setCiudad(Ciudad);
            peticion.setFecha(LocalDate.parse(fecha1));
            peticion.setHora(LocalDateTime.parse(hora1));
            peticion.setCodigoUsuario(nombreUsuario);

            ArrayList<Bicicleta> lista = new ArrayList<Bicicleta>();
            lista = BicicletaDB.getBicicletasEstado(nombreUsuario, estado);
            for (int i = 0; i < lista.size(); i++) {
                Bicicleta b1 = new Bicicleta();
                b1 = lista.get(i);
                if (b1.getCodigoActivacion().equals(idUno.toString())) {
                    peticion.setCodigoBicicleta(b1.getcodigoBici());
                }
            }
            PeticionRevisionDB.insertarPeticionRevision(peticion);
            
            String url = "/direccionRegistroCorrecto.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);

        } catch (Exception e) {
            
            String url = "/direccionRegistroIncorrecto.jsp";
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(registrarPeticionRevision.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(registrarPeticionRevision.class.getName()).log(Level.SEVERE, null, ex);
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
