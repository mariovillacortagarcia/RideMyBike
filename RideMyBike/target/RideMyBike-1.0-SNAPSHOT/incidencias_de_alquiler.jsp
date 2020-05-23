<%-- 
    Document   : incidencias_de_alquiler
    Created on : 10 may. 2020, 1:02:19
    Author     : davidmd
--%>

<%@page import="ridemybike.dominio.Incidencia"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de incidencias</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="icon" type="image/png" href="img/RideMyBike_icon_green.png">
        
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.css">
        
        <!-- Estilo de la tabla -->
        <link rel="stylesheet" href="css/tabla-incidencias.css">


    </head>
    <body>
        <!---Cabecera -->
        <% String s = session.getAttribute("usuario") == null ? "false" : "true"; %>
        <jsp:include page="header.jsp" >
            <jsp:param name="paginaMostrada" value="" />
            <jsp:param name="sesionIniciada" value="<%= s %>" />
        </jsp:include>

        <!-- Contenido -->
        <div class="container">
            <div class="row mt-5">
                <div class="col-12">
                    <table class="table table-bordered" bordercolor="black">
                        <thead>
                            <%
                                ArrayList<Incidencia> incidencias = (ArrayList<Incidencia>) request.getAttribute("incidencias");
                                if (incidencias.isEmpty()) {%>
                        <li class="list-group-item">
                            <div class="alert alert-light" role="alert">
                                No hay ninguna incidencia para este viaje
                            </div>
                        </li>
                        <% } else { %>
                        <tr>
                            <th scope="col">Incidencia</th>
                            <th scope="col">Gravedad</th>
                            <th scope="col">Descripción</th>
                            <th scope="col">Acciones</th>
                        </tr>
                        <% } %>
                        </thead>
                        <tbody>
                            <%

                                Incidencia incidencia;
                                String grado;
                                String descripcion;
                                String color;
                                for (int i = 0; i < incidencias.size(); i++) {
                                    incidencia = incidencias.get(i);
                                    grado = incidencia.getGravedad().toString();
                                    descripcion = incidencia.getDescripcion();
                                    color = "red";
                                    if (grado.equals("Leve")) {
                                        color = "green";
                                    }
                                    if (grado.equals("Moderado")) {
                                        color = "darkorange";
                                    }
                            %>
                            <tr>
                                <th scope="row"><%=i + 1%></th>
                                <td><font color="<%=color%>"><%=grado%></font></td>
                                <td><%=descripcion%></td>
                                <td>
                                    <a type="button" class="btn btn-success" href="SolucionarIncidencia?codigoIncidencia=<%=incidencia.getCodigoIncidencia()%>&codigoAlquiler=<%= request.getAttribute("codigoAlquiler")%>">Solucionar</a>
                                </td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
