package ridemybike.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import ridemybike.dominio.Usuario;
import ridemybike.dominio.db.UsuarioDB;

/**
 * Implementacion del servlet para la actualizacion de la foto de perfil
 * 
 * @author davidmd
 */
@WebServlet(name = "GuardarImagenPerfil", urlPatterns = {"/GuardarImagenPerfil"})
@MultipartConfig
public class GuardarImagenPerfil extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Part foto = request.getPart("fotoElegida");
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        user.setFotoPerfil(foto);
        UsuarioDB.actualizarUsuario(user);
        
        String url = "/result.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}