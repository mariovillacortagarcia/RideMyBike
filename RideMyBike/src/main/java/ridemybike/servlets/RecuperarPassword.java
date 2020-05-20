package ridemybike.servlets;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.Random;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ridemybike.dominio.Usuario;
import ridemybike.dominio.db.UsuarioDB;
import ridemybike.security.PasswordEncoder;

/**
 * Implementacion de un servlet para enviar un email de recuperacion de
 * contraseña al email especificado en la peticion
 */
@WebServlet(name = "RecuperarPassword", urlPatterns = {"/RecuperarPassword"})
@MultipartConfig
public class RecuperarPassword extends HttpServlet {

    private final String PATRON_EMAIL_CORRECTO = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    private final String ERROR_EMAIL_NO_REGISTRADO = "No existe ninguna cuenta vinculada a este email.";
    private final String ERROR_EMAIL_NO_VALIDO = "Este e-mail no es válido.";
    private final String MENSAJE_EXITO = "E-mail de recuperación enviado correctamente.";
    private final String EMAIL_RIDE_MY_BIKE = "webdelasbicis@gmail.com";

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        boolean todoCorrecto = true;

        String emailRecuperacion = request.getParameter("emailRecuperacion");
        if (!UsuarioDB.existeEmail(emailRecuperacion)) {
            request.setAttribute("errorEmail", ERROR_EMAIL_NO_REGISTRADO);
            request.setAttribute("emailIncorrecto", emailRecuperacion);
            todoCorrecto = false;
        }

        if (!emailRecuperacion.matches(PATRON_EMAIL_CORRECTO)) {
            request.setAttribute("errorEmail", ERROR_EMAIL_NO_VALIDO);
            request.setAttribute("emailIncorrecto", emailRecuperacion);
            todoCorrecto = false;
        }

        if (todoCorrecto) {
            String passwordRecuperacion = generateRandomPassword();
            PasswordEncoder encoder = new PasswordEncoder();
            Usuario user = UsuarioDB.selectUserByEmail(emailRecuperacion);
            user.setHashPasswd(encoder.hash(passwordRecuperacion.toCharArray()));
            UsuarioDB.actualizarUsuario(user);

            sendEmailRecuperacion(emailRecuperacion, user.getNickName(), passwordRecuperacion);
            request.setAttribute("mensajeExito", MENSAJE_EXITO);
        }

        String url = "/recuperar_password.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    private String generateRandomPassword() {
        String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        String generatedString = "";
        int index;
        for (int i = 0; i < 8; i++) {
            index = (int) (alphaNumericString.length() * Math.random());
            generatedString += alphaNumericString.charAt(index);
        }
        return generatedString;
    }

    private void sendEmailRecuperacion(String email, String userName, String passwordRecuperacion) {
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.port", 465);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        Message message = new MimeMessage(session);
        try {
            message.setSubject("Recuperación de contraseña");
            String cuerpoMensaje = "Hola " + userName + ", \nhemos recibido una petición de reestablecimiento de contraseña."
                    + "\nEsta es tu nueva contraseña: " + passwordRecuperacion + "\n\nSólo tú has recibido este email, de modo que si no has solicitado el "
                    + "reestablecimiento de contraseña, la seguridad e integridad de tu cuenta no se han visto comprometidas. Siéntete libre de "
                    + "cambiar esta contraseña según tu gusto. \n\nUn saludo, \ntu equipo de RideMyBike!";
            message.setText(cuerpoMensaje);
            Address fromAddress = new InternetAddress(EMAIL_RIDE_MY_BIKE);
            message.setFrom(fromAddress);
            Address toAddress = new InternetAddress(email);
            message.setRecipient(Message.RecipientType.TO, toAddress);
            Transport transport = session.getTransport();
            transport.connect(EMAIL_RIDE_MY_BIKE, "donpatricio");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
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
