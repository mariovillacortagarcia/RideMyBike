<%@page import="ridemybike.dominio.Usuario"%>
<%@page import="ridemybike.dominio.Incidencia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">

    <head>
        <title> Valorar usuario - RideMyBike </title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="icon" type="image/png" href="img/RideMyBike_icon_green.png">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.css">
    </head>

    <body>
        <!---Cabecera -->
        <jsp:include page="header.jsp" >
            <jsp:param name="paginaMostrada" value="Viajes" />
            <jsp:param name="sesionIniciada" value="true" />
        </jsp:include>

        <!-- Contenido -->
        <%
            Usuario usuarioPropietario = (Usuario) request.getAttribute("usuarioPropietario");
            Usuario usuarioArrendatario = (Usuario) request.getAttribute("usuarioArrendatario");
            String nombrePropietario = usuarioPropietario.getNombre();
            String apellidosPropietario = usuarioPropietario.getApellidos();
        %>
        <div class="container pt-4 mt-1">
            <!--Formulario de Registro de una valoracion de usuario-->
            <div class="alert alert-secondary bg-white row" role="alert">
                <img src="RecuperarImagenPerfil?usuario=<%= usuarioPropietario.getNickName()%>"  alt="Propietario de la bici" class="img-thumbnail col-3">
                <div class="col-9">
                    <h3>¡Puntúa a <%=nombrePropietario%> <%=apellidosPropietario%>!</h3>
                    <form action="ValorarUsuario" method="POST">
                        
                        <button type="submit" class="btn btn-success">Enviar valoración</button>
                    </form>
                </div>

            </div>


        </div>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="js/bootstrap.js"></script>
    </body>

</html>
