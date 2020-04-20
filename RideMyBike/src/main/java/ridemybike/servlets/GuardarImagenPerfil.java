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
 * Implementacion del servlet para la actualizacion de la imagen de perfil
 */
@WebServlet(name = "GuardarImagenPerfil", urlPatterns = {"/GuardarImagenPerfil"})
@MultipartConfig
public class GuardarImagenPerfil extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Usuario user = new Usuario();
        Part foto = request.getPart("fotoElegida");
        String usuario = request.getParameter("usuario");

        user.setFotoPerfil(foto);
        user.setNombreUsuario(usuario);
        
        UsuarioDB.setImagen(user);
        
        String url = "/perfil.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}