<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
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
        <!---Cabecera -->
        <% String s = session.getAttribute("usuario") == null ? "false" : "true";%>
        <jsp:include page="header.jsp" >
            <jsp:param name="paginaMostrada" value="Home" />
            <jsp:param name="sesionIniciada" value="<%= s%>" />
        </jsp:include>

        <!-- Contenido -->
        <div id="carouselAlquiler" class="carousel slide mt-4" data-ride="carousel" data-interval="false">
            <div class="carousel-inner">
                <form action="InicioPeticion" method="post">

                    <!-- Parte 1/2 del formulario -->
                    <!-- Mapa y fecha de la peticion -->
                    <div class="carousel-item active">
                        <div id="principal" class="container p-4">
                            <div class="row">

                                <!-- Mapa-->
                                <div class="col-lg-8 col-sm-12">
                                    <h5><b>1. Elige una bici libre </b>🚴</h5>
                                    <div id="mapaglobal" class="mt-3 mb-2" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" style="border: 1px solid black"></div>
                                    <div class="card mb-3" style="max-width: 540px;">
                                        <div class="row no-gutters">
                                            <div class="col-md-4">
                                                <img id="imgBici" src="" class="card-img" alt="Sin imagen disponible">
                                            </div>
                                            <div class="col-md-8">
                                                <div class="card-body">
                                                    <h5 class="card-title">Bicicleta seleccionada</h5>
                                                    <p class="card-text">No ha seleccionado ninguna bicicleta.</p>
                                                    <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    Bicicleta seleccionada: <a id="bicicletaUbicacion">Ninguna  </a>
                                    <input type="hidden" name="bicicletaId" id="bicicletaId" value="">
                                </div>

                                <!-- Formulario de fecha -->
                                <div class="col-lg-4 col-sm-12 mt-4">
                                    <h5><b>2. Indica el tiempo de uso </b>🕒</h5>

                                    <!-- Inicio de la peticion -->
                                    <div class="row mt-3 pl-3">
                                        <label for="entradaFechaInicio">Inicio del préstamo</label>
                                    </div>

                                    <div class="row">
                                        <div class="col">
                                            <input type="date" class="form-control" name="fechaInicioPrestamo" id="fechaInicioPrestamo">
                                        </div>
                                        <div class="col">
                                            <input type="time" class="form-control" name="horaInicioPrestamo" id="horaInicioPrestamo">
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <small id="fechaInicioHelp" class="form-text text-muted ml-3">La fecha y hora aproximada de inicio del préstamo.</small>
                                    </div> 

                                    <!-- Fin de la peticion -->
                                    <div class="form-group">
                                        <div class="row mt-3 pl-3">
                                            <label for="entradaFechaFinalización">Fin del préstamo</label>
                                        </div>

                                        <div class="row">
                                            <div class="col">
                                                <input type="date" class="form-control" name="fechaFinPrestamo" id="fechaFinPrestamo">
                                            </div>
                                            <div class="col">
                                                <input type="time" class="form-control" name="horaFinPrestamo" id="horaFinPrestamo">
                                            </div>
                                        </div>

                                        <div class="row mb-4">
                                            <small id="fechaFinHelp" class="form-text text-muted ml-3">La fecha y hora estimada de finalización del préstamo.</small>
                                        </div> 
                                    </div>
                                    <a href="#carouselAlquiler" role="button" data-slide="next">
                                        <button class="btn btn-outline-success">Siguiente</button>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Parte 2/2 del formulario-->
                    <div class="carousel-item mt-5">
                        <div class="container p-4">
                            <div class="row">

                                <!-- Opciones adicionales del alquiler -->
                                <div class="col-lg-8 col-sm-12">
                                    <div class="container mb-10">
                                        <h5><b>3. Opciones adicionales </b></h5>

                                        <!-- Seguro de viaje -->
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="" name="seguroViaje" id="seguroViaje">
                                            <label class="form-check-label" for="seguroViaje">
                                                Seguro de viaje +1€
                                            </label>
                                        </div>

                                        <!-- Llegaré tarde -->
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="" name="llegareTarde" id="llegareTarde">
                                            <label class="form-check-label" for="llegareTarde">
                                                Llegaré tarde (media hora extra de espera) +1€
                                            </label>
                                        </div>

                                        <!-- Alquiler en mano o estandar -->
                                        <div class="form-check mb-5">
                                            <input class="form-check-input" type="checkbox" value="" name="alquilerEnMano" id="alquilerEnMano">
                                            <label class="form-check-label" for="alquilerEnMano">
                                                Alquiler en mano (bicicleta entregada presencialmente)
                                            </label>
                                        </div>
                                    </div>
                                    <a href="#carouselAlquiler" role="button" data-slide="prev">
                                        <button class="btn btn-outline-secondary">Anterior</button>
                                    </a>
                                </div>

                                <!-- Resumen de confirmacion -->
                                <div class="col-lg-4 col-sm-12">
                                    <h5><b>4. Confirmar pago</b></h5>
                                    <ul class="list-group">
                                        <li class="list-group-item">
                                            <p>
                                                Bicicleta: <label id="textoBiciSeleccionada"></label><br>
                                                Desde el <label name="textoFechaInicio" id="textoFechaInicio"></label> a las <label id="textoHoraInicio"></label><br>
                                                Hasta el <label id="textoFechaFin"></label> a las <label id="textoHoraFin"></label> <br>
                                                Metodo de pago: Mastercard
                                            </p>
                                        </li>
                                        <li class="list-group-item"><b>Total: </b> <label name="textoPrecioTotal" id="textoPrecioTotal"></label>€</li>
                                    </ul>
                                    <div class="container p-3">
                                        <button class="btn btn-outline-success" type="submit">RideMyBike!</button>
                                        <a href="index.jsp" role="button">
                                            <button type="button" class="btn btn-outline-danger" href="index.jsp" name="cancelarPeticion" id="cancelarPeticion">Cancelar</button>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.0.js" integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="js/bootstrap.js"></script>

        <!-- Leaflet's JS -->
        <script src="https://unpkg.com/leaflet@1.6.0/dist/leaflet.js" integrity="sha512-gZwIG9x3wUXg2hdXF6+rVkLF/0Vi9U8D2Ntg4Ga5I5BZpVkVxlJWbSQtXPSiUTtC0TjtGOmxa1AJPuV0CPthew==" crossorigin=""></script>
        <script src="js/mapaglobal.js"></script>

        <!-- Index JS -->
        <script src="js/index.js"></script>
    </body>
</html>
