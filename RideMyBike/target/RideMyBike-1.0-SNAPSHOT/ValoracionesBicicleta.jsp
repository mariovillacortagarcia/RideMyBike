<%@page import="java.time.LocalDateTime"%>
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
        <!-- Star rating -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            .checked {
                color: orange;
            }
        </style>
    </head>

    <body>
        <% String s = session.getAttribute("usuario") == null ? "false" : "true"; %>
        <jsp:include page="header.jsp" >
            <jsp:param name="paginaMostrada" value="ValoracionesBicicleta" />
            <jsp:param name="sesionIniciada" value="<%= s %>" />
        </jsp:include>  
        <!----- --->
        <%
            String nombreUsuario = session.getAttribute("usuario") == null ? "value='false'" : "value='true'";  
            ArrayList<ValoracionBicicleta> valoraciones = (ArrayList<ValoracionBicicleta>) request.getAttribute("valoraciones");           
        %>
        <div class="container pt-4 my-3">
            <!-- Navegacion por los viajes -->
            <div class="card text-center">
                <div class="card-header">Lista de opiniones</div>
                <div class="card-body">
                    <!-- Lista de Opiniones -->
                    <ul class="list-group overflow-auto">
                        <%
                            if (!valoraciones.isEmpty()) {
                                for (int i = 0; i < valoraciones.size(); i++) {
                                    //Servlet SeleccionaAlquiler
                                    Valoracion valoracion = valoraciones.get(i);
                                    String descripcion = valoracion.getDescripcion();
                                    int puntuacion = valoracion.getPuntuacion();

                        %>
                        <li class="list-group-item">
                            <!--Opinion 1-->
                            <div class="alert alert-secondary bg-white" role="alert">
                                <div class="row">
                                    <div class="col-1"></div>
                                    <div class="col-5">
                                        <!--Informacion-->
                                        <p>
                                            <b>Descripcion: </b><%= descripcion%>
                                        </p>
                                    </div>
                                    <div class="col-6" >
                                        <!--Precio y valoracion-->
                                        <div class="container pb-2"> <br> 
                                            <%
                                                
                                                int numEstrellasNegro = 5 - puntuacion;
                                                for (int j = 0; j < puntuacion; j++) {
                                            %>
                                            <span class="fa fa-star checked"></span>
                                            <%
                                                }
                                                for (int j = 0; j < numEstrellasNegro; j++) {
                                            %>
                                            <span class="fa fa-star "></span>
                                            <%
                                                }
                                            %>                 
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <%
                            }
                        } else {
                        %>
                        <li class="list-group-item">
                            <div class="alert alert-light" role="alert">
                                No hay ninguna opinión para esta bicicleta
                            </div>
                        </li>
                        <%
                            }
                        %>
                    </ul>
                </div>
            </div>
        </div>
</html>
