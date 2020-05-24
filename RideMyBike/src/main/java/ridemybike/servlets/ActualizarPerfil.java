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
import ridemybike.dominio.db.UtilitiesDB;
import ridemybike.dominio.db.ValoracionUsuarioDB;
import ridemybike.security.PasswordEncoder;

/**
 * Implementacion del servlet para la actualizacion de los datos del perfil dado
 */
@WebServlet(name = "ActualizarPerfil", urlPatterns = {"/ActualizarPerfil"})
@MultipartConfig
public class ActualizarPerfil extends HttpServlet {

    private final String PATRON_EMAIL_CORRECTO = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    private final String ERROR_NOMBRE = "Este nombre no es válido.";
    private final String ERROR_APELLIDOS = "Estos apellidos no son válidos.";
    private final String ERROR_EMAIL_NO_VALIDO = "Este e-mail no es válido.";
    private final String ERROR_EMAIL_YA_EN_USO = "Este e-mail ya está en uso.";
    private final String ERROR_DIRECCION = "Esta direccion no es válida.";
    private final String ERROR_TLF = "Este teléfono no es válido.";
    private final String ERROR_TARJETA = "Este número de tarjeta no es válido.";
    private final String ERROR_PASSWORD_ACTUAL = "La contraseña actual no coincide con la introducida";
    private final String ERROR_PASSWORD_NUEVA = "La contraseña debe contener almenos 8 caracteres válidos";
    private final String ERROR_PASSWORD_NUEVA_BLANCO = "La contraseña no puede contener espacios en blanco";
    private final String ERROR_PASSWORD_CONFIRMADA = "Las nuevas contraseñas no coinciden";
    private final String MENSAJE_EXITO_CAMBIO_PASSWORD = "Contraseña cambiada con éxito.";

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
        String passwordActualReal = UsuarioDB.selectUser(usuario).getHashPasswd();
        String passwordActualIntroducida = request.getParameter("passwordActual");
        String passwordNueva = request.getParameter("passwordNueva");
        String passwordNuevaConfirmacion = request.getParameter("passwordNuevaConfirmacion");

        boolean todoCorrecto = true;
        if (nombre.isBlank() || UtilitiesDB.posibleInyeccionSQL(nombre)) {
            request.setAttribute("errorNombre", ERROR_NOMBRE);
            todoCorrecto = false;
        }
        if (apellidos.isBlank() || UtilitiesDB.posibleInyeccionSQL(apellidos)) {
            request.setAttribute("errorApellidos", ERROR_APELLIDOS);
            todoCorrecto = false;
        }
        if (direccion.isBlank() || UtilitiesDB.posibleInyeccionSQL(direccion)) {
            request.setAttribute("errorDireccion", ERROR_DIRECCION);
            todoCorrecto = false;
        }
        if (!email.matches(PATRON_EMAIL_CORRECTO)) {
            request.setAttribute("errorEmail", ERROR_EMAIL_NO_VALIDO);
            todoCorrecto = false;
        }
        if(UsuarioDB.existeEmail(email) && !UsuarioDB.selectUserByEmail(email).getNickName().equals(usuario)){
            request.setAttribute("errorEmail", ERROR_EMAIL_YA_EN_USO);
            todoCorrecto = false;
        }
        if (!cadenaNumerica(telefono)) {
            request.setAttribute("errorTlf", ERROR_TLF);
            todoCorrecto = false;
        }
        if (!cadenaNumerica(tarjeta)) {
            request.setAttribute("errorTarjeta", ERROR_TARJETA);
            todoCorrecto = false;
        }
        PasswordEncoder enc = new PasswordEncoder();
        if (!passwordNueva.isEmpty()) {
            if (!enc.authenticate(passwordActualIntroducida.toCharArray(), passwordActualReal)) {
                request.setAttribute("errorPasswordActual", ERROR_PASSWORD_ACTUAL);
                todoCorrecto = false;
            } else {
                if (passwordNueva.isBlank() || passwordNueva.contains(" ")) {
                    request.setAttribute("errorPasswordNueva", ERROR_PASSWORD_NUEVA_BLANCO);
                    todoCorrecto = false;
                } else {
                    if (passwordNueva.length() < 8) {
                        request.setAttribute("errorPasswordNueva", ERROR_PASSWORD_NUEVA);
                        todoCorrecto = false;
                    } else {
                        if (!passwordNueva.equals(passwordNuevaConfirmacion)) {
                            request.setAttribute("errorPasswordConfirmada", ERROR_PASSWORD_CONFIRMADA);
                            todoCorrecto = false;
                        } else{
                            request.setAttribute("passwordCambiadaConExito", MENSAJE_EXITO_CAMBIO_PASSWORD);
                        }
                    }
                }
            }
        }

        Usuario user = new Usuario();
        user.setNombreUsuario(usuario);
        user.setNombre(nombre);
        user.setApellidos(apellidos);
        user.setEmail(email);
        user.setTlf(telefono);
        user.setDireccion(direccion);
        user.setTarjetaCredito(tarjeta);
        user.setHashPasswd(passwordNueva.isEmpty() ? passwordActualReal : enc.hash(passwordNueva.toCharArray()));
        
        int valoracionMedia = ValoracionUsuarioDB.selectValoracionMedia(usuario);
        user.setValoracionMedia(valoracionMedia);

        String url;
        if(todoCorrecto){
            UsuarioDB.actualizarUsuario(user);
            url = "/RecuperarPerfil";
        } else{
            request.setAttribute("usuarioErroneo", user);
            url = "/perfil.jsp";
        }
        
        Part foto = request.getPart("fotoElegida");
        if (foto.getSize() != 0) {
            user.setFotoPerfil(foto);

            UsuarioDB.setImagen(user);
        }
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
