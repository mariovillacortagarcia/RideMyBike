<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">

    <head>
        <title> Perfil de Usuario - RideMyBike </title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="icon" type="image/png" href="img/RideMyBike_icon_green.png">
        <link rel="stylesheet" href="css/style.css">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

        <!-- Star rating -->
        <style>
            .checked {
                color: orange;
            }
        </style>

        <!-- Inputfile style -->
        <link rel="stylesheet" href="css/inputfile.css">
    </head>

    <body>
        <div class="container-fluid" style="background-color:#85c1e9">
            <!---Sin cabecera cuando se ha iniciado sesion -->
            <nav class="navbar navbar-expand-lg navbar-dark">
                <!-- Menu de navegacion -->
                <a class="navbar-brand" href="index.jsp">RideMyBike</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle	navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="index.jsp">Home 🏠</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="RecuperarBicicletas">Mis Bicis 🚴</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="RecuperarViajes">Viajes 🚵</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="garantias.jsp">Garantías 🛡</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="sobre_nosotros.jsp">Sobre nosotros 💬</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="ayuda.jsp">Ayuda ❓</a>
                        </li>
                        <li class="nav-item">
                        </li>
                    </ul>
                </div>
            </nav>
        </div>

        <!-- Contenido -->
        <%@page import="ridemybike.dominio.db.UsuarioDB"%>
        <%@page import="ridemybike.dominio.db.ValoracionUsuarioDB"%>
        <%@page import="ridemybike.dominio.Usuario"%>
        <%
            Usuario user = (Usuario) request.getAttribute("user");

            if (request.getAttribute("usuarioErroneo") != null) {
                user = (Usuario) request.getAttribute("usuarioErroneo");
            }
        %>
        <div class="container mt-5">
            <div class="row flex-lg-nowrap">
                <div class="col">
                    <div class="row">
                        <div class="col mb-3">
                            <form class="form" novalidate="" id="form-datos-usuario" name="form-datos-usuario" action="ActualizarPerfil" method="post" enctype="multipart/form-data">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="e-profile">
                                            <div class="row mb-4">

                                                <div class="col-12 col-sm-auto mb-3">
                                                    <div class="mx-auto" style="width: 160px;">
                                                        <div class="d-flex justify-content-center align-items-center rounded" style="height: 160px; background-color: rgb(233, 236, 239);">
                                                            <img src="RecuperarImagenPerfil?usuario=<%= user.getNickName()%>" id="fotoPerfil" class="img-thumbnail" alt="..." style="width:  160px;height:  160px">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col d-flex flex-column flex-sm-row justify-content-between mb-3">
                                                    <div class="text-center text-sm-left mb-2 mb-sm-0">
                                                        <h4 class="pt-sm-2 pb-1 mb-0 text-nowrap"><%= user.getNombre() + " " + user.getApellidos()%></h4>
                                                        <p class="mb-0">@<%= user.getNickName()%> </p>
                                                        <div class="pt-1">
                                                            <%
                                                                int valoracionMedia = user.getValoracionMedia();
                                                                int numEstrellasNegro = 5 - valoracionMedia;
                                                                for (int i = 0; i < valoracionMedia; i++) {
                                                            %>
                                                            <span class="fa fa-star checked"></span>
                                                            <%
                                                                }
                                                                for (int i = 0; i < numEstrellasNegro; i++) {
                                                            %>
                                                            <span class="fa fa-star "></span>
                                                            <%
                                                                }
                                                            %>
                                                        </div>
                                                        <div class="mt-4 pt-1">
                                                            <input type="file" accept=".png, .jpeg, .jpg" name="fotoElegida" id="fotoElegida" class="inputfile"/>
                                                            <label for="fotoElegida">
                                                                <i class="fa fa-fw fa-camera"></i>
                                                                <span>Cambiar foto</span>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <ul class="nav nav-tabs">
                                                <li class="nav-item"><a href="" class="active nav-link"><b>Información de usuario</b></a></li>
                                            </ul>
                                            <div class="tab-content pt-3">
                                                <div class="tab-pane active">
                                                    <div class="row">
                                                        <div class="col">
                                                            <div class="row">
                                                                <div class="col">
                                                                    <div class="form-group">
                                                                        <label>Nombre</label>
                                                                        <input class="form-control" type="text" maxlength="50" name="nombre" required="text" value="<%= user.getNombre()%>">
                                                                        <% if (request.getAttribute("errorNombre") != null) {%>
                                                                        <small style="color:red"><%=request.getAttribute("errorNombre")%></small>
                                                                        <% }%>
                                                                    </div>
                                                                </div>
                                                                <div class="col">
                                                                    <div class="form-group">
                                                                        <label>Apellidos</label>
                                                                        <input class="form-control" type="text" maxlength="100" name="apellidos" required="text" value="<%= user.getApellidos()%>">
                                                                        <% if (request.getAttribute("errorApellidos") != null) {%>
                                                                        <small style="color:red"><%=request.getAttribute("errorApellidos")%></small>
                                                                        <% }%>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col">
                                                                    <div class="form-group">
                                                                        <label>E-mail</label>
                                                                        <input class="form-control" type="text" name="email" maxlength="100" required="text" value="<%= user.getEmail()%>">
                                                                        <% if (request.getAttribute("errorEmail") != null) {%>
                                                                        <small style="color:red"><%=request.getAttribute("errorEmail")%></small>
                                                                        <% }%>
                                                                    </div>
                                                                </div>
                                                                <div class="col">
                                                                    <div class="form-group">
                                                                        <label>Usuario</label>
                                                                        <input class="form-control" readonly type="text" name="usuario" value="<%= user.getNickName()%>">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <ul class="nav nav-tabs mb-3 mt-4">
                                                        <li class="nav-item"><a href="" class="active nav-link"><b>Información privada</b></a></li>
                                                    </ul>
                                                    <div class="row">
                                                        <div class="col">
                                                            <div class="row">
                                                                <div class="col">
                                                                    <div class="form-group">
                                                                        <label>DNI, NIF, CIF y/o NIE</label>
                                                                        <input class="form-control" readonly type="text" name="dni" value="<%= user.getDNI()%>">
                                                                    </div>
                                                                </div>
                                                                <div class="col">
                                                                    <div class="form-group">
                                                                        <label>Teléfono móvil o fijo</label>
                                                                        <input class="form-control" type="text" name="telefono" required="text" value="<%= user.getTlf()%>">
                                                                        <% if (request.getAttribute("errorTlf") != null) {%>
                                                                        <small style="color:red"><%=request.getAttribute("errorTlf")%></small>
                                                                        <% }%>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col">
                                                                    <div class="form-group">
                                                                        <label>Dirección</label>
                                                                        <input class="form-control" type="text" name="direccion" maxlength="150" required="text" value="<%= user.getDireccion()%>">
                                                                        <% if (request.getAttribute("errorDireccion") != null) {%>
                                                                        <small style="color:red"><%=request.getAttribute("errorDireccion")%></small>
                                                                        <% }%>
                                                                    </div>
                                                                </div>
                                                                <div class="col">
                                                                    <div class="form-group">
                                                                        <label>Número de tarjeta</label>
                                                                        <input class="form-control" type="text" maxlength="150" name="tarjeta" required="text" value="<%= user.getTarjetaCredito()%>">
                                                                        <% if (request.getAttribute("errorTarjeta") != null) {%>
                                                                        <small style="color:red"><%=request.getAttribute("errorTarjeta")%></small>
                                                                        <% }%>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <ul class="nav nav-tabs mb-3 mt-4">
                                                        <li class="nav-item"><a href="" class="active nav-link"><b>Cambiar contraseña</b></a></li>
                                                    </ul>
                                                    <div class="row">
                                                        <div class="col-12 col-sm-6 mb-3">

                                                            <div class="row">
                                                                <div class="col">
                                                                    <div class="form-group">
                                                                        <label>Contraseña actual</label>
                                                                        <input class="form-control" type="password" maxlength="128" name="passwordActual" placeholder="••••••">
                                                                        <% if (request.getAttribute("errorPasswordActual") != null) {%>
                                                                        <small style="color:red"><%=request.getAttribute("errorPasswordActual")%></small>
                                                                        <% }
                                                                            if (request.getAttribute("passwordCambiadaConExito") != null) {%>
                                                                        <small style="color:green"><%=request.getAttribute("passwordCambiadaConExito")%></small>
                                                                        <% }%>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col">
                                                                    <div class="form-group">
                                                                        <label>Nueva contraseña</label>
                                                                        <input class="form-control" type="password" maxlength="128" name="passwordNueva" placeholder="••••••">
                                                                        <% if (request.getAttribute("errorPasswordNueva") != null) {%>
                                                                        <small style="color:red"><%=request.getAttribute("errorPasswordNueva")%></small>
                                                                        <% }%>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col">
                                                                    <div class="form-group">
                                                                        <label><span class="d-none d-xl-inline">Confirmar contraseña</span></label>
                                                                        <input class="form-control" type="password" maxlength="128" name="passwordNuevaConfirmacion" placeholder="••••••">
                                                                        <% if (request.getAttribute("errorPasswordConfirmada") != null) {%>
                                                                        <small style="color:red"><%=request.getAttribute("errorPasswordConfirmada")%></small>
                                                                        <% }%>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col d-flex justify-content-end">
                                                            <button class="btn btn-primary" type="submit">Guardar cambios</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <div class="col-12 col-md-3 mb-3">
                            <div class="card mb-3">
                                <div class="card-body">
                                    <div class="px-xl-3">
                                        <button class="btn btn-block btn-secondary" onclick="location.href = 'CerrarSesion'">
                                            <i class="fa fa-sign-out"></i>
                                            <span>Cerrar sesión</span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-body">
                                    <h6 class="card-title font-weight-bold">Soporte</h6>
                                    <p class="card-text">Si necesitas ayuda con algo, no dudes en contactarnos.</p>
                                    <button type="button" class="btn btn-primary">Contáctanos</button>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/perfil.js"></script>
    </body>

</html>
