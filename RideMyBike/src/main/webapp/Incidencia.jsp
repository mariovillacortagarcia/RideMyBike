

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ridemybike.dominio.*"%>
<%@page import="ridemybike.dominio.db.*"%>
<!doctype html>
<html lang="es">

<head>
  <title> Incidencia - RideMyBike </title>
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
                <jsp:param name="paginaMostrada" value="MisBicis" />
                <jsp:param name="sesionIniciada" value="false" />
            </jsp:include>
  <!-- Contenido -->
<!----- --->
<% 
    String codigoIncidencia = request.getAttribute("codigoIncidencia").toString();
    //se supone que codigoBicicleta es el parametro del anterior JSP y que usare para llamar al servlet extraeBicicleta para obtener la bicicleta
    ArrayList<Incidencia> listaOpiniones= new ArrayList<Incidencia>();
    listaOpiniones = (ArrayList<Incidencia>) request.getAttribute("lista");
%>
  <div class="container pt-4">
    <!-- Navegacion por los viajes -->
    <div class="card text-center">
      <div class="card-header">Incidencia:      </div>
      <div class="card-body">
        <!-- Lista de Opiniones -->
        <ul class="list-group overflow-auto">
            <%
               for(int i = 0; i < listaOpiniones.size(); i++){
                    Incidencia incidencia = IncidenciaDB.selectIncidencia(listaOpiniones.get(i).getCodigoIncidencia());
            %>
          <li class="list-group-item">
            <!--Opinion 1-->
            <div class="alert alert-secondary bg-white" role="alert">
              <div class="row">
                <div class="col-1"></div>
                <div class="col-5">
                  <!--Informacion-->
                  <p align="left">
          
                    <b>Descripcion:</b><%=listaOpiniones.get(i).getDescripcion() %>
                  </p>
                  <p>
                      
                      <b>Gravedad:</b><%=listaOpiniones.get(i).getGravedad()%>
                  </p>
                </div>
                
              </div>
            </div>
          </li>
            

        </ul>
      </div>
    </div>

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
</body>
</html>

