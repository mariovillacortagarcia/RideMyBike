<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ridemybike.dominio.*"%>
<%@page import="ridemybike.dominio.db.*"%>
<!doctype html>
<html lang="es">

    <head>
        <title> Mis bicis - RideMyBike </title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="icon" type="image/png" href="img/RideMyBike_icon_green.png">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.css">
        
        <style>
            .carousel-inner {
                overflow: visible;
            }
        </style>
    </head>

    <body>
        <% String var = session.getAttribute("usuario") == null ? "false" : "true";%>
        <jsp:include page="header.jsp" >
            <jsp:param name="paginaMostrada" value="MisBicisActivas" />
            <jsp:param name="sesionIniciada" value="<%= var%>" />
        </jsp:include>      


        <!-- TBicicletas que el usuario tiene en la aplicación registradas o en proceso -->



        <%
            ArrayList<Bicicleta> listaBicicletas = null;
            listaBicicletas = (ArrayList<Bicicleta>) request.getAttribute("lista");
        %>
        <div class="container pt-4 mt-3">
            <div class="row">
                <div class="col-6">
                    <div class="dropdown" >
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Bicicletas Activas
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" >
                            <a class="dropdown-item" href="RecuperarBicicletas">Todas las bicicletas</a>
                            <a class="dropdown-item" href="RecuperarBicicletasActivas">Bicicletas Activas</a>
                            <a class="dropdown-item" href="RecuperarBicicletasDesactivadas">Bicicletas Desactivadas</a>
                        </div>
                    </div>
                </div>
                <div class="col-6 text-right">
                    <a href="registrar_bicicleta.jsp"><button type="button" class="btn btn-secondary " name="botonRegistraBicicleta">Registrar nueva bicicleta</button> </a>
                </div>
            </div>
            <div class="row pt-4 mt-4">
                <div class="col-12">
                    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <%
                                EstadoBicicleta estado = EstadoBicicleta.Activado;
                                ArrayList<Bicicleta> lista = new ArrayList<Bicicleta>();
                                for (Bicicleta bici : listaBicicletas) {
                                    if (bici.getEstado() == estado) {
                                        lista.add(bici);
                                    }
                                }

                                for (int i = 0; i < lista.size(); i++) {
                            %>
                            <li data-target="#carouselExampleIndicators" data-slide-to="<%= i%>" class="active"></li>
                                <%}%>
                        </ol>         
                        <div class="carousel-inner" style="height: 450px">
                            <%  for (int i = 0; i < lista.size(); i++) {
                                    String marca = lista.get(i).getMarca();
                                    String modelo = lista.get(i).getModelo();
                                    Double tamano = lista.get(i).getTamCuadro();
                                    String freno = lista.get(i).getFreno().toString();
                                    String descripcion = lista.get(i).getDescripcion();
                                    int codigoBici = lista.get(i).getcodigoBici();
                                    String nombreUsuario = lista.get(i).getUsuarioPropietario();
                                    String latitud = Double.toString(lista.get(i).getLatitud());
                                    String longitud = Double.toString(lista.get(i).getLongitud());

                            %>
                            <div class="carousel-item <% String s = (i == 0) ? "active" : "";%> <%= s%> ">
                                <div class="row">
                                    <div class="col-1"></div>
                                    <div class="col-5">
                                        <img src="BicicletasEstados?codigoBici=<%=codigoBici%>" class="img-thumbnail" alt="..." style="width:  350px;height:  400px">
                                    </div>
                                    <div class="col-5">
                                        <div class="row">
                                            <div class="col-12"><b>Estado: </b> 
                                                <a class="text-success"><%=estado%></a>
                                            </div> 
                                            <div class="col-12"><b>Marca: </b><%=marca%></div>
                                            <div class="col-12"><b>Modelo: </b><%=modelo%></div>
                                            <div class="col-12"><b>Tamaño de Cuadro: </b><%=tamano%>cm</div>
                                            <div class="col-12"><b>Tipo de Freno: </b><%=freno%></div>
                                            <div class="col-12"><b>Descripción: </b> <%=descripcion%></div>
                                            <div class="col-12"><b>Ubicación: </b><a class="ubicacion">{"lat":<%=latitud%>, "lng": <%=longitud%>}</a></div>

                                            <div class="col-12 text-center pt-4">
                                                <div class="dropdown" name="selector2">
                                                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                        Opciones
                                                    </button>
                                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                                        <a class="dropdown-item" href="ValoracionesBicicleta?codigoBicicleta=<%=codigoBici%>">Opiniones</a>
                                                        <a class="dropdown-item" href="ActivacionBicicleta?codigoBicicleta=<%=codigoBici%>">Activar/Desactivar Bicicleta</a>
                                                        <a class="dropdown-item" href="HistorialAlquileresBicicleta?codigoBicicleta=<%=codigoBici%>">Historial de Alquileres</a>
                                                        <a class="dropdown-item" href="VerIncidenciasBicicleta?codigoBicicleta=<%=codigoBici%>">Ver incidencias</a>
                                                        <a class="dropdown-item" href="EliminarBicicleta?codigoBicicleta=<%=codigoBici%>">Eliminar</a>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>                              

                            <% } %>

                            <% if (lista.isEmpty()) { %>
                            <li class="list-group-item">
                                <!--Viaje 1-->
                                <div class="alert alert-light" role="alert">
                                    No hay ninguna bicicleta activa
                                </div>
                            </li>
                            <% }%>

                            <% if (!lista.isEmpty()) { %>
                            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                            <% }%>

                        </div>  
                    </div>
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

