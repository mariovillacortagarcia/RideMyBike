<%-- 
    Document   : index
    Created on : 25 abr. 2020, 15:05:10
    Author     : JCHFJ
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ridemybike.dominio.*"%>
<%@page import="ridemybike.dominio.db.*"%>
<!doctype html>
<html lang="es">

    <head>
        <title> Inicio - RideMyBike </title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="icon" type="image/png" href="img/RideMyBike_icon_green.png">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <link rel="stylesheet" href="https://unpkg.com/leaflet@1.6.0/dist/leaflet.css"
              integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
              crossorigin=""/>
        <style>
            #mapaglobal{
                width:40rem; 
                height:25rem;
            }
        </style>
    </head>

    <body>
        <!--- Cabecera -->
        <jsp:include page="header.jsp" >
            <jsp:param name="paginaMostrada" value="Home" />
            <jsp:param name="sesionIniciada" value="true" />
        </jsp:include>
        
        <!-- Contenido -->
        <div id="carouselAlquiler" class="carousel slide" data-ride="carousel" data-interval="false">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <!-- Parte 1/2 del formulario-->

                    <div id="principal" class="container p-4">
                        <div class="row">
                            <div class="col-lg-8 col-sm-12">
                                <!-- Mapa-->
                                <h5><b>1. Elige una bici libre </b>🚴</h5>
                                <div id="mapaglobal" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" style="border: 1px solid black">
                                </div><br>
                                Bicicleta seleccionada: <a id="bicicletaSeleccionada">Ninguna  </a>
                            </div>
                            <div class="col-lg-4 col-sm-12">
                                <!--Formulario de fecha -->
                                <form class="form-group mb-2" action="InicioPeticion" method="post">
                                    <h5><b>2. Indica el tiempo de uso </b>🕒</h5>
                                    <div class="form-group mb-2">
                                        <label for="entradaFechaInicio">Inicio del préstamo</label>
                                        <input type="datetime-local" class="form-control" id="fechaInicioPrestamo" aria-describedby="emailHelp" placeholder="Desde...">
                                        <small id="fechaInicioHelp" class="form-text text-muted">La fecha de inicio del préstamo (dd/mm/aaaa)</small>
                                    </div>
                                    <div class="form-group mb-5">
                                        <label for="entradaFechaFinalización">Fin del préstamo</label>
                                        <input type="datetime-local" class="form-control" id="fechaFinPrestamo" aria-describedby="emailHelp" placeholder="Hasta...">
                                        <small id="fechaFinHelp" class="form-text text-muted">La fecha de finalización del préstamo (dd/mm/aaaa)</small>
                                    </div>
                                    <a href="#carouselAlquiler" role="button" data-slide="next">
                                        <button class="btn btn-outline-success" type="submit">Siguiente</button>
                                    </a>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <%
                    // TO - DO: Obtener correctamente la id de la bicicletaSeleccionada.
                    //int idBiciSeleccionada = Integer.parseInt(request.getParameter("bicicletaSeleccionada"));
                    SimpleDateFormat FormFechaEntera = new SimpleDateFormat("dd-MM-yyyy-hh-mm");
                    Date fechaInicioPrestamo = FormFechaEntera.parse(request.getParameter("fechaInicioPrestamo"));
                    Date fechaFinPrestamo = FormFechaEntera.parse(request.getParameter("fechaFinPrestamo"));
                    SimpleDateFormat FormFecha = new SimpleDateFormat("dd-MM-yyyy");
                    SimpleDateFormat FormHora = new SimpleDateFormat("hh-mm");
                    Date fechaInicio = FormFecha.parse(request.getParameter("fechaInicioPrestamo"));
                    Date fechaFin = FormFecha.parse(request.getParameter("fechaFinPrestamo"));
                    Date horaInicio = FormHora.parse(request.getParameter("fechaInicioPrestamo"));
                    Date horaFin = FormHora.parse(request.getParameter("fechaFinPrestamo"));
                %>
                <div class="carousel-item">
                    <!-- Parte 2/2 del formulario-->
                    <div class="container p-4">
                        <div class="row">
                             <!-- Form añadido para enviar al servlet -->
                            <form method="post"
                            <div class="col-lg-8 col-sm-12">
                                <div class="container mb-10">
                                    <!-- Opciones adicionales del alquiler -->
                                    <h5><b>3. Opciones adicionales </b></h5>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="opSeguro">
                                        <label class="form-check-label" for="opSeguro">
                                            Seguro de viaje +1€
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="opTarde">
                                        <label class="form-check-label" for="opTarde">
                                            Llegaré tarde (media hora extra de espera) +1€
                                        </label>
                                    </div>
                                    <div class="form-check mb-5">
                                        <input class="form-check-input" type="checkbox" value="" id="opEnMano" disabled>
                                        <label class="form-check-label" for="opcion3">
                                            Alquiler en mano (no disponible para esta bici)
                                        </label>
                                    </div>
                                </div>
                                <a href="#carouselAlquiler" role="button" data-slide="prev">
                                    <button class="btn btn-outline-secondary">Anterior</button>
                                </a>
                            </div>
                            </form>
                            
                            <div class="col-lg-4 col-sm-12">
                                <!-- Resumen de confirmacion -->
                                <h5><b>4. Confirmar pago</b></h5>
                                <ul class="list-group">
                                    <li class="list-group-item">
                                        <p>
                                            Bicicleta: --- <br>
                                            Desde el <%=fechaInicio%> a las <%=horaInicio%><br>
                                            Hasta el <%=fechaFin%> a las <%=horaFin%><br>
                                            Metodo de pago: Mastercard
                                        </p>
                                    </li>
                                    <li class="list-group-item"><b>Total:</b> <%=request.getParameter("precio")%>€</li>
                                </ul>
                                <div class="container p-3">
                                    <button class="btn btn-outline-success">RideMyBike!</button>
                                    <button class="btn btn-outline-danger">Cancelar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Anterior
            <a class="carousel-control-prev" href="#carouselAlquiler" role="button" data-slide="prev">
            </a>-->
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
        <!-- Make sure you put this AFTER Leaflet's CSS -->
        <script src="https://unpkg.com/leaflet@1.6.0/dist/leaflet.js"
                integrity="sha512-gZwIG9x3wUXg2hdXF6+rVkLF/0Vi9U8D2Ntg4Ga5I5BZpVkVxlJWbSQtXPSiUTtC0TjtGOmxa1AJPuV0CPthew=="
        crossorigin=""></script>
        <script src="js/mapaglobal.js"></script>
    </body>

</html>
