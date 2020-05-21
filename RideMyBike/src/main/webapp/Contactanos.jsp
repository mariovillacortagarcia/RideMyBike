<%-- 
    Document   : Contactanos
    Created on : 21 may. 2020, 16:28:09
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <title>Contactanos - RideMyBike</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

        <link rel="icon" type="image/png" href="img/RideMyBike_icon_green.png">
        <link rel="stylesheet" href="css/style.css">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.css">
    </head>
    <body>

        <!-- Cabecera -->
        <% String sesion = session.getAttribute("usuario") == null ? "false" : "true";%>
        <jsp:include page="header.jsp" >
            <jsp:param name="paginaMostrada" value="RecuperarPassword" />
            <jsp:param name="sesionIniciada" value="<%= sesion%>" />
        </jsp:include>

        <!-- Contenido -->
        <div class="container d-flex justify-content-center">

            <div class="col-md-8 order-md-1">
                <div class="pt-5 text-center">
                    <h3><b>Contáctanos</b></h3>
                    <p class="mb-1">Rellena los campos y dinos lo que quieras</p>
                </div>
                <hr class="mb-4">

                <!--Formulario de Registro de un nuevo usuario-->
                <form action="EnviarEmail" method="POST" class="form-group mb-2" enctype="multipart/form-data">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="nombre">Introduce tu Nombre</label>
                            <input type="String" class="form-control" id="firstName" name="nombre" required="text" maxlength="30" onpaste="return false;">

                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="email">Introduce tu Email</label>
                            <input type="String" class="form-control" id="lastName" name="email" required="text" maxlength="30" onpaste="return false;">

                        </div>

                    </div>
                    <div class="mb-3">
                        <label for="asunto">Asunto del mensaje</label>
                        <input type="String" class="form-control" id="telefono" name="asunto" required="text" maxlength="20" onpaste="return false;">

                    </div>

                    <label for="contenido">Mensaje</label>
                    <textarea type="String" class="form-control" id="descripcionValoracion" name="mensaje" rows="4" maxlength="350" required="text" onpaste="return false;"></textarea>
                    <hr class="mb-4">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Enviar</button>
                </form>
            </div>
        </div>
        <!-- Footer -->
        <jsp:include page="footer.jsp" >
            <jsp:param name="etiqueta" value="RideMyBike" />
            <jsp:param name="mostrarBoton" value="false" />
        </jsp:include>

    </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="js/bootstrap.js"></script>
</body>
</html>
