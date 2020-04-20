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
 * Implementacion del servlet para la actualizacion de los datos del perfil dado
 */
@WebServlet(name = "ActualizarPerfil", urlPatterns = {"/ActualizarPerfil"})
@MultipartConfig
public class ActualizarPerfil extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Usuario user = new Usuario();
        Part foto = request.getPart("fotoElegida");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String tarjeta = request.getParameter("tarjeta");
        String passwordAntigua = request.getParameter("passwordAntigua");
        String passwordActual = request.getParameter("passwordActual");
        String passwordNueva = request.getParameter("passwordNueva");
        String passwordNuevaConfirmacion = request.getParameter("passwordNuevaConfirmacion");
        
        user.setFotoPerfil(foto);
        user.setNombre(nombre);
        user.setApellidos(apellidos);
        user.setEmail(email); //si queremos validar el email, hacerlo antes de esta linea
        user.setTlf(Long.parseLong(telefono)); //si queremos validar el tlf, hacerlo antes de esta linea 
                                               //(imprescindible validar almenos que es numerico porque cascaria ParseLong)
        user.setDireccion(direccion); 
        user.setTarjetaCredito(tarjeta); //si queremos validar el numero de tarjeta, hacerlo antes de esta linea
        user.setHashPasswd(passwordNueva); //Antes de hacer esta linea (que ademas habria que meter el hash de passwordNueva, 
                                           // y no passwordNueva en plano, comprobar que passwordAntigua coincide con passwordActual y
                                           // y que passwordNueva coincide con passwordNuevaConfirmacion
        
        UsuarioDB.actualizarUsuario(user);
        
        String url = "/perfil.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}