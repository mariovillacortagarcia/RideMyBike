

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
  <div class="container-fluid" style="background-color:#85c1e9">
    <!---Cabecera -->
    <div class="row p-3 align-items-center">
      <div class="col-2">
        <img style="height:auto;max-width:125%" src="img/RideMyBike_icon_green.png" />
      </div>
      <div class="col-6">
        <h5 class="text-white"><b>RideMyBike</b>, la página de préstamo de bicicletas que lucha por un aire
          más puro en nuestras ciudades</h5>
      </div>
      <div class="col-4">
        <div>
          <button type="button" onclick="location.href='registrarse.html'" class="btn btn-light">Registrarse</button>
          <button type="button" onclick="location.href='iniciar_sesion.html'" class="btn btn-success">Iniciar sesión</button>
        </div>

      </div>
    </div>
    <nav class="navbar navbar-expand-lg navbar-dark">
      <!-- Menu de navegacion -->
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle	navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="index.html">Home 🏠</a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="mis_bicis.html">Mis Bicis 🚴‍<span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="viajes.html">Viajes 🚵‍</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="garantias.html">Garantías 🛡️</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="sobre_nosotros.html">Sobre nosotros 💬</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="ayuda.html">Ayuda ❓</a>
          </li>
        </ul>
      </div>
    </nav>
  </div>
<!----- --->
<% 
    String codigoBicicleta = request.getAttribute("codigoBicicleta").toString();
    //se supone que codigoBicicleta es el parametro del anterior JSP y que usare para llamar al servlet extraeBicicleta para obtener la bicicleta
    ArrayList<ValoracionBicicleta> listaOpiniones= new ArrayList<ValoracionBicicleta>();
    listaOpiniones = (ArrayList<ValoracionBicicleta>) request.getAttribute("lista");
%>
  <div class="container pt-4">
    <!-- Navegacion por los viajes -->
    <div class="card text-center">
      <div class="card-header">Lista de Opiniones:      </div>
      <div class="card-body">
        <!-- Lista de Opiniones -->
        <ul class="list-group overflow-auto">
            <%
               for(int i = 0; i < listaOpiniones.size(); i++){
                    Alquiler alquiler = AlquilerDB.selectAlquiler(listaOpiniones.get(i).getCodigo());
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
                    <% // FALTAN LAS ESTRELLITAS DE LA PUNTUACION ASI QUE ESO FALTA%>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star"></span>
                  </div>
                </div>
              </div>
            </div>
          </li>
            <%
                } 
             %>
                    <li class="list-group-item">
            <!--Viaje 1-->
            <div class="alert alert-secondary bg-white" role="alert">
              <div class="row">
                <div class="col-1"></div>
                <div class="col-5">
                  <!--Informacion del viaje-->
                  <p align="left">
                    Desde el 17 de marzo de 2020 a las 10:00 <br>
                    Hasta el 17 de marzo de 2020 a las 10:23
                  </p>
                  <p>
                    <b>Descripcion:</b> Muy buena, la bici estaba en buen estado y se conducia bien ademas mientras la probaba una señora muy maja me regalo un cachorro.
                  </p>
                </div>
                <div class="col-6" >
                  <!--Precio y valoracion-->
                  <div class="container pb-2"> <br> 
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star"></span>
                  </div>
                </div>
              </div>
            </div>
          </li>

                    <li class="list-group-item">
            <!--Viaje 1-->
            <div class="alert alert-secondary bg-white" role="alert">
              <div class="row">
                <div class="col-1"></div>
                <div class="col-5">
                  <!--Informacion del viaje-->
                  <p align="left">
                    Desde el 17 de marzo de 2020 a las 10:00 <br>
                    Hasta el 17 de marzo de 2020 a las 10:23
                  </p>
                  <p>
                    <b>Descripcion:</b> Muy buena, la bici estaba en buen estado y se conducia bien ademas mientras la probaba una señora muy maja me regalo un cachorro.
                  </p>
                </div>
                <div class="col-6" >
                  <!--Precio y valoracion-->
                  <div class="container pb-2"> <br> 
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star"></span>
                  </div>
                </div>
              </div>
            </div>
          </li>

        </ul>
      </div>
    </div>

  </div>
</html>
