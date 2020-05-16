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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            .checked {
                color: orange;
            }
        </style>
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
            String nombrePropietario = usuarioPropietario.getNombre();
            String apellidosPropietario = usuarioPropietario.getApellidos();
            String codigoAlquiler = request.getParameter("codigoAlquiler");
            String nickPropietario = usuarioPropietario.getNickName();
        %>
        <div class="container pt-4 mt-1">
            <!--Formulario de Registro de una valoracion de usuario-->
            <div class="alert alert-secondary bg-white row" role="alert">
                <img src="RecuperarImagenPerfil?usuario=<%= usuarioPropietario.getNickName()%>"  alt="Propietario de la bici" class="img-thumbnail col-3">
                <div class="col-9">
                    <h3>¡Puntúa a <%=nombrePropietario%> <%=apellidosPropietario%>!</h3>
                    <div class="mb-2">
                        <span class="fa fa-star " id="uno"></span>
                        <span class="fa fa-star " id="dos"></span>
                        <span class="fa fa-star " id="tres"></span>
                        <span class="fa fa-star" id="cuatro"></span>
                        <span class="fa fa-star" id="cinco"></span>
                    </div>
                    <form action="ValorarUsuario" method="POST">
                        <label for="descripcionValoracion">Descripción</label>
                        <textarea type="String" class="form-control" id="descripcionValoracion" name="descripcion" rows="4"> </textarea>
                        
                        
                        <input name="usuarioValorado" type="text" value="<%=nickPropietario%>" style="display:none">
                        <input name="codigoAlquiler" type="number" value="<%=codigoAlquiler%>" style="display:none">
                        <input id="valoracion" name="valoracion" type="number" min="0" max="5" style="display:none">
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
        <script src="js/valoracionUsuario.js"></script>
    </body>

</html>
