<%@page import="java.util.ArrayList"%>
<%@page import="ridemybike.dominio.Alquiler"%>
<%@page import="java.time.LocalDateTime"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">

    <head>
        <title> Viajes - RideMyBike </title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="icon" type="image/png" href="img/RideMyBike_icon_green.png">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.css">

        <!-- Star rating -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            .checked {
                color: orange;
            }
        </style>
    </head>

    <body>
        <!---Cabecera -->
        <!---Cabecera -->
        <% String s = session.getAttribute("usuario") == null ? "false" : "true";%>
        <jsp:include page="header.jsp" >
            <jsp:param name="paginaMostrada" value="Viajes" />
            <jsp:param name="sesionIniciada" value="<%= s%>" />
        </jsp:include>

        <!-- Contenido -->
        <div class="container pt-4 mb-5">
            <!-- Navegacion por los viajes -->
            <div class="card text-center">
                <div class="card-header">
                    <ul class="nav nav-tabs card-header-tabs">
                        <li class="nav-item">
                            <a class="nav-link" href="RecuperarViajes">Realizados</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="RecuperarViajesEnProceso">En proceso</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="RecuperarViajesArchivados">Archivados</a>
                        </li>
                    </ul>
                </div>
                <div class="card-body">
                    <!-- Lista de viajes -->
                    <ul class="list-group overflow-auto">
                        <%
                            ArrayList<Alquiler> alquileres = (ArrayList<Alquiler>) request.getAttribute("alquileres");
                            if (!alquileres.isEmpty()) {
                                for (Alquiler alquiler : alquileres) {
                                    int idAlquiler = alquiler.getCodigoAlquiler();
                                    String precio = Double.toString(alquiler.getPrecio());
                                    String inicio = alquiler.getInicio();
                                    LocalDateTime fechaInicio;
                                    String diaInicio = "";
                                    String mesInicio = "";
                                    String anoInicio = "";
                                    String horaInicio = "";
                                    if (alquiler.getHoraInicial() != null) {
                                        fechaInicio = alquiler.getHoraInicial().toLocalDateTime();
                                        diaInicio = Integer.toString(fechaInicio.getDayOfMonth());
                                        mesInicio = Integer.toString(fechaInicio.getMonthValue());
                                        anoInicio = Integer.toString(fechaInicio.getYear());
                                        horaInicio = Integer.toString(fechaInicio.getHour()) + ":" + Integer.toString(fechaInicio.getMinute());
                                    }

                        %>
                        <li class="list-group-item">
                            <!--Viaje 1-->
                            <div class="alert alert-secondary bg-white" role="alert">
                                <div class="row">
                                    <div class="col-3">
                                        <!--Mapa con ruta realizada-->
                                        <img class="img-thumbnail" src="img/test/viaje1.png">
                                    </div>
                                    <div class="col-6">
                                        <!--Informacion del viaje-->
                                        <b><h6 class="ubicacion"><%=inicio%></h6><h6> - ?</h6></b>
                                                <% if (alquiler.getHoraInicial() != null) {%>
                                        <p>
                                            Desde el <%=diaInicio%> de <%=mesInicio%> de <%=anoInicio%> a las <%=horaInicio%> <br>
                                        </p>
                                        <% } else { %>
                                        <p>
                                            Viaje no iniciado <br>
                                        </p>
                                        <% }%>
                                        <p>
                                            <a class="text-secondary">Estimado: </a><b><%=precio%> €</b>
                                        </p>
                                    </div>
                                    <div class="col-3">
                                        <!--Precio y valoracion-->
                                        <!--
                                        <div class="container pb-2">
                                            <p class="text-secondary">No se puede puntuar todavía </p>
                                        </div>-->
                                        <div class="dropdown">
                                            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                Opciones
                                            </button>
                                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                                <a class="dropdown-item" href="registrar_incidencia.jsp?idAlquiler=<%=idAlquiler%>">Registrar incidencia</a>
                                                <%if (alquiler.getHoraInicial() != null) {%>
                                                <a class="dropdown-item" href="TerminarViaje?codigoAlquiler=<%= alquiler.getCodigoAlquiler()%>">Finalizar viaje</a>
                                                <%} else {%>
                                                <a class="dropdown-item" href="IniciarViaje?codigoAlquiler=<%= alquiler.getCodigoAlquiler()%>">Iniciar viaje</a>

                                                <%}%>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>

                        <% }
                            }
                            else {%>
                        <li class="list-group-item">
                            <!--Viaje 1-->
                            <div class="alert alert-light" role="alert">
                                No hay ningún viaje en proceso
                            </div>
                        </li>
                        <% }%>
                    </ul>
                </div>
            </div>

        </div>

        
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.0.js" integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="js/bootstrap.js"></script>


        <link rel="stylesheet" href="https://unpkg.com/leaflet@1.4.0/dist/leaflet.css" integrity="sha512-puBpdR0798OZvTTbP4A8Ix/l+A4dHDD0DGqYW6RQ+9jxkRFclaxxQb/SJAWZfWAkuyeQUytO7+7N4QKrDh+drA==" crossorigin="" />
        <script src="https://unpkg.com/leaflet@1.4.0/dist/leaflet.js" integrity="sha512-QVftwZFqvtRNi0ZyCtsznlKSWOStnDORoefr1enyq5mVL4tmKB3S/EnC3rRJcxCPavG10IcrVGSmPh6Qw5lwrg==" crossorigin=""></script>


        <!-- Load Esri Leaflet from CDN -->
        <script src="https://unpkg.com/esri-leaflet@2.2.3/dist/esri-leaflet.js" integrity="sha512-YZ6b5bXRVwipfqul5krehD9qlbJzc6KOGXYsDjU9HHXW2gK57xmWl2gU6nAegiErAqFXhygKIsWPKbjLPXVb2g==" crossorigin=""></script>


        <!-- Load Esri Leaflet Geocoder from CDN -->
        <link rel="stylesheet" href="https://unpkg.com/esri-leaflet-geocoder@2.2.13/dist/esri-leaflet-geocoder.css" integrity="sha512-v5YmWLm8KqAAmg5808pETiccEohtt8rPVMGQ1jA6jqkWVydV5Cuz3nJ9fQ7ittSxvuqsvI9RSGfVoKPaAJZ/AQ==" crossorigin="">
        <script src="https://unpkg.com/esri-leaflet-geocoder@2.2.13/dist/esri-leaflet-geocoder.js" integrity="sha512-zdT4Pc2tIrc6uoYly2Wp8jh6EPEWaveqqD3sT0lf5yei19BC1WulGuh5CesB0ldBKZieKGD7Qyf/G0jdSe016A==" crossorigin=""></script>
        <script src="js/traduccionDirecciones.js"></script>
    </body>

</html>
