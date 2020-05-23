<%@page import="ridemybike.dominio.MensajeUsuario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">

    <head>
        <title>Dudas y sugerencias - RideMyBike </title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="icon" type="image/png" href="img/RideMyBike_icon_green.png">
        
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        
        <!-- Estilo de la tabla -->
        <link rel="stylesheet" href="css/tabla-incidencias.css">
    </head>

    <body>
        <!---Cabecera -->
        <jsp:include page="header_admin.jsp" >
            <jsp:param name="paginaMostrada" value="DudasSugerencias" />
        </jsp:include>

        <!-- Contenido -->
        <div class="container">
            <div class="row mt-5">
                <div class="col-12">
                    <table class="table table-bordered" bordercolor="black">
                        <thead>
                            <%
                                ArrayList<MensajeUsuario> problemas = (ArrayList<MensajeUsuario>) request.getAttribute("dudasSugerencias");
                                if (problemas.isEmpty()) {%>
                        <li class="list-group-item">
                            <div class="alert alert-light" role="alert">
                                No hay dudas o sugerencias pendientes de revisión
                            </div>
                        </li>
                        <% } else { %>
                        <tr>
                            <th scope="col">Duda/Sugerencia</th>
                            <th scope="col">Fecha</th>
                            <th scope="col">Asunto</th>
                            <th scope="col">Descripción</th>
                            <th scope="col">Acciones</th>
                        </tr>
                        <% } %>
                        </thead>
                        <tbody>
                            <%

                                MensajeUsuario problema;
                                String asunto;
                                String descripcion;
                                String fecha;
                                for (int i = 0; i < problemas.size(); i++) {
                                    problema = problemas.get(i);
                                    fecha = problema.getFechaCreacion().toString();
                                    asunto = problema.getAsunto();
                                    descripcion = problema.getDescripcion();
                            %>
                            <tr>
                                <th scope="row"><%=i + 1%></th>
                                <td><%=fecha%></td>
                                <td><%=asunto%></td>
                                <td><%=descripcion%></td>
                                <td>
                                    <a type="button" class="btn btn-success" href="SolucionarDudaSugerencia?codigoDudaSugerencia=<%= problema.getCodigo()%>">Solucionar</a>
                                </td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.0.js" integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="js/bootstrap.js"></script>
    </body>
</html>
