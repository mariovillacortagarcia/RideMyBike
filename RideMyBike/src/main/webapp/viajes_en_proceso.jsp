<%@page import="ridemybike.dominio.Alquiler"%>
<%@page import="java.time.LocalDateTime"%>
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
        <div class="container-fluid" style="background-color:#85c1e9">
            <!---Cabecera -->
            <jsp:include page="header.jsp" >
                <jsp:param name="paginaMostrada" value="viajes" />
                <jsp:param name="sesionIniciada" value="false" />
            </jsp:include>
            <!-- Contenido -->
            <div class="container pt-4">
                <!-- Navegacion por los viajes -->
                <div class="card text-center">
                    <div class="card-header">
                        <ul class="nav nav-tabs card-header-tabs">
                            <li class="nav-item">
                                <a class="nav-link" href="viajes.html">Realizados</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="viajes_en_proceso.html">En proceso</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="viajes_archivados.html">Archivados</a>
                            </li>
                        </ul>
                    </div>
                    <div class="card-body">
                        <!-- Lista de viajes -->
                        <ul class="list-group overflow-auto">
                            <%
                                Alquiler alquileres[] = (Alquiler[]) request.getAttribute("alquileres");
                                boolean sinViajes = true;
                                if (alquileres != null) {
                                    for (Alquiler alquiler : alquileres) {
                                        String precio = Double.toString(alquiler.getPrecio());
                                        String inicio = alquiler.getInicio();
                                        String fin = alquiler.getFin();
                                        LocalDateTime fechaInicio = alquiler.getHoraInicial();
                                        LocalDateTime fechaFin = alquiler.getHoraFinal();
                                        if (alquiler.getFin() != null || alquiler.getHoraFinal() != null) {
                                            continue;
                                        }
                                        sinViajes = false;
                                        String diaInicio = Integer.toString(fechaInicio.getDayOfMonth());
                                        String mesInicio = Integer.toString(fechaInicio.getMonthValue());
                                        String anoInicio = Integer.toString(fechaInicio.getYear());
                                        String horaInicio = Integer.toString(fechaInicio.getHour()) + ":" + Integer.toString(fechaInicio.getMinute());
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
                                            <h6 class=""><b><%=inicio%> - <%=fin%></b></h6>
                                            <p>
                                                Desde el <%=diaInicio%> de <%=mesInicio%> de <%=anoInicio%> a las <%=horaInicio%> <br>
                                            </p>
                                            <p>
                                                <a class="text-secondary">Estimado: </a><b><%=precio%> ?</b>
                                            </p>
                                        </div>
                                        <div class="col-3">
                                            <!--Precio y valoracion-->
                                            <div class="container pb-2">
                                                <p class="text-secondary">No se puede puntuar todavía </p>
                                            </div>
                                            <div class="dropdown">
                                                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    Opciones
                                                </button>
                                                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                                    <a class="dropdown-item" href="registrar_incidencia.html">Registrar incidencia</a>
                                                    <a class="dropdown-item" href="#">Finalizar viaje</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>

                            <% }
                                }
                                if (sinViajes) {%>
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
            <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
            <script src="js/bootstrap.js"></script>
    </body>

</html>
