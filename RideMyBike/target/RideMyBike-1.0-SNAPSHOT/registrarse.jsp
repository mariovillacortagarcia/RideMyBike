<%@page import="ridemybike.dominio.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <title> Registrarse - RideMyBike </title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="icon" type="image/png" href="img/RideMyBike_icon_green.png">
        <link rel="stylesheet" href="css/style.css">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.css">
    </head>

    <body>

        <!-- Cabecera -->

        <jsp:include page="header.jsp" >
            <jsp:param name="paginaMostrada" value="Registrarse" />
            <jsp:param name="sesionIniciada" value="" />
        </jsp:include>

        <!-- Contenido -->
        <%
            Usuario usuarioErroneo = (Usuario) request.getAttribute("usuarioErroneo");
            boolean existeUsuarioErroneo = usuarioErroneo != null;
        %>
        <div class="container d-flex justify-content-center">

            <div class="col-md-8 order-md-1">
                <div class="pt-5 text-center">
                    <h3><b>Regístrate</b></h3>
                    <p class="mb-1">Necesitamos algunos datos para completar tu registro</p>
                </div>
                <hr class="mb-4">
                <h4 class="mb-3">Datos personales</h4>
                <!--Formulario de Registro de un nuevo usuario-->
                <form action="RegistrarUsuario" method="POST" class="form-group mb-2" enctype="multipart/form-data">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="firstName">Nombre</label>
                            <input type="String" class="form-control" id="firstName" name="nombre" required="text" maxlength="30" onpaste="return false;" <% if (existeUsuarioErroneo) {%> value="<%= usuarioErroneo.getNombre()%>" <% } %> >
                            <% if (request.getAttribute("errorNombre") != null) {%>
                            <small style="color:red"><%=request.getAttribute("errorNombre")%></small>
                            <% }%>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="lastName">Apellidos</label>
                            <input type="String" class="form-control" id="lastName" name="apellidos" required="text" maxlength="30" onpaste="return false;" <% if (existeUsuarioErroneo) {%> value="<%= usuarioErroneo.getApellidos()%>" <% } %> >
                            <% if (request.getAttribute("errorApellidos") != null) {%>
                            <small style="color:red"><%=request.getAttribute("errorApellidos")%></small>
                            <% }%>
                        </div>

                    </div>

                    <div class="mb-3">
                        <label for="username">Nombre de usuario</label>
                        <div class="input-group">
                            <div class="form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">@</span>
                                    <input type="String" class="form-control" id="userName" name="usuario" placeholder="Usuario" required="text" maxlength="20" onpaste="return false;" <% if (existeUsuarioErroneo) {%> value="<%= usuarioErroneo.getNickName()%>" <% } %> >
                                </div>
                                <% if (request.getAttribute("errorUsuario") != null) {%>
                                <small style="color:red"><%=request.getAttribute("errorUsuario")%></small>
                                <% }%>
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="dni">DNI, NIF, CIF y/o NIE</label>
                        <input type="String" class="form-control" id="dni" name="dni" required="text" maxlength="9" onpaste="return false;" <% if (existeUsuarioErroneo) {%> value="<%= usuarioErroneo.getDNI()%>" <% } %> >
                        <% if (request.getAttribute("errorDNI") != null) {%>
                        <small style="color:red"><%=request.getAttribute("errorDNI")%></small>
                        <% }%>
                    </div>

                    <div class="mb-3">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="usuario@example.com" required="text" maxlength="20" onpaste="return false;" <% if (existeUsuarioErroneo) {%> value="<%= usuarioErroneo.getEmail()%>" <% } %> >
                        <% if (request.getAttribute("errorEmail") != null) {%>
                        <small style="color:red"><%=request.getAttribute("errorEmail")%></small>
                        <% }%>
                    </div>

                    <div class="mb-3">
                        <label for="movil">Teléfono movil o fijo</label>
                        <input type="tel" class="form-control" id="telefono" name="telefono" required="number" maxlength="20" onpaste="return false;" <% if (existeUsuarioErroneo) {%> value="<%= usuarioErroneo.getTlf()%>" <% } %> >
                        <% if (request.getAttribute("errorTlf") != null) {%>
                        <small style="color:red"><%=request.getAttribute("errorTlf")%></small>
                        <% }%>
                    </div>

                    <div class="mb-3">
                        <label for="address">Dirección de domicilio</label>
                        <input type="String" class="form-control" id="address" name="direccion" required="text" maxlength="80" onpaste="return false;" <% if (existeUsuarioErroneo) {%> value="<%= usuarioErroneo.getDireccion()%>" <% } %> >
                        <% if (request.getAttribute("errorDireccion") != null) {%>
                        <small style="color:red"><%=request.getAttribute("errorDireccion")%></small>
                        <% }%>
                    </div>

                    <div class="mb-3">
                        <label for="address2">Contraseña</label>
                        <input type="password" class="form-control" id="address2" name="password" required minleght="8" maxlength="128" onpaste="return false;">
                        <% if (request.getAttribute("errorPasswordNoValida") != null) {%>
                        <small style="color:red"><%=request.getAttribute("errorPasswordNoValida")%></small>
                        <% }%>
                    </div>

                    <div class="mb-3">
                        <label for="address2">Repetir contraseña</label>
                        <input type="password" class="form-control" id="address2" name="passwordrepe" required minleght="8" maxlength="128" onpaste="return false;">
                        <% if (request.getAttribute("errorPasswordsNoCoinciden") != null) {%>
                        <small style="color:red"><%=request.getAttribute("errorPasswordsNoCoinciden")%></small>
                        <% }%>
                    </div>

                    <div class="row">
                        <div class="col-md-5 mb-3">
                            <label for="country">País</label>
                            <select class="custom-select d-block w-100" id="country" name="pais" required="text">
                                <option selected disabled hidden>Elige un país...</option>
                                <option>Alemania</option>
                                <option>Andorra</option>
                                <option>Argentina</option>
                                <option>Brasil</option>
                                <option>Botswana</option>
                                <option>China</option>
                                <option>Costa Rica</option>
                                <option>España</option>
                                <option>Estados Unidos</option>
                                <option>Italia</option>
                                <option>Francia</option>
                                <option>Japón</option>
                                <option>Venezuela</option>
                            </select>
                            <div class="invalid-feedback">
                                Selecciona un país válido.
                            </div>
                        </div>

                    </div>
                    <hr class="mb-4">

                    <h4 class="mb-3">Forma de pago</h4>

                    <div class="d-block my-3">
                        <div class="custom-control custom-radio">
                            <input id="credit" name="paymentMethod" type="radio" class="custom-control-input" checked required onpaste="return false;">
                            <label class="custom-control-label" for="credit">Tarjeta de crédito</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="cc-number">Número de tarjeta</label>
                            <input type="String" class="form-control" id="cc-number" name="tarjeta" required="number" maxlength="20" onpaste="return false;" <% if (existeUsuarioErroneo) {%> value="<%= usuarioErroneo.getTarjetaCredito()%>" <% } %> >
                            <% if (request.getAttribute("errorTarjeta") != null) {%>
                            <small style="color:red"><%=request.getAttribute("errorTarjeta")%></small>
                            <% }%>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 mb-3">
                            <label for="cc-expiration">Expiración</label>
                            <input type="date" class="form-control" id="cc-expiration" name="expiracion" required>
                            <div class="invalid-feedback">
                                Es necesaria la fecha de expiración
                            </div>
                        </div>
                        <div class="col-md-3 mb-3">
                            <label for="cc-cvv">CVV</label>
                            <input type="String" class="form-control" id="cc-cvv" name="cvv" required="number" maxlength="10" onpaste="return false;" required <% if (existeUsuarioErroneo) {%> value="<%= request.getAttribute("codigoSeguridad")%>" <% } %> >
                            <% if (request.getAttribute("errorCodigoSeguridad") != null) {%>
                            <small style="color:red"><%=request.getAttribute("errorCodigoSeguridad")%></small>
                            <% }%>
                        </div>
                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" class="custom-control-input" id="same-address" name="checkbox" required>
                            <label class="custom-control-label" for="same-address">He leído y acepto las <a href="garantias.jsp">Condiciones de Uso y la Política de Privacidad y Cookies</a> de RideMyBike</label>
                        </div>
                    </div>
                    <hr class="mb-4">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Registrarse</button>
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

