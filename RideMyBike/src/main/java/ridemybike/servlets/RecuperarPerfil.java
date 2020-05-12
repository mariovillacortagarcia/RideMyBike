package ridemybike.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ridemybike.dominio.Usuario;
import ridemybike.dominio.db.UsuarioDB;
import ridemybike.dominio.db.ValoracionUsuarioDB;

/**
 * Implementacion del servlet para recuperar el objeto usuario de modelo 
 * con los datos del usuario de la sesion
 */
@WebServlet(name = "RecuperarPerfil", urlPatterns = {"/RecuperarPerfil"})
@MultipartConfig
public class RecuperarPerfil extends HttpServlet {
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session= request.getSession();
        String nombreUsuario = session.getAttribute("usuario").toString();
        
        Usuario usuario = UsuarioDB.selectUser(nombreUsuario);
        request.setAttribute("user", usuario);
        
        int valoracionMedia = ValoracionUsuarioDB.selectValoracionMedia(nombreUsuario);
        request.setAttribute("valoracionMedia", valoracionMedia);
        
        String url = "/perfil.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
