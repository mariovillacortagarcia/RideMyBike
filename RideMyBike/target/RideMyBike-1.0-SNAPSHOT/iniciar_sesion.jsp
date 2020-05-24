<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <title> Iniciar sesión - RideMyBike </title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="icon" type="image/png" href="img/RideMyBike_icon_green.png">
        <link rel="stylesheet" href="css/style.css">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.css">
        <script type="text/javascript">
            function mostrarPassword() {
                var cambio = document.getElementById("password");
                if (cambio.type === "password") {
                    cambio.type = "text";

                } else {
                    cambio.type = "password";

                }
            }

            $(document).ready(function () {
                //mostrar contraseña
                $('#ShowPassword').click(function () {
                    $('#Password').attr('type', $(this).is(':checked') ? 'text' : 'password');
                });
            });
        </script>
    </head>

    <body>
        <!-- Cabecera -->
        <jsp:include page="header.jsp" >
            <jsp:param name="paginaMostrada" value="IniciarSesion" />
            <jsp:param name="sesionIniciada" value="false" />
        </jsp:include>

        <!-- Contenido -->
        <%
            boolean usuarioMostrable = request.getAttribute("usuario") != null;
            boolean passwordIncorrecta = request.getAttribute("errorPassword") != null;
        %>
        <div class="row justify-content-center my-auto pt-5">
            <form class="form-signin" action="IniciarSesion" method="post">
                <div class="text-center mb-4">

                    <h1 class="h3 mb-3 font-weight-normal"><b>Iniciar sesión</b></h1>
                </div>

                <div class="form-label-group pb-2">
                    <label for="usuario">Usuario o email</label>
                    <input type="text" name="usuario" class="form-control" placeholder="Usuario o email" <% if (usuarioMostrable) {%>value="<%= request.getAttribute("usuario")%>" <% } %>required autofocus>
                    <% if (request.getAttribute("errorUsuario") != null) {%>
                    <small style="color:red"><%=request.getAttribute("errorUsuario")%></small>
                    <% }%>
                </div>

                <div class="form-label-group pb-2">

                    <label>Contraseña</label>
                    <div class="input-group">
                        <input ID="password" type="password" name="password" Class="form-control" placeholder="Contraseña" <% if (passwordIncorrecta) {%>value="<%= request.getAttribute("passwordIncorrecta")%>" <% } %> required>
                        <div class="input-group-append">
                            <button id="show_password" class="btn btn-primary" type="button" onclick="mostrarPassword()"> <svg class="bi bi-eye-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path d="M10.5 8a2.5 2.5 0 1 1-5 0 2.5 2.5 0 0 1 5 0z"/>
                                <path fill-rule="evenodd" d="M0 8s3-5.5 8-5.5S16 8 16 8s-3 5.5-8 5.5S0 8 0 8zm8 3.5a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7z"/>
                                </svg></button>
                        </div>
                    </div>
                    <% if (request.getAttribute("errorPassword") != null) {%>
                    <small style="color:red"><%=request.getAttribute("errorPassword")%></small>
                    <% }%>

                    <div class="checkbox mb-3 mt-1">
                        <label>
                            <input type="checkbox" value="remember-me"> Recuérdame
                        </label>
                        <p>Si todavía no tienes una cuenta puedes <a href="registrarse.jsp">registrarte aquí.</a></p>
                    </div>
                    <p><a href="recuperar_password.jsp">He olvidado mi contraseña.</a></p>
                    <button class="btn btn-lg btn-primary btn-block mt-1" type="submit">Iniciar sesión</button>
            </form>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="footer.jsp" >
        <jsp:param name="etiqueta" value="RideMyBike" />
        <jsp:param name="mostrarBoton" value="false" />
    </jsp:include>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="js/bootstrap.js"></script>
</body>

</html>

