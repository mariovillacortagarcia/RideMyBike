/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridemybike.servlets;


import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;
import ridemybike.dominio.Usuario;
import ridemybike.dominio.db.UsuarioDB;
import ridemybike.security.PasswordEncoder;
/**
 *
 * @author David
 */
/**
 * Implementacion del servlet para la actualizacion de los datos del perfil dado
 */
@WebServlet(name = "RegistrarUsuario", urlPatterns = {"/RegistrarUsuario"})
@MultipartConfig
public class RegistrarUsuario extends HttpServlet {
    private boolean cadenaNumerica(String tlf) {
        try {
            Long.parseLong(tlf);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
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
        String usuario = request.getParameter("usuario");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String dni = request.getParameter("dni");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String tarjeta = request.getParameter("tarjeta");
        String password = request.getParameter("password");
        String passwordrepe = request.getParameter("passwordrepe"); 
       
        boolean todoCorrecto = true;
        if (usuario.isBlank()) {
            request.setAttribute("errorUsuario", "Este usuario no es válido.");
            todoCorrecto = false;
        }
        if (nombre.isBlank()) {
            request.setAttribute("errorNombre", "Este nombre no es válido.");
            todoCorrecto = false;
        }
        if (apellidos.isBlank()) {
            request.setAttribute("errorApellidos", "Estos apellidos no son válidos.");
            todoCorrecto = false;
        }
        if (direccion.isBlank()) {
            request.setAttribute("errorDireccion", "Esta direccion no es válida.");
            todoCorrecto = false;
        }
        if (dni.isBlank()|| dni.length() < 9)  {
            request.setAttribute("errorDNI", "Este dni no es válida.");
            todoCorrecto = false;
        }
        if (!email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
            request.setAttribute("errorEmail", "Este e-mail no es válido.");
            todoCorrecto = false;
        }
        if(UsuarioDB.existeEmail(email) && !UsuarioDB.selectUserByEmail(email).getNickName().equals(usuario)){
            request.setAttribute("errorEmail", "Este e-mail ya está en uso.");
            todoCorrecto = false;
        }
        if (!cadenaNumerica(telefono)) {
            request.setAttribute("errorTlf", "Este teléfono no es válido.");
            todoCorrecto = false;
        }
        if (!cadenaNumerica(tarjeta)) {
            request.setAttribute("errorTarjeta", "Este número de tarjeta no es válido.");
            todoCorrecto = false;
        }    
        PasswordEncoder enc = new PasswordEncoder();
        if (!password.isEmpty()) {
            if (!password.equals(passwordrepe)) {
                request.setAttribute("errorPassword", "Las contraseñas no coinciden");
                todoCorrecto = false;
            } else {
                if (password.isBlank() || password.contains(" ")) {
                    request.setAttribute("errorPasswordEnBlanco", "La contraseña no puede contener espacios en blanco");
                    todoCorrecto = false;
                } else {
                    if (password.length() < 8) {
                        request.setAttribute("errorPasswordNoValida", "La contraseña debe contener almenos 8 caracteres válidos");
                        todoCorrecto = false;
                    }
                }
            }
        } 
            
            
            Usuario user = new Usuario();
            user.setNombreUsuario(usuario);
            user.setNombre(nombre);
            user.setApellidos(apellidos);
            user.setDni(dni);
            user.setEmail(email);
            user.setTlf(telefono);
            user.setDireccion(direccion);
            user.setTarjetaCredito(tarjeta);
            
            user.setHashPasswd(enc.hash(password.toCharArray()));
           
           
          
    
        String url;
        if (todoCorrecto) {
            UsuarioDB.insertarUsuario(user);
            url = "/direccionRegistroCorrecto.jsp";
        } else {
            request.setAttribute("usuarioErroneo", user);
            url = "/direccionRegistroIncorrecto.jsp";
        }
        
        
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
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
