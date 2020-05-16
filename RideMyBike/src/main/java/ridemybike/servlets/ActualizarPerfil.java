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
//import org.apache.commons.validator.routines.EmailValidator;
import ridemybike.dominio.Usuario;
import ridemybike.dominio.db.UsuarioDB;

/**
 * Implementacion del servlet para la actualizacion de los datos del perfil dado
 */
@WebServlet(name = "ActualizarPerfil", urlPatterns = {"/ActualizarPerfil"})
@MultipartConfig
public class ActualizarPerfil extends HttpServlet {

    private boolean nombreValido(String nombre) {
        return nombre.replace(" ", "").length() == 0 ? false : true;
    }

    private boolean cadenaNumerica(String tlf) {
        try {
            Long.parseLong(tlf);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
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

        if (nombre.isBlank() || apellidos.isBlank() || direccion.isBlank()) {
            String url = "/RecuperarPerfil";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else {
            if (!email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
                String url = "/RecuperarPerfil";
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
            } else {
                if (!cadenaNumerica(telefono) || !cadenaNumerica(tarjeta)) {
                    String url = "/RecuperarPerfil";
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
                    dispatcher.forward(request, response);
                } else {
                    if ((!passwordNueva.isBlank() && !passwordAntigua.equals(passwordActual)) || (!passwordNueva.isBlank() && !passwordNueva.equals(passwordNuevaConfirmacion))) {
                        String url = "/RecuperarPerfil";
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
                        dispatcher.forward(request, response);
                    } else {
                        Usuario user = new Usuario();
                        user.setNombreUsuario(usuario);
                        user.setNombre(nombre);
                        user.setApellidos(apellidos);
                        user.setEmail(email);
                        user.setTlf(Long.parseLong(telefono));
                        user.setDireccion(direccion);
                        user.setTarjetaCredito(tarjeta);
                        user.setHashPasswd(passwordNueva.isBlank() ? passwordAntigua : passwordNueva);

                        UsuarioDB.actualizarUsuario(user);

                        Part foto = request.getPart("fotoElegida");
                        if (foto.getSize() != 0) {
                            user.setFotoPerfil(foto);

                            UsuarioDB.setImagen(user);
                        }

                        String url = "/RecuperarPerfil";
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
                        dispatcher.forward(request, response);
                    }
                }
            }
        }
    }
}
