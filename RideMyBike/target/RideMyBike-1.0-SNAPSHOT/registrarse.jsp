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

        <script type="text/javascript">
            function mostrarPassword() {
                var cambio = document.getElementById("password");
                if (cambio.type === "password") {
                    cambio.type = "text";

                } else {
                    cambio.type = "password";

                }
            }

            function mostrarPasswordRepe() {
                var cambio = document.getElementById("passwordrepe");
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
                        <input type="email" class="form-control" id="email" name="email" placeholder="usuario@example.com" required="text" maxlength="100" onpaste="return false;" <% if (existeUsuarioErroneo) {%> value="<%= usuarioErroneo.getEmail()%>" <% } %> >
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

                    <label for="address2">Contraseña</label>
                    <div class="input-group">
                        <input type="password" class="form-control" id="password" name="password" required minleght="8" maxlength="128" onpaste="return false;">
                        <div class="input-group-append">
                            <button id="show_password" class="btn btn-primary" type="button" onclick="mostrarPassword()"> <svg class="bi bi-eye-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path d="M10.5 8a2.5 2.5 0 1 1-5 0 2.5 2.5 0 0 1 5 0z"/>
                                <path fill-rule="evenodd" d="M0 8s3-5.5 8-5.5S16 8 16 8s-3 5.5-8 5.5S0 8 0 8zm8 3.5a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7z"/>
                                </svg></button>
                        </div>                    
                    </div>
                    <% if (request.getAttribute("errorPasswordNoValida") != null) {%>
                    <small style="color:red"><%=request.getAttribute("errorPasswordNoValida")%></small>
                    <% }%>  


                    <div class="mt-3">
                        <label for="address2">Repetir contraseña</label>
                        <div class="input-group">

                            <input type="password" class="form-control" id="passwordrepe" name="passwordrepe" required minleght="8" maxlength="128" onpaste="return false;">
                            <div class="input-group-append">
                                <button id="show_password" class="btn btn-primary" type="button" onclick="mostrarPasswordRepe()"> <svg class="bi bi-eye-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M10.5 8a2.5 2.5 0 1 1-5 0 2.5 2.5 0 0 1 5 0z"/>
                                    <path fill-rule="evenodd" d="M0 8s3-5.5 8-5.5S16 8 16 8s-3 5.5-8 5.5S0 8 0 8zm8 3.5a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7z"/>
                                    </svg></button>
                            </div> 
                        </div>
                        <% if (request.getAttribute("errorPasswordsNoCoinciden") != null) {%>
                        <small style="color:red"><%=request.getAttribute("errorPasswordsNoCoinciden")%></small>
                        <% }%>
                    </div>

                    <div class="row">
                        <div class="col-md-5 my-3">
                            <label for="country">País</label>
                            <select class="custom-select d-block w-100" id="country" name="pais" required>
                                <option selected disabled hidden>Elige un país...</option>
                                <option value="AF">Afganistán</option>
                                <option value="AL">Albania</option>
                                <option value="DE">Alemania</option>
                                <option value="AD">Andorra</option>
                                <option value="AO">Angola</option>
                                <option value="AI">Anguilla</option>
                                <option value="AQ">Antártida</option>
                                <option value="AG">Antigua y Barbuda</option>
                                <option value="AN">Antillas Holandesas</option>
                                <option value="SA">Arabia Saudí</option>
                                <option value="DZ">Argelia</option>
                                <option value="AR">Argentina</option>
                                <option value="AM">Armenia</option>
                                <option value="AW">Aruba</option>
                                <option value="AU">Australia</option>
                                <option value="AT">Austria</option>
                                <option value="AZ">Azerbaiyán</option>
                                <option value="BS">Bahamas</option>
                                <option value="BH">Bahrein</option>
                                <option value="BD">Bangladesh</option>
                                <option value="BB">Barbados</option>
                                <option value="BE">Bélgica</option>
                                <option value="BZ">Belice</option>
                                <option value="BJ">Benin</option>
                                <option value="BM">Bermudas</option>
                                <option value="BY">Bielorrusia</option>
                                <option value="MM">Birmania</option>
                                <option value="BO">Bolivia</option>
                                <option value="BA">Bosnia y Herzegovina</option>
                                <option value="BW">Botswana</option>
                                <option value="BR">Brasil</option>
                                <option value="BN">Brunei</option>
                                <option value="BG">Bulgaria</option>
                                <option value="BF">Burkina Faso</option>
                                <option value="BI">Burundi</option>
                                <option value="BT">Bután</option>
                                <option value="CV">Cabo Verde</option>
                                <option value="KH">Camboya</option>
                                <option value="CM">Camerún</option>
                                <option value="CA">Canadá</option>
                                <option value="TD">Chad</option>
                                <option value="CL">Chile</option>
                                <option value="CN">China</option>
                                <option value="CY">Chipre</option>
                                <option value="VA">Ciudad del Vaticano (Santa Sede)</option>
                                <option value="CO">Colombia</option>
                                <option value="KM">Comores</option>
                                <option value="CG">Congo</option>
                                <option value="CD">Congo, República Democrática del</option>
                                <option value="KR">Corea</option>
                                <option value="KP">Corea del Norte</option>
                                <option value="CI">Costa de Marfíl</option>
                                <option value="CR">Costa Rica</option>
                                <option value="HR">Croacia (Hrvatska)</option>
                                <option value="CU">Cuba</option>
                                <option value="DK">Dinamarca</option>
                                <option value="DJ">Djibouti</option>
                                <option value="DM">Dominica</option>
                                <option value="EC">Ecuador</option>
                                <option value="EG">Egipto</option>
                                <option value="SV">El Salvador</option>
                                <option value="AE">Emiratos Árabes Unidos</option>
                                <option value="ER">Eritrea</option>
                                <option value="SI">Eslovenia</option>
                                <option value="ES">España</option>
                                <option value="US">Estados Unidos</option>
                                <option value="EE">Estonia</option>
                                <option value="ET">Etiopía</option>
                                <option value="FJ">Fiji</option>
                                <option value="PH">Filipinas</option>
                                <option value="FI">Finlandia</option>
                                <option value="FR">Francia</option>
                                <option value="GA">Gabón</option>
                                <option value="GM">Gambia</option>
                                <option value="GE">Georgia</option>
                                <option value="GH">Ghana</option>
                                <option value="GI">Gibraltar</option>
                                <option value="GD">Granada</option>
                                <option value="GR">Grecia</option>
                                <option value="GL">Groenlandia</option>
                                <option value="GP">Guadalupe</option>
                                <option value="GU">Guam</option>
                                <option value="GT">Guatemala</option>
                                <option value="GY">Guayana</option>
                                <option value="GF">Guayana Francesa</option>
                                <option value="GN">Guinea</option>
                                <option value="GQ">Guinea Ecuatorial</option>
                                <option value="GW">Guinea-Bissau</option>
                                <option value="HT">Haití</option>
                                <option value="HN">Honduras</option>
                                <option value="HU">Hungría</option>
                                <option value="IN">India</option>
                                <option value="ID">Indonesia</option>
                                <option value="IQ">Irak</option>
                                <option value="IR">Irán</option>
                                <option value="IE">Irlanda</option>
                                <option value="BV">Isla Bouvet</option>
                                <option value="CX">Isla de Christmas</option>
                                <option value="IS">Islandia</option>
                                <option value="KY">Islas Caimán</option>
                                <option value="CK">Islas Cook</option>
                                <option value="CC">Islas de Cocos o Keeling</option>
                                <option value="FO">Islas Faroe</option>
                                <option value="HM">Islas Heard y McDonald</option>
                                <option value="FK">Islas Malvinas</option>
                                <option value="MP">Islas Marianas del Norte</option>
                                <option value="MH">Islas Marshall</option>
                                <option value="UM">Islas menores de Estados Unidos</option>
                                <option value="PW">Islas Palau</option>
                                <option value="SB">Islas Salomón</option>
                                <option value="SJ">Islas Svalbard y Jan Mayen</option>
                                <option value="TK">Islas Tokelau</option>
                                <option value="TC">Islas Turks y Caicos</option>
                                <option value="VI">Islas Vírgenes (EEUU)</option>
                                <option value="VG">Islas Vírgenes (Reino Unido)</option>
                                <option value="WF">Islas Wallis y Futuna</option>
                                <option value="IL">Israel</option>
                                <option value="IT">Italia</option>
                                <option value="JM">Jamaica</option>
                                <option value="JP">Japón</option>
                                <option value="JO">Jordania</option>
                                <option value="KZ">Kazajistán</option>
                                <option value="KE">Kenia</option>
                                <option value="KG">Kirguizistán</option>
                                <option value="KI">Kiribati</option>
                                <option value="KW">Kuwait</option>
                                <option value="LA">Laos</option>
                                <option value="LS">Lesotho</option>
                                <option value="LV">Letonia</option>
                                <option value="LB">Líbano</option>
                                <option value="LR">Liberia</option>
                                <option value="LY">Libia</option>
                                <option value="LI">Liechtenstein</option>
                                <option value="LT">Lituania</option>
                                <option value="LU">Luxemburgo</option>
                                <option value="MK">Macedonia, Ex-República Yugoslava de</option>
                                <option value="MG">Madagascar</option>
                                <option value="MY">Malasia</option>
                                <option value="MW">Malawi</option>
                                <option value="MV">Maldivas</option>
                                <option value="ML">Malí</option>
                                <option value="MT">Malta</option>
                                <option value="MA">Marruecos</option>
                                <option value="MQ">Martinica</option>
                                <option value="MU">Mauricio</option>
                                <option value="MR">Mauritania</option>
                                <option value="YT">Mayotte</option>
                                <option value="MX">México</option>
                                <option value="FM">Micronesia</option>
                                <option value="MD">Moldavia</option>
                                <option value="MC">Mónaco</option>
                                <option value="MN">Mongolia</option>
                                <option value="MS">Montserrat</option>
                                <option value="MZ">Mozambique</option>
                                <option value="NA">Namibia</option>
                                <option value="NR">Nauru</option>
                                <option value="NP">Nepal</option>
                                <option value="NI">Nicaragua</option>
                                <option value="NE">Níger</option>
                                <option value="NG">Nigeria</option>
                                <option value="NU">Niue</option>
                                <option value="NF">Norfolk</option>
                                <option value="NO">Noruega</option>
                                <option value="NC">Nueva Caledonia</option>
                                <option value="NZ">Nueva Zelanda</option>
                                <option value="OM">Omán</option>
                                <option value="NL">Países Bajos</option>
                                <option value="PA">Panamá</option>
                                <option value="PG">Papúa Nueva Guinea</option>
                                <option value="PK">Paquistán</option>
                                <option value="PY">Paraguay</option>
                                <option value="PE">Perú</option>
                                <option value="PN">Pitcairn</option>
                                <option value="PF">Polinesia Francesa</option>
                                <option value="PL">Polonia</option>
                                <option value="PT">Portugal</option>
                                <option value="PR">Puerto Rico</option>
                                <option value="QA">Qatar</option>
                                <option value="UK">Reino Unido</option>
                                <option value="CF">República Centroafricana</option>
                                <option value="CZ">República Checa</option>
                                <option value="ZA">República de Sudáfrica</option>
                                <option value="DO">República Dominicana</option>
                                <option value="SK">República Eslovaca</option>
                                <option value="RE">Reunión</option>
                                <option value="RW">Ruanda</option>
                                <option value="RO">Rumania</option>
                                <option value="RU">Rusia</option>
                                <option value="EH">Sahara Occidental</option>
                                <option value="KN">Saint Kitts y Nevis</option>
                                <option value="WS">Samoa</option>
                                <option value="AS">Samoa Americana</option>
                                <option value="SM">San Marino</option>
                                <option value="VC">San Vicente y Granadinas</option>
                                <option value="SH">Santa Helena</option>
                                <option value="LC">Santa Lucía</option>
                                <option value="ST">Santo Tomé y Príncipe</option>
                                <option value="SN">Senegal</option>
                                <option value="SC">Seychelles</option>
                                <option value="SL">Sierra Leona</option>
                                <option value="SG">Singapur</option>
                                <option value="SY">Siria</option>
                                <option value="SO">Somalia</option>
                                <option value="LK">Sri Lanka</option>
                                <option value="PM">St Pierre y Miquelon</option>
                                <option value="SZ">Suazilandia</option>
                                <option value="SD">Sudán</option>
                                <option value="SE">Suecia</option>
                                <option value="CH">Suiza</option>
                                <option value="SR">Surinam</option>
                                <option value="TH">Tailandia</option>
                                <option value="TW">Taiwán</option>
                                <option value="TZ">Tanzania</option>
                                <option value="TJ">Tayikistán</option>
                                <option value="TF">Territorios franceses del Sur</option>
                                <option value="TP">Timor Oriental</option>
                                <option value="TG">Togo</option>
                                <option value="TO">Tonga</option>
                                <option value="TT">Trinidad y Tobago</option>
                                <option value="TN">Túnez</option>
                                <option value="TM">Turkmenistán</option>
                                <option value="TR">Turquía</option>
                                <option value="TV">Tuvalu</option>
                                <option value="UA">Ucrania</option>
                                <option value="UG">Uganda</option>
                                <option value="UY">Uruguay</option>
                                <option value="UZ">Uzbekistán</option>
                                <option value="VU">Vanuatu</option>
                                <option value="VE">Venezuela</option>
                                <option value="VN">Vietnam</option>
                                <option value="YE">Yemen</option>
                                <option value="YU">Yugoslavia</option>
                                <option value="ZM">Zambia</option>
                                <option value="ZW">Zimbabue</option>
                            </select>
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
                            <input type="String" class="form-control" id="cc-number" name="tarjeta" required="number" maxlength="19" onpaste="return false;" <% if (existeUsuarioErroneo) {%> value="<%= usuarioErroneo.getTarjetaCredito()%>" <% } %> >
                            <% if (request.getAttribute("errorTarjeta") != null) {%>
                            <small style="color:red"><%=request.getAttribute("errorTarjeta")%></small>
                            <% }%>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 mb-3">
                            <label for="cc-expiration">Expiración</label>
                            <input type="date" class="form-control" id="cc-expiration" name="expiracion" required="date">
                        </div>
                        <div class="col-md-3 mb-3">
                            <label for="cc-cvv">CVV</label>
                            <input type="String" class="form-control" id="cc-cvv" name="cvv" required="number" maxlength="3" onpaste="return false;" required <% if (existeUsuarioErroneo) {%> value="<%= request.getAttribute("codigoSeguridad")%>" <% } %> >
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

