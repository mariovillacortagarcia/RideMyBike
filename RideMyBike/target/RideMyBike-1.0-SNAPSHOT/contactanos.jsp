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
        <jsp:include page="header.jsp" >
            <jsp:param name="paginaMostrada" value="" />
            <jsp:param name="sesionIniciada" value="true" />
        </jsp:include>

        <!-- Contenido -->
        <div class="container d-flex justify-content-center">

            <div class="col-md-8 order-md-1">
                <div class="pt-5 text-center">
                    <h3><b>Contáctanos</b></h3>
                    <p class="mb-1">Si tienes algún problema, duda o sugerencia no dudes en contactarnos</p>
                </div>
                <hr class="mb-3">

                <!--Formulario de Registro de un nuevo usuario-->
                <form action="RegistrarProblemaSugerencia" method="POST" class="form-group mb-2" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="asunto">Asunto del mensaje</label>
                        <input type="String" class="form-control" id="telefono" name="asunto" required="text" maxlength="50" onpaste="return false;">
                        <% if (request.getAttribute("errorAsunto") != null) {%>
                        <small style="color:red"><%=request.getAttribute("errorAsunto")%></small>
                        <% }%>
                    </div>

                    <label for="contenido">Mensaje</label>
                    <textarea type="String" class="form-control" id="descripcionValoracion" name="mensaje" rows="4" maxlength="2000" required="text" onpaste="return false;"></textarea>
                    <% if (request.getAttribute("errorMensaje") != null) {%>
                    <small style="color:red"><%=request.getAttribute("errorMensaje")%></small>
                    <% }%>
                    <hr class="my-3">

                    <div class="form-group mb-5">
                        <label for="categoriaMensaje">Categoría del mensaje</label>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" value="duda" name="categoria" id="duda" required>
                            <label class="form-check-label" for="duda">
                                <b>Duda o sugerencia</b>: mi mensaje es una pregunta o tiene carácter informativo.
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" value="problema" name="categoria" id="problema" required>
                            <label class="form-check-label" for="problema">
                                <b>Problema</b>: mi mensaje está reportando un problema o error.
                            </label>
                        </div>
                    </div>

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
    <script src="https://code.jquery.com/jquery-3.5.0.js" integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="js/bootstrap.js"></script>
</body>
</html>
