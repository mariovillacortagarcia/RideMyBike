package ridemybike.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ridemybike.dominio.Usuario;
import ridemybike.dominio.db.UsuarioDB;
import ridemybike.security.PasswordEncoder;

/**
 * Servlet que gestiona el inicio de sesion
 */
@WebServlet(name = "IniciarSesion", urlPatterns = {"/IniciarSesion"})
public class IniciarSesion extends HttpServlet {

    private final String ERROR_USUARIO_INCORRECTO = "Este usuario no es válido.";
    private final String PASSWORD_INCORRECTA = "Contraseña incorrecta.";

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

        String usuarioIntroducido = request.getParameter("usuario");
        String passwordIntroducida = request.getParameter("password");
        String url;
        PasswordEncoder encoder = new PasswordEncoder();

        if (!UsuarioDB.existeUsuario(usuarioIntroducido)) {
            request.setAttribute("errorUsuario", ERROR_USUARIO_INCORRECTO);
            request.setAttribute("usuario", usuarioIntroducido);
            url = "/iniciar_sesion.jsp";
        } else {
            Usuario presuntoUser = UsuarioDB.selectUser(usuarioIntroducido);

            if (encoder.authenticate(passwordIntroducida.toCharArray(), presuntoUser.getHashPasswd())) {
                request.getSession().setAttribute("usuario", usuarioIntroducido);
                if(UsuarioDB.esAdministrador(usuarioIntroducido)){
                    request.getSession().setAttribute("admin", "El usuario de la sesion es un administrador del sistema.");
                }
                url = "/index.jsp";
            } else {
                request.setAttribute("errorPassword", PASSWORD_INCORRECTA);
                request.setAttribute("usuario", usuarioIntroducido);
                request.setAttribute("passwordIncorrecta", passwordIntroducida);
                url = "/iniciar_sesion.jsp";
            }
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

}
