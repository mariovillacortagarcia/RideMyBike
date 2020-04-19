package ridemybike.servlets;

import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ridemybike.dominio.db.UsuarioDB;

/**
 * Implementacion del servlet para recuperar la foto de perfil de un usuario
 * a partir de su nombre de usuario
 * 
 * @author davidmd
 */
@WebServlet(name = "RecuperarImagenPerfil", urlPatterns = {"/RecuperarImagenPerfil"})
public class RecuperarImagenPerfil extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("image/jpg");
        OutputStream respuesta = response.getOutputStream();
        String nombreUsuario = request.getParameter("nombreUsuario");
        UsuarioDB.getImagen(nombreUsuario, respuesta);
        respuesta.close();
        response.flushBuffer();
    }
}