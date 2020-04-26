

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
    <jsp:include page="header.jsp" >
        <jsp:param name="paginaMostrada" value="MisBicis" />
        <jsp:param name="sesionIniciada" value="true" />
    </jsp:include> 

<!----- --->
<% 
    String codigoBicicleta = request.getAttribute("codigoBicicleta").toString();
    //se supone que codigoBicicleta es el parametro del anterior JSP y que usare para 
    //llamar al servlet extraeBicicleta para obtener la bicicleta
    ArrayList<ValoracionBicicleta> listaOpiniones= new ArrayList<ValoracionBicicleta>();
    listaOpiniones = (ArrayList<ValoracionBicicleta>) request.getAttribute("lista");
    //se supone que codigoBicicleta es el parametro del anterior JSP y que usare para 
    //llamar al servlet SeleccionaAlquiler para obtener la lista de Alquileres (mismo tamano que valoraciones)
    ArrayList<Alquiler> listaAlquileres= new ArrayList<Alquiler>();
    listaAlquileres = (ArrayList<Alquiler>) request.getAttribute("listaAlquileres");
%>
    <div class="container pt-4">
        <!-- Navegacion por los viajes -->
        <div class="card text-center">
          <div class="card-header">Lista de Opiniones:      </div>
          <div class="card-body">
            <!-- Lista de Opiniones -->
            <ul class="list-group overflow-auto">
                <%
                    if(listaOpiniones != null){
                        for(int i = 0; i < listaOpiniones.size(); i++){
                        //Servlet SeleccionaAlquiler
                            Alquiler alquiler = listaAlquileres.get(i);
                    %>
                            <li class="list-group-item">
                              <!--Opinion 1-->
                                <div class="alert alert-secondary bg-white" role="alert">
                                    <div class="row">
                                    <div class="col-1"></div>
                                    <div class="col-5">
                                    <!--Informacion-->
                                        <p align="left">
                                            Desde el <%=alquiler.getHoraInicial().getDayOfMonth() %> del <%=alquiler.getHoraInicial().getMonthValue() %> de <%=alquiler.getHoraInicial().getYear() %> a las <%=alquiler.getHoraInicial().getHour() %>:<%=alquiler.getHoraInicial().getMinute()%> <br>
                                            Hasta el <%=alquiler.getHoraFinal().getDayOfMonth() %> del <%=alquiler.getHoraFinal().getMonthValue() %> de <%=alquiler.getHoraFinal().getYear() %> a las <%=alquiler.getHoraFinal().getHour() %>:<%=alquiler.getHoraFinal().getMinute()%>
                                        </p>
                                        <p>
                                            <b>Descripcion:</b><%=listaOpiniones.get(i).getDescripcion() %>
                                        </p>
                                    </div>
                                    <div class="col-6" >
                                    <!--Precio y valoracion-->
                                        <div class="container pb-2"> <br> 
                                        <%
                                            int puntuacionRecibida = listaOpiniones.get(i).getPuntuacion();
                                            int contador = 0;
                                            for(int j=0; j < 5; j++){
                                                if(contador < puntuacionRecibida){
                                        %>
                                                    <span class="fa fa-star checked"></span>
                                        <%
                                                    contador++;
                                                }else{
                                        %>
                                                    <span class="fa fa-star"></span>
                                        <%
                                                } 
                                            }
                                        %>                   
                                        </div>
                                    </div>
                                    </div>
                              </div>
                            </li>
                    <%
                        }
                    }else{
                    %>
                        <li class="list-group-item">
                            <div class="alert alert-light" role="alert">
                                No hay ningún viaje realizado
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